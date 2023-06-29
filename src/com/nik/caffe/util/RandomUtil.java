package com.nik.caffe.util;

import com.nik.caffe.Params;

import java.util.Random;

public class RandomUtil {
    private static final Random RANDOM = new Random();

    private RandomUtil() {
    }

    public static int getRandom() {
        return RANDOM.nextInt(Params.DEFAULT_BOUND);
    }

    public static int getRandom(int bound) {
        return RANDOM.nextInt(bound);
    }

}
