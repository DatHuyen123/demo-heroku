package com.demo.vnpt.dto.request;

import java.util.List;

public class ProductsForDelete {
    private List<Long> productsForDelete;

    public List<Long> getProductsForDelete() {
        return productsForDelete;
    }

    public void setProductsForDelete(List<Long> productsForDelete) {
        this.productsForDelete = productsForDelete;
    }
}
