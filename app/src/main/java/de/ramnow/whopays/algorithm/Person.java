package de.ramnow.whopays.algorithm;

public class Person {
    
    public Person() {
        this.name = "";
    }

    public Person(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
