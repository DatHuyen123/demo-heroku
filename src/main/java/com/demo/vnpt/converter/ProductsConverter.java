package com.demo.vnpt.converter;

import com.demo.vnpt.dto.ProductsDTO;
import com.demo.vnpt.enitty.ProductsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductsConverter {

    @Autowired
    private ModelMapper modelMapper;

    public ProductsDTO convertToDTO(ProductsEntity productsEntity){
        return modelMapper.map(productsEntity , ProductsDTO.class);
    }

    public ProductsEntity convertToEntity(ProductsDTO productsDTO){
        return modelMapper.map(productsDTO , ProductsEntity.class);
    }
}
