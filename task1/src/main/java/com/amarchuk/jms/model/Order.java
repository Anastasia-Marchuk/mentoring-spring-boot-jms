package com.amarchuk.jms.model;

public class Order {

    int id;
    User user;
    Item item;

    public Order(int id, User user, Item item) {
        this.id = id;
        this.user = user;
        this.item = item;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user.toString() +
                ", item=" + item.toString() +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
