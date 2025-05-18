//package com.tcs.inventory.entity;
//
//
//public class OrderItem {
//    private String productId;
//    private int quantity;
//    private double unitPrice;
//
//    public OrderItem(String productId, int quantity, double unitPrice) {
//        this.productId = productId;
//        this.quantity = quantity;
//        this.unitPrice = unitPrice;
//    }
//
//    public String getProductId() {
//        return productId;
//    }
//
//    public void setProductId(String productId) {
//        this.productId = productId;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public double getUnitPrice() {
//        return unitPrice;
//    }
//
//    public void setUnitPrice(double unitPrice) {
//        this.unitPrice = unitPrice;
//    }
//
//    public double getTotal() {
//        return quantity * unitPrice;
//    }
//
//    @Override
//    public String toString() {
//        return "OrderItem{" +
//                "productId='" + productId + '\'' +
//                ", quantity=" + quantity +
//                ", unitPrice=" + unitPrice +
//                '}';
//    }
//}