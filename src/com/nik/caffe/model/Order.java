package com.nik.caffe.model;

import com.nik.caffe.enams.Menu;

import java.util.List;

public class Order {

    private static Long generator = 1L;
    private Long id;
    private Buyer buyer;
    private Cashbox cashbox;
    private List<Menu> order;

    public Order(Buyer buyer, Cashbox cashbox, List<Menu> order) {
        this.id = generator++;
        this.buyer = buyer;
        this.cashbox = cashbox;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Menu> getOrder() {
        return order;
    }

    public static Long getGenerator() {
        return generator;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Cashbox getCashbox() {
        return cashbox;
    }

    public void setOrder(List<Menu> order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cashbox=" + cashbox.getName() +
                ", buyer='" + buyer.getName() + '\'' +
                ", order=" + order +
                '}';
    }
}
