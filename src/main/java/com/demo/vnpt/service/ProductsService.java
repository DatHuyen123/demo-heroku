package com.demo.vnpt.service;

import com.demo.vnpt.builder.ProductsBuilder;
import com.demo.vnpt.dto.ProductsDTO;
import com.demo.vnpt.dto.response.DeleteProductsResponse;
import com.demo.vnpt.dto.response.GetProductsResponse;

import java.util.List;

public interface ProductsService {
    ProductsDTO saveOrUpdate(ProductsDTO productNew);
    void delete(long[] ids);
    GetProductsResponse findAll(ProductsBuilder productsBuilder);
}
