package org.example.seminar_2.hw_2;



public class Cat extends Animal {

    private final Color color;
    private final CatAttribute catAttribute;

    public Cat(String name, Integer age, Color color, CatAttribute catAttribute) {
        super(name, age);
        this.color = color;
        this.catAttribute = catAttribute;
    }

    public static class CatAttribute {
        String meal;
        int weight;
        Color eyesColor;
    }



    @Override
    public void voice() {
        System.out.println("Мяукать");
    }

    @Override
    public void animalInfo() {
        super.animalInfo();
        System.out.println("Кошку зовут " + super.getName() + "; color - " + this.color);
    }

    public CatAttribute getCatAttribute() {
        return catAttribute;
    }
}