package com.example.sweetgift.utils.candy;

import com.example.sweetgift.model.Candy;

public class CandyValidator {
    public static boolean isCandyValid(Candy candy) {
        return candy != null &&
                arePropertiesValid(candy.getName(), candy.getBrand(),
                        candy.getPrice(), candy.getWeight(), candy.getSugar());
    }

    public static boolean arePropertiesValid(String name, String brand, double price, int weight, int sugar) {
        return isStringValid(name) && isStringValid(brand) &&
                isNumberValid(price) && isNumberValid(weight) &&
                isNumberValid(sugar) &&
                isSugarAmountValid(sugar, weight);
    }

    public static boolean isStringValid(String value) {
        return value != null && !value.isBlank();
    }

    public static boolean isNumberValid(double value) {
        return value >= 0;
    }

    private static boolean isSugarAmountValid(int sugar, int weight){
        return isNumberValid(sugar)
                && isNumberValid(weight)
                && sugar < weight;
    }
}
