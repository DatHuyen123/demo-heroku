package com.server.tradedoc.logic.enums;

public enum ProductCollection {
    EXPERT("experts"),
    INDICATORS("indicators");

    private String value;

    ProductCollection(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
