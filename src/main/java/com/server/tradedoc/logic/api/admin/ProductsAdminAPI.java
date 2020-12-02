package com.server.tradedoc.logic.api.admin;

import com.server.tradedoc.logic.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * ProductsAdminAPI
 *
 * @author DatDV
 *
 */
@RestController
@RequestMapping("/api/admin")
public class ProductsAdminAPI {

    @Autowired
    private ProductsService productsService;

    @RequestMapping(value = "/create-products" , method = RequestMethod.POST)
    public ResponseEntity<?> createProducts(@RequestParam("images") List<MultipartFile> images ,
                                            @RequestParam("fileProduct") MultipartFile file ,
                                            @RequestParam("avatar") MultipartFile avatar ,
                                            @RequestParam String data){
        return ResponseEntity.ok(productsService.createProduct(images , file , data , avatar));
    }

    @RequestMapping(value = "/update-products" , method = RequestMethod.PUT)
    public ResponseEntity<?> updateProducts(@RequestParam("images") List<MultipartFile> images ,
                                            @RequestParam("fileProduct") MultipartFile file ,
                                            @RequestParam("avatar") MultipartFile avatar,
                                            @RequestParam String data){
        return ResponseEntity.ok(productsService.updateProduct(images , file , data , avatar));
    }

    @RequestMapping(value = "/delete-products" , method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProducts(@RequestParam("ids") List<Long> ids){
        return ResponseEntity.ok(productsService.deleteProduct(ids));
    }

    @RequestMapping(value = "/find-one" , method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@RequestParam("id") Long id){
        return ResponseEntity.ok(productsService.getById(id));
    }

    @RequestMapping(value = "/get-product-types" , method = RequestMethod.GET)
    public ResponseEntity<Map<String , String>> getProductTypes(){
        return ResponseEntity.ok(productsService.getProductTypes());
    }
}
