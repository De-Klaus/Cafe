package com.nik.caffe.service;

import com.nik.caffe.enams.Menu;
import com.nik.caffe.model.Buyer;
import com.nik.caffe.model.Cashbox;
import com.nik.caffe.model.Order;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HistoryOrderService {

    private final Lock lock = new ReentrantLock();
    private final List<Order> allOrders;

    public HistoryOrderService(List<Order> allOrders) {
        this.allOrders = allOrders;
    }

    public void addHistoryOrders(Buyer buyer, Cashbox cashbox, List<Menu> itemsOrder) {
        System.out.println(String.format("Поток %s записывает данные в БД", buyer.getName()));
        Order newOrder = new Order(buyer,cashbox,itemsOrder);
        allOrders.add(newOrder);
        System.out.println(String.format("Поток %s добавил данные в БД", buyer.getName()));
    }

    public List<Order> getHistoryOrders() {
        return allOrders;
    }

    public Lock getLock() {
        return lock;
    }
}
