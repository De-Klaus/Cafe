package com.nik.caffe.enams;

import com.nik.caffe.Params;
import com.nik.caffe.util.RandomUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public enum Menu {
    IT_STEAK("steak", BigDecimal.valueOf(500), BigDecimal.valueOf(10)),
    LEGACY_SALAD("salad", BigDecimal.valueOf(50), BigDecimal.valueOf(5)),
    SWITCH_POTATOES("potatoes", BigDecimal.valueOf(300), BigDecimal.valueOf(3)),
    DEBUG_COLA("cola", BigDecimal.valueOf(25), BigDecimal.valueOf(2)),
    ICE_CREAM_SCRIPT("cream", BigDecimal.valueOf(150), BigDecimal.valueOf(4));

    private final String name;
    private final BigDecimal calories;
    private final BigDecimal price;

    Menu(String name, BigDecimal calories, BigDecimal price) {
        this.name = name;
        this.calories = calories;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCalories() {
        return calories;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static Menu getByItemMenu(String item){
        for (Menu value : Menu.values()){
            if (value.getName().equals(item)){
                return value;
            }
        }
        return null;
    }

    public static List<Menu> getRandomOrder() {
        List<List<Menu>> orders = new ArrayList<>();
        orders.add(List.of(IT_STEAK,LEGACY_SALAD,LEGACY_SALAD));
        orders.add(List.of(DEBUG_COLA));
        orders.add(List.of(ICE_CREAM_SCRIPT,SWITCH_POTATOES,DEBUG_COLA,DEBUG_COLA));
        orders.add(List.of(LEGACY_SALAD));
        orders.add(List.of(LEGACY_SALAD,SWITCH_POTATOES,DEBUG_COLA,ICE_CREAM_SCRIPT));
        return orders.get(RandomUtil.getRandom(Params.COUNT_GENERATED_ORDER));
    }
}
