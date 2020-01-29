package io.github.com.entities;

/**
 * Created by Dmitrii Pavlov on 10.10.2019
 * Email: delnote@gmail.com; Skype: Dmitrii Pavlov
 */

public class FormUsers {
    public static FormContacts BLANK_CONTACT = new FormContacts();
    public static FormContacts DEFAULT_CONTACT = defaultContact();
    public static FormContacts DEFAULT_CHECK = new FormContacts().set(c -> {
        c.name = "Mark"; c.lastName = "Otto"; c.userName = "";
        c.city = ""; c.state = ""; c.zip = ""; c.accept = "false";
    });
    public static FormContacts HORIZONTAL_FORM_CONTACT = new FormContacts().set(c -> {
        c.email = "delnote@gmail.com"; c.password = "qwerty";
        c.radio = "option2"; c.accept = "true";
    });
    public static FormContacts HORIZONTAL_FORM_DEFAULT = new FormContacts().set(c -> {
        c.email = ""; c.password = ""; c.radio = "option1"; c.accept = "false";
    });
    public static FormContacts LOWER_CASE_NAME_CONTACT =
        defaultContact().set(c -> c.name = c.name.toLowerCase());
    public static FormContacts UPPER_CASE_NAME_CONTACT =
        defaultContact().set(c -> c.name = c.name.toUpperCase());

    public static FormContacts ONLY_NAME_FILLED_DEFAULT_CONTACT =
        new FormContacts().set(c -> c.name = "Roman");

    public static FormContacts ALL_EXCEPT_NAME_FILLED_DEFAULT_CONTACT =
        defaultContact().set(c -> c.name = null);

    public static FormContacts INLINE_FORM_CONTACT =
        new FormContacts().set(c -> {
            c.name = "Arnold";
            c.userName = "Reiner";
        });

    public static FormContacts ONLY_NAME_FILLED_INLINE_FORM_CONTACT =
        new FormContacts().set(c -> c.name = "Arnold");

    public static void clearBlankContact() {
        BLANK_CONTACT = new FormContacts();
    }

    private static FormContacts defaultContact() {
        return new FormContacts().set(c -> {
            c.name = "Dmitrii"; c.lastName = "Pavlov"; c.userName = "Delnote";
            c.city = "Saint-Petersburg"; c.state = "North capital"; c.zip = "190000";
            c.accept = "true";
        });
    }
}
