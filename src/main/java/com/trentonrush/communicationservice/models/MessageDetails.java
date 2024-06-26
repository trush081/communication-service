package com.trentonrush.communicationservice.models;

import com.trentonrush.communicationservice.models.strapi.Product;

import java.math.BigDecimal;
import java.util.List;

public class MessageDetails {
    private String subject;
    private String content;
    private String orderId;
    private String customerName;
    private String customerEmail;
    private String orderStatus;
    private BigDecimal totalAmount;
    private List<Product> products;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "MessageDetails{" +
                "subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
