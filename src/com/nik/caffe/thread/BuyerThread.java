package com.nik.caffe.thread;

import com.nik.caffe.model.Buyer;
import com.nik.caffe.service.HistoryOrderService;
import com.nik.caffe.model.Cashbox;
import com.nik.caffe.enams.Menu;
import com.nik.caffe.util.LockUtil;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class BuyerThread implements Runnable {

    private final BlockingQueue<Cashbox> cashboxes;
    private final HistoryOrderService historyOrdersService;
    private final Buyer currentBuyer;

    public BuyerThread(BlockingQueue<Cashbox> cashboxes, HistoryOrderService historyOrdersService, Buyer currentBuyer) {
        this.cashboxes = cashboxes;
        this.historyOrdersService = historyOrdersService;
        this.currentBuyer = currentBuyer;
    }

    @Override
    public void run() {
        try {
            Cashbox cashbox = cashboxes.take();
            System.out.println(Thread.currentThread().getName() + " обслуживается в кассе " + cashbox);
            // Generation of a new order
            List<Menu> itemsOrder = Menu.getRandomOrder();
            long sleepTime = (30L-itemsOrder.size())*1000L;
            LockUtil.lockAccounts(historyOrdersService);
            try {
                currentBuyer.setName(Thread.currentThread().getName());
                historyOrdersService.addHistoryOrders(currentBuyer,cashbox,itemsOrder);
            }finally {
                historyOrdersService.getLock().unlock();
            }
            if(sleepTime>0)
                Thread.sleep(sleepTime); // 30 секунд
            System.out.println(Thread.currentThread().getName() + " освобождаю кассу " + cashbox);
            cashboxes.add(cashbox);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
