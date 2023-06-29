package com.nik.caffe.scheduler;

import com.nik.caffe.model.Buyer;
import com.nik.caffe.model.Order;
import com.nik.caffe.model.PurchaseBuyer;
import com.nik.caffe.service.HistoryOrderService;
import com.nik.caffe.util.FileUtil;
import com.nik.caffe.util.LockUtil;

import java.nio.file.Path;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

import static com.nik.caffe.Params.CsvParams.BUYER_DIR;

public class BuyerStatisticsScheduler extends TimerTask {

    private final HistoryOrderService historyOrdersService;

    public BuyerStatisticsScheduler(HistoryOrderService historyOrdersService) {
        this.historyOrdersService = historyOrdersService;
    }

    @Override
    public void run() {
        System.out.println("BuyerStatisticsScheduler: "+ Thread.currentThread().getName());
        // TODO получаем историю всех заказов
        List<Order> orderAllList = LockUtil.getAllOrders(historyOrdersService);

        // TODO статистику по всем покупателям
        List<PurchaseBuyer> statisticsBuyers = doStatisticsBuyers(orderAllList);
        System.out.println("Total Buyer: "+statisticsBuyers.toString());

        // TODO записываем в файл
        Path path = FileUtil.createPath(BUYER_DIR);
        boolean addSuccessfully = FileUtil.writeFile(path,statisticsBuyers);
        if (addSuccessfully)
            System.out.println("Successfully added");
        else
            System.out.println("Failed to add");



    }

    private List<PurchaseBuyer> doStatisticsBuyers(List<Order> orderList) {
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
                .collect(Collectors.toList());
    }


}
