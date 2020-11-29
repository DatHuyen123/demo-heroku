package com.server.tradedoc.logic.dto.paymentrequest;

public class PayPalDTO {
    private String sum;
    private String current;

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }
}
