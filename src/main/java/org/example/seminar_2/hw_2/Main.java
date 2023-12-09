package org.example.seminar_2.hw_2;

import java.lang.reflect.*;

//Задача 1:
//        Создайте абстрактный класс "Animal" с полями "name" и "age".
//        Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
//        Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
//        Выведите на экран информацию о каждом объекте.
//        Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
//
//        Дополнительная задача:
//
//        Доработайте метод генерации запроса на удаление объекта из таблицы БД (DELETE FROM <Table> WHERE ID = '<id>')
//        В классе QueryBuilder который мы разработали на семинаре.
public class Main {
    public static void main(String[] args) {

        /**
         * Создать массив объектов типа "Animal":
         */
        Animal[] animals = new Animal[3];
        animals[0] = new Cat("Мурзик",5, Color.BLACK, new Cat.CatAttribute());
        animals[1] = new Dog("Барбос", 1,Color.DOTTED, "Овчарка");
        animals[2] = new Cat("Васька",14, Color.GREY, new Cat.CatAttribute());


/**
 *  Вывести информацию о каждом объекте:
 */
        for (Animal animal : animals) {
            Class<?> animalClass = animal.getClass();
            System.out.println("Информация о животном:");
            System.out.println("Класс: " + animalClass.getName());
            System.out.println("Название: " + animal.getName());

            if (animalClass == Cat.class) {
                Cat cat = (Cat) animal;
                System.out.println("Цвет: " + cat.getCatAttribute().eyesColor);
            } else if (animalClass == Dog.class) {
                Dog dog = (Dog) animal;
                System.out.println("Цвет: " + dog.getColor());
                System.out.println("Тип: " + dog.getType());
            }

            Field[] fields = animalClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object fieldValue = field.get(animal);
                    System.out.println("Поле " + field.getName() + ": " + fieldValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("--------------------");
        }

/**
 * Вызвать метод "voice()" у каждого объекта, если такой метод присутствует:
 */
        for (Animal animal : animals) {
            Class<?> animalClass = animal.getClass();

            try {
                Method voiceMethod = animalClass.getMethod("voice");
                System.out.println("Результат вызова метода voice() у объекта класса " + animalClass.getName() + ":");
                voiceMethod.invoke(animal);
                System.out.println("--------------------");
            } catch (NoSuchMethodException e) {
                // Обработка исключения, если метод voice() отсутствует
            } catch (IllegalAccessException | InvocationTargetException e) {
                // Обработка исключения, возникающего при вызове метода
            }
        }
    }
}
