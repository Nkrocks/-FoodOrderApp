package com.nik.foodorder.Models;

public class OrdersModel {
    int orderImage;
    String soldItemName,price,orderNumber,finPrice;

    public OrdersModel(int orderImage, String soldItemName, String price, String orderNumber,String finPrice) {
        this.orderImage = orderImage;
        this.soldItemName = soldItemName;
        this.price = price;
        this.orderNumber = orderNumber;
        this.finPrice = finPrice;
    }

    public String getFinPrice() {
        return finPrice;
    }

    public void setFinPrice(String finPrice) {
        this.finPrice = price;
    }

    public OrdersModel() {

    }

    public int getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(int orderImage) {
        this.orderImage = orderImage;
    }

    public String getSoldItemName() {
        return soldItemName;
    }

    public void setSoldItemName(String soldItemName) {
        this.soldItemName = soldItemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
