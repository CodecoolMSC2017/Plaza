package com.codecool.plaza.api;

import java.util.Date;

public class FoodProduct extends Product {
    private int calories;
    private Date bestBefore;

    public FoodProduct(long barcode, String manufacturer, int calories, Date bestBefore){
        super(barcode, manufacturer);
        this.calories = calories;
        this.bestBefore = bestBefore;
    }

    public boolean isStillConsumable(){
        return true;
    }

    public int getCalories() {
        return calories;
    }

    public String toString(){
        return "Bakker";
    }

}
