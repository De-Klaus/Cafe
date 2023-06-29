package com.nik.caffe.model;

import java.math.BigDecimal;

public class PurchaseBuyer {
    private Integer id;
    private String nameBuyer;
    private Long countOrders;
    private BigDecimal allSum;
    private BigDecimal calories;

    public PurchaseBuyer(Integer id){
        this.id = id;
        this.countOrders = 0L;
        this.allSum = BigDecimal.ZERO;
        this.calories = BigDecimal.ZERO;
    }

    public void addSum(BigDecimal sum) {
        allSum = allSum.add(sum);
    }

    public void addCalories(BigDecimal calories) {
        this.calories = calories.add(calories);
    }

    public void addOrders(Long orders) {
        this.countOrders += orders;
    }

    public Integer getId() {
        return id;
    }

    public String getNameBuyer() {
        return nameBuyer;
    }

    public void setNameBuyer(String nameBuyer) {
        this.nameBuyer = nameBuyer;
    }

    public Long getCountOrders() {
        return countOrders;
    }

    public BigDecimal getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", nameBuyer='" + nameBuyer + '\'' +
                ", countOrders=" + countOrders +
                ", allSum=" + allSum +
                ", calories=" + calories;
    }
}
