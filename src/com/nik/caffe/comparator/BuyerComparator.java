package com.nik.caffe.comparator;

import com.nik.caffe.model.PurchaseBuyer;
import com.nik.caffe.model.WorkCash;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;

public class BuyerComparator implements Comparator<PurchaseBuyer> {


    @Override
    public int compare(PurchaseBuyer o1, PurchaseBuyer o2) {
        return o1.getCalories().compareTo(o2.getCalories());
    }
}
