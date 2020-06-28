package com.mx.model;

import lombok.Data;

@Data
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


}
