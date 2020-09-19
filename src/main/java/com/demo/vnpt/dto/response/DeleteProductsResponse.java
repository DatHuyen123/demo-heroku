package com.demo.vnpt.dto.response;

import java.util.List;

public class DeleteProductsResponse {
    private List<Long> productIdsDeleted;

    public DeleteProductsResponse(List<Long> productIdsDeleted) {
        this.productIdsDeleted = productIdsDeleted;
    }

    public List<Long> getProductIdsDeleted() {
        return productIdsDeleted;
    }

    public void setProductIdsDeleted(List<Long> productIdsDeleted) {
        this.productIdsDeleted = productIdsDeleted;
    }
}
