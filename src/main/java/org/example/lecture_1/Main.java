package org.example.lecture_1;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        /**
         * стандартная реализация
         */

//        Plainlnterface plainlnterface = new Plainlnterface() {
//            @Override
//            public String action(int x, int y) {
//                return String.valueOf(x + y);
//            }
//        };

/**
 * тоже самое через лямбду
 */
//        Plainlnterface plainlnterface = (x, y) -> (x + y);
//        Plainlnterface plainlnterface1 = (x, y) -> Integer.compare(x, y);
//        Plainlnterface plainlnterface = Integer::sum;
//        Plainlnterface plainlnterface1 = Integer::compare;
//
//        System.out.println(plainlnterface.action(2, 5));
//        System.out.println(plainlnterface1.action(2,2));

        List<String> list = Arrays.asList("Привет", "мир", "!", "я" , "родился", "!");
        list.stream().filter(s -> s.length() > 4).forEach(System.out::println);


    }
}
