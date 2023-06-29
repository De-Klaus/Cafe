package com.nik.caffe.model;

public class Buyer {

    private int id;
    private String name;

    public Buyer(int id) {
        this.id = id;
        this.name = "BUYER"+this.id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name += name;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "name='" + name + '\'' +
                '}';
    }
}
