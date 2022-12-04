package com.example.sweetgift.model;

public class CandyFilters {
    Double priceMin;
    Double priceMax;
    Integer weightMin;
    Integer weightMax;
    Integer sugarMin;
    Integer sugarMax;
    String sort;

    CandyFilters(){}

    public CandyFilters(Double priceMin, Double priceMax, Integer weightMin, Integer weightMax, Integer sugarMin, Integer sugarMax, String sort) {
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.weightMin = weightMin;
        this.weightMax = weightMax;
        this.sugarMin = sugarMin;
        this.sugarMax = sugarMax;
        this.sort = sort;
    }

    public Double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Double priceMin) {
        this.priceMin = priceMin;
    }

    public Double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Double priceMax) {
        this.priceMax = priceMax;
    }

    public Integer getWeightMin() {
        return weightMin;
    }

    public void setWeightMin(Integer weightMin) {
        this.weightMin = weightMin;
    }

    public Integer getWeightMax() {
        return weightMax;
    }

    public void setWeightMax(Integer weightMax) {
        this.weightMax = weightMax;
    }

    public Integer getSugarMin() {
        return sugarMin;
    }

    public void setSugarMin(Integer sugarMin) {
        this.sugarMin = sugarMin;
    }

    public Integer getSugarMax() {
        return sugarMax;
    }

    public void setSugarMax(Integer sugarMax) {
        this.sugarMax = sugarMax;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
