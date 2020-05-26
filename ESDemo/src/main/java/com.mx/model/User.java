package com.mx.model;

public class User {

    public int age;
    public String name;

    public User() {
        super();
    }

    public User(int age, String name, long weight, boolean married) {
        super();
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
