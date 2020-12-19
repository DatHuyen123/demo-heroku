package com.server.tradedoc.logic.api.admin;

import com.server.tradedoc.logic.builder.SearchProductBuilder;
import com.server.tradedoc.logic.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<?> createProducts(@RequestParam("fileProduct") MultipartFile file ,
                                            @RequestParam("avatar") MultipartFile avatar ,
                                            @RequestParam String data){
        return ResponseEntity.ok(productsService.createProduct(file , data , avatar));
    }

    @RequestMapping(value = "/update-products" , method = RequestMethod.PUT)
    public ResponseEntity<?> updateProducts(@RequestParam("fileProduct") MultipartFile file ,
                                            @RequestParam("avatar") MultipartFile avatar,
                                            @RequestParam String data){
        return ResponseEntity.ok(productsService.updateProduct(file , data , avatar));
    }

    @RequestMapping(value = "/delete-products" , method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProducts(@RequestParam("ids") List<Long> ids){
        return ResponseEntity.ok(productsService.deleteProduct(ids));
    }

    @RequestMapping(value = "/find-all-product-by-condition" , method = RequestMethod.GET)
    public ResponseEntity<?> findAllProductByCondition(@RequestParam Map<String , String> model){
        SearchProductBuilder builder = initSearchProductBuilder(model);
        Pageable pageable = PageRequest.of(Integer.parseInt(model.get("page")) - 1 , Integer.parseInt(model.get("maxPageItem")));
        return ResponseEntity.ok(productsService.getProductByCondition(builder , pageable));
    }

    @RequestMapping(value = "/count-product-by-condition" , method = RequestMethod.GET)
    public ResponseEntity<?> countProductByCondition(@RequestParam Map<String , String> model){
        SearchProductBuilder builder = initSearchProductBuilder(model);
        return ResponseEntity.ok(productsService.count(builder));
    }

    @RequestMapping(value = "/find-one-product" , method = RequestMethod.GET)
    public ResponseEntity<?> findOneProduct(@RequestParam("id") Long id){
        return ResponseEntity.ok(productsService.getById(id));
    }

    @RequestMapping(value = "/get-product-types" , method = RequestMethod.GET)
    public ResponseEntity<Map<String , String>> getProductTypes(){
        return ResponseEntity.ok(productsService.getProductTypes());
    }

    private SearchProductBuilder initSearchProductBuilder(Map<String , String> model){
        SearchProductBuilder builder = new SearchProductBuilder.builder()
                .setCategoryName(model.get("categoryName"))
                .setPriceForm(model.get("priceForm") != "" ? Integer.parseInt(model.get("priceForm")) : null)
                .setPriceTo(model.get("priceTo") != "" ? Integer.parseInt(model.get("priceTo")) : null)
                .setProductName(model.get("productName"))
                .setProductType(model.get("productType"))
                .builder();
        return builder;
    }
}
