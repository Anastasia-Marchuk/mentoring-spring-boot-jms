package com.amarchuk.jms.extra.model;

public class Item {

    String name;
    int volume;
    Type type;
    double price;

    public Item(String name, int volume, Type type, double price) {
        this.name = name;
        this.volume = volume;
        this.type = type;
        this.price=price;
    }

    public Item() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    public Type getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", volume=" + volume +
                ", type=" + type +
                '}';
    }

    public void setType(Type type) {
        this.type = type;
    }
}
