package com.server.tradedoc.logic.service.impl;

import com.server.tradedoc.constants.AppConstant;
import com.server.tradedoc.logic.builder.SearchHistoryPaymentBuilder;
import com.server.tradedoc.logic.converter.HistoryPaymentConverter;
import com.server.tradedoc.logic.dto.HistoryPaymentDTO;
import com.server.tradedoc.logic.entity.CustomersEntity;
import com.server.tradedoc.logic.entity.HistoryPaymentEntity;
import com.server.tradedoc.logic.entity.ProductsEntity;
import com.server.tradedoc.logic.enums.PaymentType;
import com.server.tradedoc.logic.repository.HistoryPaymentRepository;
import com.server.tradedoc.logic.service.HistoryPaymentService;
import com.server.tradedoc.utils.BuildMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class HistoryPaymentServiceImpl implements HistoryPaymentService {

    @Autowired
    private HistoryPaymentRepository historyPaymentRepository;

    @Autowired
    private HistoryPaymentConverter historyPaymentConverter;

    @Autowired
    private BuildMapUtils buildMapUtils;

    @Override
    public Map<String, String> getAllPaymentType() {
        Map<String , String> result = new HashMap<>();
        Stream.of(PaymentType.values()).forEach(item -> {
            result.put(item.name() , item.getValue());
        });
        return result;
    }

    @Override
    public HistoryPaymentDTO save(ProductsEntity productsEntity, CustomersEntity customersEntity , PaymentType paymentType , String total) {
        HistoryPaymentEntity historyPaymentEntity = new HistoryPaymentEntity();
        historyPaymentEntity.setPaymentType(paymentType.toString());
        historyPaymentEntity.setProduct(productsEntity);
        historyPaymentEntity.setCustomer(customersEntity);
        historyPaymentEntity.setTotal(Long.parseLong(total.split("\\.")[0]));
        historyPaymentEntity.setStatus(AppConstant.statusSendFile.SUCCESS);
        historyPaymentEntity.setDeleted(AppConstant.deletedStatus.ACTIVE);
        historyPaymentEntity.setCreatedDate(Instant.now());
        historyPaymentEntity.setModifiedDate(Instant.now());
        return historyPaymentConverter.toDto(historyPaymentRepository.save(historyPaymentEntity));
    }

    @Override
    public List<HistoryPaymentDTO> getAllHistoryPayment(SearchHistoryPaymentBuilder builder, Pageable pageable) {
        Map<String , Object> parameter = buildMapUtils.buildMapSearch(builder);

        return null;
    }

}
