package testingapplicationios.tests;

import nativeapp.ios.testingapp.EpamIosTestApp;
import org.testng.annotations.Test;
import testingapplicationios.IosApplicationInit;

public class ToggleSwitchTest extends IosApplicationInit {

    @Test
    public void ToggleSwitchOnTest(){
        EpamIosTestApp.toggleSwitch.click();
    }
}
