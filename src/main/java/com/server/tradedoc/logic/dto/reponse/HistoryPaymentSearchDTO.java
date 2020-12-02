package com.server.tradedoc.logic.dto.reponse;

import java.time.Instant;

public class HistoryPaymentSearchDTO {

    private String paymentType;
    private Long paymentTotal;
    private Long customerId;
    private String historyStatus;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private Integer productPrice;
    private String productName;
    private String productAvatar;
    private Instant createdDate;
    private Instant modifiedDate;

    public HistoryPaymentSearchDTO() {
    }

    public HistoryPaymentSearchDTO(String paymentType, Long paymentTotal, Long customerId, String historyStatus, String customerName, String customerEmail, String customerPhone, Integer productPrice, String productName, String productAvatar, Instant createdDate, Instant modifiedDate) {
        this.paymentType = paymentType;
        this.paymentTotal = paymentTotal;
        this.customerId = customerId;
        this.historyStatus = historyStatus;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productAvatar = productAvatar;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Long getPaymentTotal() {
        return paymentTotal;
    }

    public void setPaymentTotal(Long paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getHistoryStatus() {
        return historyStatus;
    }

    public void setHistoryStatus(String historyStatus) {
        this.historyStatus = historyStatus;
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

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductAvatar() {
        return productAvatar;
    }

    public void setProductAvatar(String productAvatar) {
        this.productAvatar = productAvatar;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
