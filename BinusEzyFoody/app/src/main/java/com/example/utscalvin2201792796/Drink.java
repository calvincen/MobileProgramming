package com.example.utscalvin2201792796;

import java.io.Serializable;

public class Drink implements Serializable {
    private String name;
    private int price;
    private int image;
    private int qty;

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Drink(String name, int price, int image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Drink(String name, int price, int image, int qty) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.qty = qty;
    }
}
