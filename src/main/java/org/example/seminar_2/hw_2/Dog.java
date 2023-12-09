package org.example.seminar_2.hw_2;

public class Dog extends Animal {


    private final String color;
    private final String type;

    public Dog(String name, Integer age, Color color, String type) {
        super(name, age);
        this.color = String.valueOf(color);
        this.type = type;
    }


    @Override
    public void voice() {
        System.out.println("Гаф");
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }
}