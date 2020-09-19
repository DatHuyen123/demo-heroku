package com.demo.vnpt.repository.custom;

import com.demo.vnpt.enitty.ProductsEntity;

import java.util.List;
import java.util.Map;

public interface ProductsRepositoryCustom {
    List<ProductsEntity> findAll(Map<String , Object> searchPrams);
}
