package org.example.seminar_2.hw_2;

public abstract class Animal {
    private final String name;
    private final Integer age;
    public Animal(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public abstract void voice();

    public void animalInfo() {
        System.out.println("Название животного " + this.name);
    }


    public String getName() {
        return name;
    }
}