package com.application.entity.dto;

public class PopularDishReport {

    private String dishName;
    private Long quantityOrdered;
    private Long salesSum;

    public PopularDishReport(String dishName, Long quantityOrdered, Long salesSum) {
        this.dishName = dishName;
        this.quantityOrdered = quantityOrdered;
        this.salesSum = salesSum;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Long getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Long quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public Long getSalesSum() {
        return salesSum;
    }

    public void setSalesSum(Long salesSum) {
        this.salesSum = salesSum;
    }

    @Override
    public String toString() {
        return "DishReport{" +
                "dishName='" + dishName + '\'' +
                ", quantityOrdered=" + quantityOrdered +
                ", salesSum=" + salesSum +
                '}';
    }
}
