package io.github.epam.test.data;

import io.github.com.entities.SupportMessage;

public class SupportMessages {
    public static SupportMessage EXAMPLE_MESSAGE = new SupportMessage().set(field -> {
        field.supportEmail = "help-me.please@mail.org";
        field.supportMessage = "Nothing works! Nothing! Answer me, PLEASE!";
    });
    public static SupportMessage TEMPLATE_MESSAGE = new SupportMessage().set(field -> {
        field.supportEmail = "";
        field.supportMessage = "";
    });
}
