package io.github.com.entities;

import com.epam.jdi.tools.DataClass;

public class SimpleContact extends DataClass<SimpleContact> {
    public String name, email, phone;

    public SimpleContact(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
