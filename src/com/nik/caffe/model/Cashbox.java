package com.nik.caffe.model;

public class Cashbox {

    private static Integer generator = 1;
    private Integer id;
    private String name;

    public Cashbox() {
        this.id = generator++;
        this.name = "CASHBOX"+this.id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Cashbox{" +
                "name='" + name + '\'' +
                '}';
    }
}
