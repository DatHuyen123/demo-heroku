package com.server.tradedoc.logic.service;

import com.server.tradedoc.logic.dto.ProductsDTO;
import com.server.tradedoc.logic.dto.paymentrequest.PayPalDTO;
import com.server.tradedoc.logic.dto.paymentrequest.PaymentIntentDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * ProductsService
 *
 * @author DatDV
 */
public interface ProductsService {
    List<ProductsDTO> getAllProducts() throws URISyntaxException;
    Long createProduct(List<MultipartFile> imageFiles , MultipartFile file , String data , MultipartFile avatar);
    Long updateProduct(List<MultipartFile> imageFiles , MultipartFile file , String data , MultipartFile avatar);
    List<Long> deleteProduct(List<Long> ids);
    Map<String , Object> createPayment(PayPalDTO payPalDTO);
    Map<String, Object> completePayment(String payerId , String paymentId , Long productId);
    ProductsDTO getById(Long id);
    PaymentIntent paymentIntent(PaymentIntentDTO paymentIntentDto) throws StripeException;
    Map<String , Object> confirm(String id, Long productId) ;
    Map<String , Object> cancel(String id) ;
    Map<String , String> getProductTypes();
}
