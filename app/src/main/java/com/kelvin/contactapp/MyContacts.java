package com.kelvin.contactapp;

public class MyContacts {
    private String name, number, image;

    public MyContacts(String name, String number, String image) {
        this.name = name;
        this.number = number;
        this.image = image;
       //System.out.println("image string 3 : "+image);
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
}
