package com.demo.vnpt.api;

import com.demo.vnpt.builder.ProductsBuilder;
import com.demo.vnpt.dto.ProductsDTO;
import com.demo.vnpt.dto.response.GetProductsResponse;
import com.demo.vnpt.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/products")
public class ProductsAPI {

    @Autowired
    private ProductsService productsService;

    @PostMapping(value = "/create-or-update")
    public ProductsDTO createProducts(@RequestBody ProductsDTO productNew){
        return productsService.saveOrUpdate(productNew);
    }

    @DeleteMapping(value = "/delete-products/{id}")
    public void deleteProducts(@PathVariable("id") long[] ids){
        productsService.delete(ids);
    }

    @GetMapping(value = "/get-all")
    public GetProductsResponse getAll(@RequestParam Map<String , String> model){
        return productsService.findAll(initProductsBuilder(model));
    }

    private ProductsBuilder initProductsBuilder(Map<String , String> model){
        ProductsBuilder productsBuilder =new ProductsBuilder.Builder()
                .setName(model.get("name"))
                .setNote(model.get("note"))
                .setId(model.get("id") != null ? Long.parseLong(model.get("id")) : null)
                .builder();
        return productsBuilder;
    }
}
