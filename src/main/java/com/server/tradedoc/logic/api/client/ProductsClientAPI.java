package com.server.tradedoc.logic.api.client;

import com.google.gson.Gson;
import com.server.tradedoc.logic.dto.paymentrequest.PayPalDTO;
import com.server.tradedoc.logic.dto.paymentrequest.PayPalRequest;
import com.server.tradedoc.logic.dto.paymentrequest.PaymentIntentDTO;
import com.server.tradedoc.logic.service.ProductsService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/public/api-client")
public class ProductsClientAPI {

    @Autowired
    private ProductsService productsService;

    @RequestMapping(value = "/get-all-products" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllProducts(@RequestParam("categoryIds") List<Long> categoryIds) throws URISyntaxException {
        return ResponseEntity.ok(productsService.getAllProducts(categoryIds));
    }

    @RequestMapping(value = "/find-one" , method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@RequestParam("id") Long id){
        return ResponseEntity.ok(productsService.getById(id));
    }

}
