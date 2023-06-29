package com.nik.caffe.scheduler;

import com.nik.caffe.comparator.BuyerComparator;
import com.nik.caffe.comparator.CashboxComparator;
import com.nik.caffe.model.*;
import com.nik.caffe.service.HistoryOrderService;
import com.nik.caffe.util.LockUtil;

import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class ComparisonIndicatorsScheduler extends TimerTask {

    private final HistoryOrderService historyOrdersService;

    public ComparisonIndicatorsScheduler(HistoryOrderService historyOrdersService) {
        this.historyOrdersService = historyOrdersService;
    }

    @Override
    public void run() {

        // TODO получаем историю всех заказов
        List<Order> orderAllList = LockUtil.getAllOrders(historyOrdersService);

        // TODO определяет лучшего кассира
        List<WorkCash> sortsCash = sortCash(orderAllList);
        if (!sortsCash.isEmpty()) {
            System.out.println(String.format("Лучший кассир за весь период: %s", sortsCash.stream().findFirst()));
        }

        // TODO определяет самого прожорливого покупателя
        List<PurchaseBuyer> sortsBuyer = sortBuyer(orderAllList);
        if (!sortsBuyer.isEmpty()) {
            System.out.println(String.format("Самый прожорливый покупатель за весь период: %s", sortsBuyer.stream().findFirst()));
        }

    }


    /**
     * Sort a list of cash
     * @param orderList
     * @return
     */
    private List<WorkCash> sortCash(List<Order> orderList){
        return orderList.stream()
                .map(Order::getCashbox)
                .map(Cashbox::getId)
                .distinct()
                .map(WorkCash::new)
                .peek(wk->{orderList.stream()
                        .filter(order -> order.getCashbox().getId()==wk.getId())
                        .forEach(order ->{
                            // add the name of the cash box
                            if(wk.getNameCashbox()==null)
                                wk.setNameCashbox(order.getCashbox().getName());
                            // count order
                            order.getOrder()
                                    .forEach(m -> {
                                        wk.addSum(m.getPrice());
                                        wk.addOrders(1L);
                                    });});})
                .sorted(new CashboxComparator())
                .collect(Collectors.toList());
    }

    /**
     * Sort the order list buyer
     * @param orderList
     * @return
     */
    private List<PurchaseBuyer> sortBuyer(List<Order> orderList) {
        return orderList.stream()
                .map(Order::getBuyer)
                .map(Buyer::getId)
                .distinct()
                .map(PurchaseBuyer::new)
                .peek(pb->{orderList.stream()
                        .filter(order -> order.getBuyer().getId()==pb.getId())
                        .forEach(order ->{
                            // add the name of the cash box
                            if(pb.getNameBuyer()==null)
                                pb.setNameBuyer(order.getBuyer().getName());
                            // count order
                            order.getOrder()
                                    .forEach(m -> {
                                        pb.addSum(m.getPrice());
                                        pb.addCalories(m.getCalories());
                                        pb.addOrders(1L);
                                    });});})
                .sorted(new BuyerComparator())
                .collect(Collectors.toList());
    }
}
