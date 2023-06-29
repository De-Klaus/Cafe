package com.nik.caffe.scheduler;

import com.nik.caffe.model.Cashbox;
import com.nik.caffe.model.Order;
import com.nik.caffe.model.WorkCash;
import com.nik.caffe.service.HistoryOrderService;
import com.nik.caffe.util.FileUtil;
import com.nik.caffe.util.LockUtil;

import java.nio.file.Path;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

import static com.nik.caffe.Params.CsvParams.CASHBOX_DIR;

public class CashboxStatisticsScheduler extends TimerTask {

    private final HistoryOrderService historyOrdersService;

    public CashboxStatisticsScheduler(HistoryOrderService historyOrdersService) {
        this.historyOrdersService = historyOrdersService;
    }

    @Override
    public void run() {
        System.out.println("CashboxStatisticsScheduler: "+ Thread.currentThread().getName());

        // TODO получаем историю всех заказов
        List<Order> orderAllList = LockUtil.getAllOrders(historyOrdersService);

        // TODO статистику по всем кассам
        List<WorkCash> statisticsCash = doStatisticsCash(orderAllList);

        // TODO записываем в файл

        Path path = FileUtil.createPath(CASHBOX_DIR);
        boolean addSuccessfully = FileUtil.writeFile(path,statisticsCash);
        if (addSuccessfully)
            System.out.println("Successfully added");
        else
            System.out.println("Failed to add");


    }

    private List<WorkCash> doStatisticsCash(List<Order> orderList){
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
                .collect(Collectors.toList());
    }

}
