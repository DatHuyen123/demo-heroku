package com.demo.vnpt.service.impl;

import com.demo.vnpt.builder.ProductsBuilder;
import com.demo.vnpt.converter.ProductsConverter;
import com.demo.vnpt.dto.ProductsDTO;
import com.demo.vnpt.dto.response.DeleteProductsResponse;
import com.demo.vnpt.dto.response.GetProductsResponse;
import com.demo.vnpt.enitty.ProductsEntity;
import com.demo.vnpt.repository.ProductsRepository;
import com.demo.vnpt.service.ProductsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsReopsitory;

    @Autowired
    private ProductsConverter productsConverter;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductsDTO saveOrUpdate(ProductsDTO productNew) {
        ProductsEntity productsEntity = productsReopsitory.save(productsConverter.convertToEntity(productNew));
        return productsConverter.convertToDTO(productsEntity);
    }

    @Override
    public void delete(long[] ids) {
        /*List<Long> deleted = productsReopsitory.findByIdIn(productIdsForDelete);
        if(!deleted.isEmpty()){
            deleted.forEach(id -> {
                productsReopsitory.deleteById(id);
            });
        }
        DeleteProductsResponse response = new DeleteProductsResponse();
        response.setProductIdsDeleted(deleted);*/
        if(ids != null){
            for(long id : ids){
                productsReopsitory.deleteById(id);
            }
        }
    }

    @Override
    public GetProductsResponse findAll(ProductsBuilder productsBuilder) {
        Map<String , Object> properties = buildMapSearch(productsBuilder);
        List<ProductsDTO> resultDTO = productsReopsitory.findAll(properties).stream().map(pro -> modelMapper.map(pro , ProductsDTO.class)).collect(Collectors.toList());
        return new GetProductsResponse(resultDTO);
    }

    private Map<String , Object> buildMapSearch(ProductsBuilder productsBuilder){
        Map<String , Object> result = new HashMap<>();
        try{
            Field[] fields = productsBuilder.getClass().getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                if(field.get(productsBuilder) != null){
                    result.put(field.getName().toLowerCase() , field.get(productsBuilder));
                }
            }
        }catch (IllegalArgumentException | IllegalAccessException e){
            e.printStackTrace();
        }
        return result;
    }
}
