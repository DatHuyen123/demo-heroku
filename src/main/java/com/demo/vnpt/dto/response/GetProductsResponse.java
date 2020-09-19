package com.demo.vnpt.dto.response;

import com.demo.vnpt.dto.ProductsDTO;

import java.util.List;

public class GetProductsResponse {
    private List<ProductsDTO> productsList;

    public GetProductsResponse(List<ProductsDTO> productsList) {
        this.productsList = productsList;
    }

    public List<ProductsDTO> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<ProductsDTO> productsList) {
        this.productsList = productsList;
    }
}
