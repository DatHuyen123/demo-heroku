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
import java.util.Map;

@RestController
@RequestMapping("/public/api-client")
public class ProductsClientAPI {

    @Autowired
    private ProductsService productsService;

    @RequestMapping(value = "/get-all-products" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllProducts() throws URISyntaxException {
        return ResponseEntity.ok(productsService.getAllProducts());
    }

    @RequestMapping(value = "/find-one" , method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@RequestParam("id") Long id){
        return ResponseEntity.ok(productsService.getById(id));
    }

    @RequestMapping(value = "/paypal/make/payment" , method = RequestMethod.POST)
    public Map<String , Object> makePayment(@RequestBody PayPalDTO payPalDTO){
        return productsService.createPayment(payPalDTO);
    }

    @RequestMapping(value = "/paypal/complete/payment" , method = RequestMethod.POST)
    public Map<String , Object> completePayment(HttpServletRequest request){
        return productsService.completePayment(request.getParameter("PayerID") ,
                                                request.getParameter("paymentId"),
                                                Long.parseLong(request.getParameter("productId")),
                                                Long.parseLong(request.getParameter("customerId")));
    }

    @RequestMapping(value = "/stripe/paymentintent" , method = RequestMethod.POST)
    public ResponseEntity<?> paymentStripe(@RequestBody PaymentIntentDTO paymentIntentDTO) throws StripeException {
        PaymentIntent paymentIntent = productsService.paymentIntent(paymentIntentDTO);
        return ResponseEntity.ok(paymentIntent.toJson());
    }

    @RequestMapping(value = "/stripe/confirm" , method = RequestMethod.POST)
    public ResponseEntity<?> confirm(HttpServletRequest request) {
        Map<String , Object> response = productsService.confirm(request.getParameter("idStripe") ,
                                                                Long.parseLong(request.getParameter("productId")) ,
                                                                Long.parseLong(request.getParameter("customerId")));
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/stripe/cancel" , method = RequestMethod.POST)
    public ResponseEntity<?> cancel(HttpServletRequest request) {
        Map<String , Object> response = productsService.cancel(request.getParameter("idStripe"));
        return ResponseEntity.ok(response);
    }
}
