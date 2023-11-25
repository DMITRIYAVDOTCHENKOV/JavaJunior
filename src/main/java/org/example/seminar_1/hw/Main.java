package org.example.seminar_1.hw;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;


//        1. Напишите программу, которая использует Stream API для обработки списка чисел. Программа должна вывести на
//        экран среднее значение всех четных чисел в списке.
//        2. *Дополнительная задча: Переработать метод балансировки корзины товаров cardBalancing() с использованием
//        Stream API
public class Main {
    public static void main(String[] args) {
        List<Integer> number = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            number.add(i);
        }
        System.out.println(number);

        OptionalDouble avgnumber = number.stream().filter(num -> num % 2 == 0).mapToInt(Integer::intValue).average();

        if (avgnumber.isPresent()){
            System.out.printf("Среднее значение четных чисел: %.2f", avgnumber.getAsDouble());
        }else {
            System.out.println("Четных чисел нет.");
        }
    }


}
