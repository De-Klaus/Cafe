package com.nik.caffe.model;

import java.math.BigDecimal;

public class WorkCash {

    private Integer id;
    private Long countOrders;
    private BigDecimal allSum;
    private String nameCashbox;

    public WorkCash(Integer id){
        this.id = id;
        this.countOrders = 0L;
        this.allSum = BigDecimal.ZERO;
    }

    public void addSum(BigDecimal sum) {
        allSum = allSum.add(sum);
    }

    public void addOrders(Long orders) {
        this.countOrders += orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCountOrders() {
        return countOrders;
    }

    public void setCountOrders(Long countOrders) {
        this.countOrders = countOrders;
    }

    public BigDecimal getAllSum() {
        return allSum;
    }

    public void setAllSum(BigDecimal allSum) {
        this.allSum = allSum;
    }

    public String getNameCashbox() {
        return nameCashbox;
    }

    public void setNameCashbox(String nameCashbox) {
        this.nameCashbox = nameCashbox;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", countOrders=" + countOrders +
                ", allSum=" + allSum +
                ", nameCashbox='" + nameCashbox;
    }
}
