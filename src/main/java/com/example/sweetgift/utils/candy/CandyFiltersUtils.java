package com.example.sweetgift.utils.candy;

import com.example.sweetgift.model.Candy;
import com.example.sweetgift.model.CandyFilters;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CandyFiltersUtils {
    private CandyFilters filters;
    private List<Candy> candyList;

    public CandyFiltersUtils(List<Candy> candyList, CandyFilters filters){
        this.candyList = candyList;
        this.filters = filters;
    }

    public List<Candy> filter(){
        filterByPrice();
        filterByWeight();
        filterBySugar();
        sort();

        return candyList;
    }

    private void filterByPrice(){
        if(isPriceFilterValid(filters)) {
            filterByLowerPriceBound();
            filterByUpperPriceBound();
        }
    }

    private void filterByWeight(){
        if(isWeightFilterValid(filters)) {
            filterByLowerWeightBound();
            filterByUpperWeightBound();
        }
    }

    private void filterBySugar(){
        if(isSugarFilterValid(filters)) {
            filterByLowerSugarBound();
            filterByUpperSugarBound();
        }
    }

    private void filterByLowerPriceBound(){
        if(!isLowerPriceBoundValid(filters)){
            return;
        }

        candyList = candyList.stream()
                .filter(candy -> candy.getPrice() >= filters.getPriceMin())
                .collect(Collectors.toList());
    }

    private void filterByUpperPriceBound(){
        if(!isUpperPriceBoundValid(filters)){
            return;
        }

        candyList = candyList.stream()
                .filter(candy -> candy.getPrice() <= filters.getPriceMax())
                .collect(Collectors.toList());
    }

    private void filterByLowerWeightBound(){
        if(!isLowerWeightBoundValid(filters)){
            return;
        }

        candyList = candyList.stream()
                .filter(candy -> candy.getWeight() >= filters.getWeightMin())
                .collect(Collectors.toList());
    }

    private void filterByUpperWeightBound(){
        if(!isUpperWeightBoundValid(filters)){
            return;
        }

        candyList = candyList.stream()
                .filter(candy -> candy.getWeight() <= filters.getWeightMax())
                .collect(Collectors.toList());
    }


    private void filterByLowerSugarBound(){
        if(!isLowerSugarBoundValid(filters)){
            return;
        }

        candyList = candyList.stream()
                .filter(candy -> candy.getSugar() >= filters.getSugarMin())
                .collect(Collectors.toList());
    }

    private void filterByUpperSugarBound(){
        if(!isUpperSugarBoundValid(filters)){
            return;
        }

        candyList = candyList.stream()
                .filter(candy -> candy.getSugar() <= filters.getSugarMax())
                .collect(Collectors.toList());
    }

    private void sort(){
        String sort = filters.getSort();
        if(!isSortValid(sort)){
            return;
        }

        switch (sort){
            case "price":
                sortByPrice();
                break;
            case "weight":
                sortByWeight();
                break;
            case "sugar":
                sortBySugar();
                break;
        }
    }

    private void sortByPrice(){
        candyList.sort(Comparator.comparingDouble(Candy::getPrice));
    }

    private void sortByWeight(){
        candyList.sort(Comparator.comparingInt(Candy::getWeight));
    }

    private void sortBySugar(){
        candyList.sort(Comparator.comparingInt(Candy::getSugar));
    }

    private static boolean isPriceFilterValid(CandyFilters filters){
        return isLowerPriceBoundValid(filters) || isUpperPriceBoundValid(filters);
    }

    private static boolean isWeightFilterValid(CandyFilters filters){
        return isLowerWeightBoundValid(filters) || isUpperWeightBoundValid(filters);
    }

    private static boolean isSugarFilterValid(CandyFilters filters){
        return isLowerSugarBoundValid(filters) || isUpperSugarBoundValid(filters);
    }

    private static boolean isLowerWeightBoundValid(CandyFilters filters){
        if(filters.getWeightMin() == null){
            return false;
        }

        if(filters.getWeightMax() == null){
            return true;
        }

        return filters.getWeightMin() <= filters.getWeightMax();
    }

    private static boolean isUpperWeightBoundValid(CandyFilters filters){
        if(filters.getWeightMax() == null){
            return false;
        }

        if(filters.getWeightMin() == null){
            return true;
        }

        return filters.getWeightMin() <= filters.getWeightMax();
    }

    private static boolean isLowerSugarBoundValid(CandyFilters filters){
        if(filters.getSugarMin() == null){
            return false;
        }

        if(filters.getSugarMax() == null){
            return true;
        }

        return filters.getSugarMin() <= filters.getSugarMax();
    }


    private static boolean isUpperSugarBoundValid(CandyFilters filters){
        if(filters.getSugarMax() == null){
            return false;
        }

        if(filters.getSugarMin() == null){
            return true;
        }

        return filters.getSugarMin() <= filters.getSugarMax();
    }

    private static boolean isLowerPriceBoundValid(CandyFilters filters){
        if(filters.getPriceMin() == null){
            return false;
        }

        if(filters.getPriceMax() == null){
            return true;
        }

        return filters.getPriceMin() <= filters.getPriceMax();
    }

    private static boolean isUpperPriceBoundValid(CandyFilters filters){
        if(filters.getPriceMax() == null){
            return false;
        }

        if(filters.getPriceMin() == null){
            return true;
        }

        return filters.getPriceMin() <= filters.getPriceMax();
    }

    private static boolean isSortValid(String sort){
        return sort != null
                && (sort.equals("price") || sort.equals("weight") || sort.equals("sugar"));
    }
}
