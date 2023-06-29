package com.nik.caffe;

import com.nik.caffe.model.Buyer;
import com.nik.caffe.model.Cashbox;
import com.nik.caffe.model.Order;
import com.nik.caffe.thread.RunSchedulerThread;
import com.nik.caffe.service.HistoryOrderService;
import com.nik.caffe.thread.BuyerThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.nik.caffe.Params.*;


public class CaffeRunner {

    public static void main(String[] args) throws InterruptedException {

        // TODO: Все созданные заказы
        List<Order> allOrders = new ArrayList<>();

        HistoryOrderService historyOrdersService = new HistoryOrderService(allOrders);
        BlockingQueue<Cashbox> cashboxes = new ArrayBlockingQueue<>(2, true, List.of(new Cashbox(), new Cashbox()));
        ExecutorService threadPool = Executors.newCachedThreadPool();

        // TODO: Создаём покупателей
        List<Buyer> allBuyers = new ArrayList<>();
        IntStream.range(0, NUMBER_BUYERS).forEach(s->allBuyers.add(new Buyer(s)));
        // TODO: Запускает всё потоки покупателей
        allBuyers.stream()
                .map(buyer->threadPool.submit(new BuyerThread(cashboxes, historyOrdersService, buyer)))
                .collect(Collectors.toList());

        // TODO: Запускаем планировщик задач
        threadPool.submit(new RunSchedulerThread(historyOrdersService));
    }
}
