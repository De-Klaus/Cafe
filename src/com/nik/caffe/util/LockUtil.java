package com.nik.caffe.util;

import com.nik.caffe.model.Order;
import com.nik.caffe.service.HistoryOrderService;

import java.util.List;

public class LockUtil {

    public static List<Order> getAllOrders(HistoryOrderService historyOrdersService) {
        lockAccounts(historyOrdersService);
        List<Order> orderList;
        try {
            orderList = historyOrdersService.getHistoryOrders();
        } finally {
            historyOrdersService.getLock().unlock();
        }
        return orderList;
    }

    public static void lockAccounts(HistoryOrderService historyOrdersService) {
        while (true) {
            boolean historyLockResult = historyOrdersService.getLock().tryLock();
            if (historyLockResult) {
                break;
            }
        }
    }

}
