package com.amarchuk.jms.extra.model;

public class User {

    String name;
    String lastname;

    public User(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
