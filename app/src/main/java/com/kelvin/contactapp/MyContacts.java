package com.kelvin.contactapp;

import android.provider.ContactsContract;
import android.util.Log;

public class MyContacts {
    private String name, number, image, id;

    public MyContacts(String name, String number, String image, String id) {
        this.name = name;
        this.number = number;
        this.image = image;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

}
