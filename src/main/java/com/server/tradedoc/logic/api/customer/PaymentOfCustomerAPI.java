package com.server.tradedoc.logic.api.customer;

import com.server.tradedoc.logic.dto.paymentrequest.PayPalDTO;
import com.server.tradedoc.logic.dto.paymentrequest.PaymentIntentDTO;
import com.server.tradedoc.logic.service.ProductsService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class PaymentOfCustomerAPI {

    @Autowired
    private ProductsService productsService;

    @RequestMapping(value = "/paypal/make/payment" , method = RequestMethod.POST)
    public Map<String , Object> makePayment(@RequestBody PayPalDTO payPalDTO){
        return productsService.createPayment(payPalDTO);
    }

    @RequestMapping(value = "/paypal/complete/payment" , method = RequestMethod.POST)
    public Map<String , Object> completePayment(HttpServletRequest request){
        return productsService.completePayment(request.getParameter("PayerID") ,
                request.getParameter("paymentId"),
                Long.parseLong(request.getParameter("productId")));
    }

    @RequestMapping(value = "/stripe/paymentintent" , method = RequestMethod.POST)
    public ResponseEntity<?> paymentStripe(@RequestBody PaymentIntentDTO paymentIntentDTO) throws StripeException {
        PaymentIntent paymentIntent = productsService.paymentIntent(paymentIntentDTO);
        return ResponseEntity.ok(paymentIntent.toJson());
    }

    @RequestMapping(value = "/stripe/confirm" , method = RequestMethod.POST)
    public ResponseEntity<?> confirm(HttpServletRequest request) {
        Map<String , Object> response = productsService.confirm(request.getParameter("idStripe") ,
                Long.parseLong(request.getParameter("productId")));
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/stripe/cancel" , method = RequestMethod.POST)
    public ResponseEntity<?> cancel(HttpServletRequest request) {
        Map<String , Object> response = productsService.cancel(request.getParameter("idStripe"));
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/create-checkout-session" , method = RequestMethod.POST)
    public ResponseEntity<?> checkOutStripe(HttpServletRequest request) throws StripeException {
        return ResponseEntity.ok(productsService.createCheckoutSessionStripe(Long.parseLong(request.getParameter("id"))));
    }

    @RequestMapping(value = "/stripe-retrieve" , method = RequestMethod.POST)
    public ResponseEntity<?> retrieveStripe(@RequestParam("idStripe") String idStripe,
                                            @RequestParam("productId") String productId) throws StripeException{
        return ResponseEntity.ok(productsService.retrieveStripe(idStripe , Long.parseLong(productId)));
    }

}
