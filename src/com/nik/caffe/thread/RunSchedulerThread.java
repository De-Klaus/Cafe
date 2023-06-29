package com.nik.caffe.thread;

import com.nik.caffe.Params;
import com.nik.caffe.scheduler.BuyerStatisticsScheduler;
import com.nik.caffe.scheduler.CashboxStatisticsScheduler;
import com.nik.caffe.scheduler.ComparisonIndicatorsScheduler;
import com.nik.caffe.service.HistoryOrderService;

import java.util.Timer;

public class RunSchedulerThread implements Runnable {

    private final HistoryOrderService historyOrdersService;
    private static Timer timer;

    public RunSchedulerThread(HistoryOrderService historyOrdersService) {
        this.historyOrdersService = historyOrdersService;
        this.timer = new Timer();
    }

    @Override
    public void run() {
        System.out.println("RunSchedulerThread: "+ Thread.currentThread().getName());
        // TODO: Статистика по покупателям
        timer.scheduleAtFixedRate(new CashboxStatisticsScheduler(historyOrdersService),50, Params.LaunchPeriod.TIME_CASHBOX);
        // TODO: Статистика по кассам
        timer.scheduleAtFixedRate(new BuyerStatisticsScheduler(historyOrdersService),5000, Params.LaunchPeriod.TIME_BUYER);
        // TODO: Статистика по максимумам покупателей и касиров
        timer.scheduleAtFixedRate(new ComparisonIndicatorsScheduler(historyOrdersService),50000, Params.LaunchPeriod.TIME_COMPARISON);
    }
}
