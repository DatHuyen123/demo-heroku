package com.server.tradedoc.logic.service.impl;

import com.google.gson.Gson;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.server.tradedoc.constants.AppConstant;
import com.server.tradedoc.logic.converter.ProductsConverter;
import com.server.tradedoc.logic.dto.HistoryPaymentDTO;
import com.server.tradedoc.logic.dto.ProductsDTO;
import com.server.tradedoc.logic.dto.paymentrequest.ChargeRequest;
import com.server.tradedoc.logic.dto.paymentrequest.PayPalDTO;
import com.server.tradedoc.logic.dto.paymentrequest.PayPalRequest;
import com.server.tradedoc.logic.dto.paymentrequest.PaymentIntentDTO;
import com.server.tradedoc.logic.dto.reponse.MessageSuccess;
import com.server.tradedoc.logic.entity.CategoryEntity;
import com.server.tradedoc.logic.entity.CustomersEntity;
import com.server.tradedoc.logic.entity.ImageEntity;
import com.server.tradedoc.logic.entity.ProductsEntity;
import com.server.tradedoc.logic.enums.PayPalPaymentIntent;
import com.server.tradedoc.logic.enums.PayPalPaymentMethod;
import com.server.tradedoc.logic.enums.PaymentType;
import com.server.tradedoc.logic.repository.*;
import com.server.tradedoc.logic.service.HistoryPaymentService;
import com.server.tradedoc.logic.service.ProductsService;
import com.server.tradedoc.utils.CommonUtils;
import com.server.tradedoc.utils.FilesUtils;
import com.server.tradedoc.utils.MailUtils;
import com.server.tradedoc.utils.error.CustomException;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ProductsServiceImpl
 *
 * @author DatDV
 */
@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private Gson gson;

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private ProductsConverter productsConverter;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private HistoryPaymentService historyPaymentService;

    @Autowired
    private FilesUtils filesUtils;

    @Autowired
    private APIContext apiContext;

    @Value("${stripe.public.key}")
    private String secretKeyStripe;

    @Override
    public List<ProductsDTO> getAllProducts() throws URISyntaxException {
        List<ProductsEntity> entitys = productsRepository.findAll();
        List<ProductsDTO> result = new ArrayList<>();
        if (!entitys.isEmpty()) {
            for (ProductsEntity productsEntity : entitys) {
                List<ImageEntity> imageEntities = new ArrayList<>();
                for (ImageEntity imageEntity : productsEntity.getImages()) {
                    imageEntity.setPathFile(filesUtils.genFilePath(imageEntity.getPathFile()));
                    imageEntities.add(imageEntity);
                }
                productsEntity.setImages(imageEntities);
                ProductsDTO productsDTO = productsConverter.toDto(productsEntity);
                result.add(productsDTO);
            }
        }
        return result;
    }

    @Override
    @Transactional
    public Long createProduct(List<MultipartFile> imageFiles, MultipartFile file, String data) {
        if (file == null) {
            throw new CustomException("file undefined", CommonUtils.putError("file", "ERR_0034"));
        }
        if (imageFiles.isEmpty()) {
            throw new CustomException("image undefined", CommonUtils.putError("imageFiles", "ERR_0034"));
        }
        ProductsDTO productsDTO = gson.fromJson(data, ProductsDTO.class);
        if (productsDTO.getCategoryIds() == null || productsDTO.getCategoryIds().isEmpty()) {
            throw new CustomException("category not null", CommonUtils.putError("data", "ERR_0034"));
        }
        ProductsEntity productsEntity = productsConverter.toEntity(productsDTO);
        productsEntity.setPathFile(filesUtils.save(file, "/fileproducts/", filesUtils.generateFileName(file.getOriginalFilename())));
        List<CategoryEntity> categoryEntities = categoryRepository.findCategoryEntitiesByIdIn(productsDTO.getCategoryIds());
        productsEntity.setCategorys(categoryEntities);
        ProductsEntity productsEntityAfterInsert = productsRepository.save(productsEntity);
        for (MultipartFile image : imageFiles) {
            String nameFileServer = filesUtils.generateFileName(image.getOriginalFilename());
            String fileName = filesUtils.save(image, "/thumbnail/", nameFileServer);
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setName(nameFileServer);
            imageEntity.setPathFile(fileName);
            imageEntity.setProducts(productsEntityAfterInsert);
            imageRepository.save(imageEntity);
        }
        return productsEntity.getId();
    }

    @Override
    @Transactional
    public Long updateProduct(List<MultipartFile> imageFiles, MultipartFile file, String data) {
        if (file == null) {
            throw new CustomException("file undefined", CommonUtils.putError("file", "ERR_0034"));
        }
        if (imageFiles.isEmpty()) {
            throw new CustomException("image undefined", CommonUtils.putError("imageFiles", "ERR_0034"));
        }
        ProductsDTO productsDTO = gson.fromJson(data, ProductsDTO.class);
        if (productsDTO.getCategoryIds() == null || productsDTO.getCategoryIds().isEmpty()) {
            throw new CustomException("category not null", CommonUtils.putError("data", "ERR_0034"));
        }
        ProductsEntity productsEntity = productsConverter.toEntity(productsDTO);
        productsEntity.setPathFile(filesUtils.save(file, "/fileproducts/", filesUtils.generateFileName(file.getOriginalFilename())));
        List<CategoryEntity> categoryEntities = categoryRepository.findCategoryEntitiesByIdIn(productsDTO.getCategoryIds());
        productsEntity.setCategorys(categoryEntities);
        ProductsEntity productsEntityAfterUpdate = productsRepository.save(productsEntity);
        for (MultipartFile image : imageFiles) {
            String nameFileServer = filesUtils.generateFileName(image.getOriginalFilename());
            String fileName = filesUtils.save(image, "/thumbnail/", nameFileServer);
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setName(nameFileServer);
            imageEntity.setPathFile(fileName);
            imageEntity.setProducts(productsEntityAfterUpdate);
            imageRepository.save(imageEntity);
        }
        return productsEntity.getId();
    }

    @Override
    @Transactional
    public List<Long> deleteProduct(List<Long> ids) {
        for (Long id : ids) {
            ProductsEntity productsEntity = productsRepository.findById(id).get();
            commentRepository.deleteCommentsEntityByProducts(productsEntity);
            imageRepository.deleteImageEntityByProducts(productsEntity);
            productsRepository.deleteById(id);
        }
        return ids;
    }

    @Override
    public Map<String, Object> createPayment(PayPalDTO payPalDTO) {
        Map<String, Object> response = new HashMap<String, Object>();

        Amount amount = new Amount();
        amount.setCurrency(payPalDTO.getCurrent());
        amount.setTotal(payPalDTO.getSum());
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(PayPalPaymentMethod.paypal.toString());
        Payment payment = new Payment();
        payment.setIntent(PayPalPaymentIntent.sale.toString());
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:3000/checkout/cancel");
        redirectUrls.setReturnUrl("http://localhost:3000/checkout/success");
        payment.setRedirectUrls(redirectUrls);
        Payment createdPayment;

        try {
            String redirectUrl = "";
            createdPayment = payment.create(apiContext);
            if (createdPayment != null) {
                List<Links> links = createdPayment.getLinks();
                for (Links link : links) {
                    if (link.getRel().equals("approval_url")) {
                        redirectUrl = link.getHref();
                        break;
                    }
                }
                response.put("status", "success");
                response.put("redirect_url", redirectUrl);
            }
        } catch (PayPalRESTException e) {
            return null;
        }

        return response;
    }

    @Override
    public Map<String, Object> completePayment(String payerId, String paymentId, Long productId, Long customerId) {
        Map<String, Object> response = new HashMap<>();
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        try {
            Payment createdPayment = payment.execute(apiContext, paymentExecution);
            if (createdPayment != null) {
                response.put("status", "success");
                ProductsEntity productsEntity = productsRepository.findById(productId).get();
                CustomersEntity customersEntity = customersRepository.findById(customerId).get();
                if (productsEntity.getId() != null && customersEntity.getId() != null) {
                    String template = "file for you: \n";
                    String subject = "WE SEND File To YOU";
                    Boolean resultSendMail = mailUtils.sendFileToMail(template, customersEntity.getEmail(), subject, productsEntity.getPathFile());
                    if (!resultSendMail) {
                        throw new CustomException("cannot send email", CommonUtils.putError("email", "ERR_0013"));
                    }
                    HistoryPaymentDTO historyPaymentDTO = historyPaymentService.save(productsEntity, customersEntity, PaymentType.PAY_PAL, createdPayment.getTransactions().get(0).getAmount().getTotal());
                    response.put("status_history", historyPaymentDTO.getStatus() == 1 ? "success_send" : "error_send");
                } else {
                    if (productsEntity.getId() == null) {
                        throw new CustomException("cannot find product", CommonUtils.putError("productId", "ERR_0034"));
                    }
                    if (customersEntity.getId() == null) {
                        throw new CustomException("cannot find customer", CommonUtils.putError("customerId", "ERR_0034"));
                    }
                }
            }
        } catch (PayPalRESTException e) {
            throw new CustomException("error paypal", CommonUtils.putError("paypal", e.getMessage()));
        }
        return response;
    }

    @Override
    public ProductsDTO getById(Long id) {
        ProductsDTO[] response = {null};
        productsRepository.findById(id).ifPresent(productsEntity -> {
            response[0] = productsConverter.toDto(productsEntity);
        });
        return response[0];
    }

    @Override
    public PaymentIntent paymentIntent(PaymentIntentDTO paymentIntentDto) throws StripeException {
        Stripe.apiKey = secretKeyStripe;
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");
        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentIntentDto.getAmount());
        params.put("currency", paymentIntentDto.getCurrency());
        params.put("description", paymentIntentDto.getDescription());
        params.put("payment_method_types", paymentMethodTypes);
        return PaymentIntent.create(params);
    }

    @Override
    public Map<String, Object> confirm(String id, Long productId, Long customerId) {
        Map<String, Object> response = new HashMap<>();
        Stripe.apiKey = secretKeyStripe;
        try {
            PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
            Map<String, Object> params = new HashMap<>();
            params.put("payment_method", "pm_card_visa");
            paymentIntent.confirm(params);
            response.put("status", "success");
            ProductsEntity productsEntity = productsRepository.findById(productId).get();
            CustomersEntity customersEntity = customersRepository.findById(customerId).get();
            if (productsEntity.getId() != null && customersEntity.getId() != null) {
                String template = "file for you: \n";
                String subject = "WE SEND File To YOU";
                Boolean resultSendMail = mailUtils.sendFileToMail(template, customersEntity.getEmail(), subject, productsEntity.getPathFile());
                if (!resultSendMail) {
                    throw new CustomException("cannot send email", CommonUtils.putError("email", "ERR_0013"));
                }
                HistoryPaymentDTO historyPaymentDTO = historyPaymentService.save(productsEntity, customersEntity, PaymentType.VISA, paymentIntent.getAmount().toString());
                response.put("status_history", historyPaymentDTO.getStatus() == 1 ? "success_send" : "error_send");
            } else {
                if (productsEntity.getId() == null) {
                    throw new CustomException("cannot find product", CommonUtils.putError("productId", "ERR_0034"));
                }
                if (customersEntity.getId() == null) {
                    throw new CustomException("cannot find customer", CommonUtils.putError("customerId", "ERR_0034"));
                }
            }
        } catch (StripeException ex) {
            throw new CustomException("error stripe", CommonUtils.putError("id", ex.getMessage()));
        }
        return response;
    }

    @Override
    public Map<String, Object> cancel(String id) {
        Map<String, Object> result = new HashMap<>();
        Stripe.apiKey = secretKeyStripe;
        try {
            PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
            paymentIntent.cancel();
            result.put("status", "cancel_success");
        } catch (StripeException ex) {
            throw new CustomException("error cancel stripe", CommonUtils.putError("id", ex.getMessage()));
        }
        return result;
    }
}