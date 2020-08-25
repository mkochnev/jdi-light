package com.epam.jdi.light.actions;

import com.epam.jdi.tools.Safe;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

import static com.epam.jdi.light.actions.ActionHelper.*;
import static com.epam.jdi.light.common.Exceptions.safeException;
import static com.epam.jdi.light.settings.WebSettings.logger;
import static com.epam.jdi.tools.LinqUtils.newList;

/**
 * Created by Roman Iovlev on 26.09.2019
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */
@SuppressWarnings("unused")
@Aspect
public class ActionProcessor {
    //com.epam.jdi.light.elements.*.*
    //@Pointcut("execution(* *(..)) && @annotation(com.epam.jdi.light.common.JDIAction)")
    @Pointcut("within(com.epam.jdi.light..*) && @annotation(com.epam.jdi.light.common.JDIAction)")
    protected void jdiPointcut() {  }
    @Pointcut("execution(* *(..)) && @annotation(io.qameta.allure.Step)")
    protected void stepPointcut() {  }
    public static Safe<List<ActionObject>> jStack = new Safe<>();

    @Around("jdiPointcut()")
    public Object jdiAround(ProceedingJoinPoint jp) {
        ActionObject jInfo = new ActionObject(jp);
        if (jInfo.topLevel())
            jStack.set(newList(jInfo));
        else
            jStack.get().add(jInfo);
        try {
            failedMethods.clear();
            BEFORE_JDI_ACTION.execute(jInfo);
            Object result = jInfo.topLevel()
                ? stableAction(jInfo)
                : defaultAction(jInfo);
            return AFTER_JDI_ACTION.execute(jInfo, result);
        } catch (Throwable ex) {
            logger.debug("ActionProcessor exception:" + safeException(ex));
            throw ACTION_FAILED.execute(jInfo, ex);
        }
        finally {
            jInfo.clear();
        }
    }

    @Before("stepPointcut()")
    public void step(JoinPoint jp) {
        beforeStepAction(jp);
    }
}
