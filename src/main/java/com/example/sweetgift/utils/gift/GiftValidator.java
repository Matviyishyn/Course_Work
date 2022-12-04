package com.example.sweetgift.utils.gift;

import com.example.sweetgift.model.Candy;
import com.example.sweetgift.model.Gift;

import java.util.List;

public class GiftValidator {
    public static boolean isGiftValid(Gift gift){
        return gift != null
                && arePropertiesValid(gift.getReceiver(), gift.getAddress(), gift.getCandyList());
    }

    public static boolean arePropertiesValid(String receiver, String address, List<Candy> candyList){
        return isStringPropertyValid(receiver)
                && isStringPropertyValid(address)
                && isCandyListValid(candyList);
    }

    public static boolean isStringPropertyValid(String property){
        return property != null && !property.isBlank();
    }

    public static boolean isCandyListValid(List<Candy> candyList){
        return candyList != null && !candyList.isEmpty();
    }
}
