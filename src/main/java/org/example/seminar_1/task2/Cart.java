package org.example.seminar_1.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Корзина
 *
 * @param <T> Еда
 */
public class Cart<T extends Food> {

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    public Cart(Class<T> clazz, UMarket market) {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs() {
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> {
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                    index.getAndIncrement(), food.getName(),
                    food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет",
                    food.getCarbohydrates() ? "Да" : "Нет");
        });
    }

    /**
     * Балансировка корзины
     */
    public void cardBalancing() {
        /**
         * Метод с использованием  стримов
         */
        AtomicBoolean proteins = new AtomicBoolean(foodstuffs.stream().anyMatch(Food::getProteins));
        AtomicBoolean fats = new AtomicBoolean(foodstuffs.stream().anyMatch(Food::getFats));
        AtomicBoolean carbohydrates = new AtomicBoolean(foodstuffs.stream().anyMatch(Food::getCarbohydrates));

        if (proteins.get() && fats.get() && carbohydrates.get()) {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        List<Food> availableFoods = (List<Food>) market.getThings(Food.class);
        /**
         *  используем стримы для проверки, есть ли в доступных продуктах продукт, содержащий протеин,
         *  и добавляем его в список foodstuffs, если это необходимо. Здесь proteins - это переменная типа boolean,
         *  указывающая наличие/отсутствие продукта с углеводами в списке foodstuffs.
         */
        availableFoods.stream()
                .filter(food -> !proteins.get() && food.getProteins())
                .findFirst()
                .ifPresent(food -> {
                    proteins.set(true);
                    foodstuffs.add((T) food);
                });
        /**
         *  используем стримы для проверки, есть ли в доступных продуктах продукт, содержащий жиры,
         *  и добавляем его в список foodstuffs, если это необходимо. Здесь fats - это переменная типа boolean,
         *  указывающая наличие/отсутствие продукта с углеводами в списке foodstuffs.
         */
        availableFoods.stream()
                .filter(food -> !fats.get() && food.getFats())
                .findFirst()
                .ifPresent(food -> {
                    fats.set(true);
                    foodstuffs.add((T) food);
                });
        /**
         *  используем стримы для проверки, есть ли в доступных продуктах продукт, содержащий углеводы,
         *  и добавляем его в список foodstuffs, если это необходимо. Здесь carbohydrates - это переменная типа boolean,
         *  указывающая наличие/отсутствие продукта с углеводами в списке foodstuffs.
         */
        availableFoods.stream()
                .filter(food -> !carbohydrates.get() && food.getCarbohydrates())
                .findFirst()
                .ifPresent(food -> {
                    carbohydrates.set(true);
                    foodstuffs.add((T) food);
                });

        if (proteins.get() && fats.get() && carbohydrates.get())
            System.out.println("Корзина сбалансирована по БЖУ.");
        else
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");

        /**
         * Метод без стримов
         */
//        boolean proteins = false;
//        boolean fats = false;
//        boolean carbohydrates = false;
//
//        for (var food : foodstuffs)
//        {
//            if (!proteins && food.getProteins())
//                proteins = true;
//            else
//            if (!fats && food.getFats())
//                fats = true;
//            else
//            if (!carbohydrates && food.getCarbohydrates())
//                carbohydrates = true;
//            if (proteins && fats && carbohydrates)
//                break;
//        }
//
//        if (proteins && fats && carbohydrates)
//        {
//            System.out.println("Корзина уже сбалансирована по БЖУ.");
//            return;
//        }
//
//        for (var thing : market.getThings(Food.class))
//        {
//            if (!proteins && thing.getProteins())
//            {
//                proteins = true;
//                foodstuffs.add((T)thing);
//            }
//            else if (!fats && thing.getFats())
//            {
//                fats = true;
//                foodstuffs.add((T)thing);
//            }
//            else if (!carbohydrates && thing.getCarbohydrates())
//            {
//                carbohydrates = true;
//                foodstuffs.add((T)thing);
//            }
//            if (proteins && fats && carbohydrates)
//                break;
//        }
//
//        if(proteins &&fats &&carbohydrates)
//            System.out.println("Корзина сбалансирована по БЖУ.");
//        else
//                System.out.println("Невозможно сбалансировать корзину по БЖУ.");

    }

}
