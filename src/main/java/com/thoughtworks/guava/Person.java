package com.thoughtworks.guava;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 6/27/13
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Person {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
