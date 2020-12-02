package com.server.tradedoc.logic.service;

import com.server.tradedoc.logic.builder.SearchHistoryPaymentBuilder;
import com.server.tradedoc.logic.dto.HistoryPaymentDTO;
import com.server.tradedoc.logic.dto.reponse.HistoryPaymentSearchDTO;
import com.server.tradedoc.logic.entity.CustomersEntity;
import com.server.tradedoc.logic.entity.ProductsEntity;
import com.server.tradedoc.logic.enums.PaymentType;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface HistoryPaymentService {
    Map<String , String> getAllPaymentType();
    HistoryPaymentDTO save(ProductsEntity productsEntity , CustomersEntity customersEntity , PaymentType paymentType , String total);
    List<HistoryPaymentSearchDTO> getAllHistoryPayment(SearchHistoryPaymentBuilder builder , Pageable pageable);
}
