package org.example;


public class Main {
    public static void main(String[] args) {
        int[] array1 = {2, 2, 2, 1,1 ,2, 2, 10, 1,1};
        boolean result1 = checkBalance(array1);
        System.out.println(result1);  // Вывод: true

        int[] array2 = {1, 1, 1, 2, 1};
        boolean result2 = checkBalance(array2);
        System.out.println(result2);  // Вывод: true


    }

        public static boolean checkBalance(int[] array){
            int leftSum = 0;
            int rightSum = 0;

            // Находим сумму всех элементов массива
            for (int i = 0; i < array.length; i++) {
                rightSum += array[i];
            }

            // Проверяем каждую позицию массива
            for (int i = 0; i < array.length - 1; i++) {
                leftSum += array[i];
                rightSum -= array[i];

                // Если суммы левой и правой частей равны, возвращаем true
                if (leftSum == rightSum) {
                    return true;
                }
            }

            // Если не найдено место с равными суммами, возвращаем false
            return false;
        }

    }
