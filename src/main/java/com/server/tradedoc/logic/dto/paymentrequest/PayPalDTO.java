package com.server.tradedoc.logic.dto.paymentrequest;

public class PayPalDTO {
    private Long productId;
    private String current;

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
