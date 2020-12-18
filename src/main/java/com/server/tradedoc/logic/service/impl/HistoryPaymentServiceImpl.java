package com.server.tradedoc.logic.service.impl;

import com.server.tradedoc.constants.AppConstant;
import com.server.tradedoc.logic.builder.SearchHistoryPaymentBuilder;
import com.server.tradedoc.logic.converter.HistoryPaymentConverter;
import com.server.tradedoc.logic.dto.HistoryPaymentDTO;
import com.server.tradedoc.logic.dto.reponse.CountResponse;
import com.server.tradedoc.logic.dto.reponse.HistoryPaymentSearchDTO;
import com.server.tradedoc.logic.entity.HistoryPaymentEntity;
import com.server.tradedoc.logic.entity.ProductsEntity;
import com.server.tradedoc.logic.entity.UserEntity;
import com.server.tradedoc.logic.enums.PaymentType;
import com.server.tradedoc.logic.repository.HistoryPaymentRepository;
import com.server.tradedoc.logic.service.HistoryPaymentService;
import com.server.tradedoc.utils.FilesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URISyntaxException;
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
    private FilesUtils filesUtils;

    @Override
    public Map<String, String> getAllPaymentType() {
        Map<String, String> result = new HashMap<>();
        Stream.of(PaymentType.values()).forEach(item -> {
            result.put(item.name(), item.getValue());
        });
        return result;
    }

    @Override
    @Transactional
    public HistoryPaymentDTO save(ProductsEntity productsEntity, UserEntity userEntity, PaymentType paymentType, String total) {
        HistoryPaymentEntity historyPaymentEntity = new HistoryPaymentEntity();
        historyPaymentEntity.setPaymentType(paymentType.toString());
        historyPaymentEntity.setProduct(productsEntity);
        historyPaymentEntity.setCustomer(userEntity);
        historyPaymentEntity.setTotal(Long.parseLong(total.split("\\.")[0]));
        historyPaymentEntity.setStatus(AppConstant.statusSendFile.SUCCESS);
        historyPaymentEntity.setDeleted(AppConstant.deletedStatus.ACTIVE);
        historyPaymentEntity.setCreatedDate(Instant.now());
        historyPaymentEntity.setModifiedDate(Instant.now());
        return historyPaymentConverter.toDto(historyPaymentRepository.save(historyPaymentEntity));
    }

    @Override
    public List<HistoryPaymentSearchDTO> getAllHistoryPayment(SearchHistoryPaymentBuilder builder, Pageable pageable) {
        List<HistoryPaymentSearchDTO> result = historyPaymentRepository.findAllHistoryPayment(builder, pageable);
        result.forEach(item -> {
            try {
                item.setProductAvatar(filesUtils.genFilePath(item.getProductAvatar()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });
        return result;
    }

    @Override
    public CountResponse countByCondition(SearchHistoryPaymentBuilder builder) {
        CountResponse result = new CountResponse();
        result.setCountItem(historyPaymentRepository.countByCondition(builder));
        return result;
    }

}
