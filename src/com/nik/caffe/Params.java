package com.nik.caffe;

public interface Params {

    Integer COUNT_GENERATED_ORDER = 4;
    Integer DEFAULT_BOUND = 4;
    Integer NUMBER_BUYERS = 1000;

    interface LaunchPeriod {
        Long TIME_CASHBOX = 60L*1000;
        Long TIME_BUYER = 120L*1000;
        Long TIME_COMPARISON = 3600L*1000;
    }

    interface CsvParams {
        String HEAD_DIR = "statistics";
        String SYSTEM_DIR = "system";
        String BUYER_DIR = "buyers";
        String CASHBOX_DIR = "cash";
        String COMPARISON_DIR = "comparisons";
        String BUYER_FILE_NAME = "buyer";
        String CASHBOX_FILE_NAME = "cashbox";
        String COMPARISON_FILE_NAME = "comparisonIndicators";
        String FORMAT_FILE = ".csv";
    }
}
