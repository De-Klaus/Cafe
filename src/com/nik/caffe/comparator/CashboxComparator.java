package com.nik.caffe.comparator;

import com.nik.caffe.model.WorkCash;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;

public class CashboxComparator implements Comparator<WorkCash> {


    @Override
    public int compare(WorkCash o1, WorkCash o2) {
        BigDecimal lo1 = getAverageValue(o1);
        BigDecimal lo2 = getAverageValue(o2);
        return lo1.compareTo(lo2);
    }

    /**
     * Получение среднего значения продаж касс
     * @param o
     * @return
     */
    private BigDecimal getAverageValue(WorkCash o) {
        BigDecimal allSum = o.getAllSum();
        BigDecimal count = new BigDecimal(o.getCountOrders());
        return allSum.divide(count, 3, RoundingMode.CEILING);
    }
}
