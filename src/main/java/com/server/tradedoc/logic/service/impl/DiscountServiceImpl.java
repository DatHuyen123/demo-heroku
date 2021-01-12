package com.server.tradedoc.logic.service.impl;

import com.server.tradedoc.constants.AppConstant;
import com.server.tradedoc.logic.converter.DiscountConverter;
import com.server.tradedoc.logic.dto.DiscountDTO;
import com.server.tradedoc.logic.dto.reponse.CreatedResponse;
import com.server.tradedoc.logic.dto.reponse.DiscountClientResponse;
import com.server.tradedoc.logic.dto.reponse.NotificationDiscount;
import com.server.tradedoc.logic.entity.DiscountEntity;
import com.server.tradedoc.logic.repository.DiscountRepository;
import com.server.tradedoc.logic.service.DiscountService;
import com.server.tradedoc.utils.CommonUtils;
import com.server.tradedoc.utils.DateTimeUtils;
import com.server.tradedoc.utils.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

/**
 * DiscountServiceImpl
 *
 * @author DatDV
 */
@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private DiscountConverter discountConverter;

    /**
     * create
     *
     * @param discountDTO
     * @return CreatedResponse {com.server.tradedoc.logic.dto.reponse}
     */
    @Override
    @Transactional
    public CreatedResponse create(DiscountDTO discountDTO) {
        if (discountDTO.getCode().equals("")) {
            throw new CustomException("code discount not empty" , CommonUtils.putError("code" , "ERR_0034"));
        }
        if (discountDTO.getDescription().equals("")) {
            throw new CustomException("description discount not empty" , CommonUtils.putError("description" , "ERR_0034"));
        }
        if (discountDTO.getDiscountPercent() < 0 || discountDTO.getDiscountPercent() > 100) {
            throw new CustomException("discount percent not be less than zero and greater than one hundred" , CommonUtils.putError("discountSale" , "ERR_0034"));
        }
        if (discountDTO.getExpireDateStart() == null) {
            throw new CustomException("expire start date discount not empty" , CommonUtils.putError("expireDateStart" , "ERR_0034"));
        }
        if (discountDTO.getExpireDateEnd() == null) {
            throw new CustomException("expire end date discount not empty" , CommonUtils.putError("expireDateEnd" , "ERR_0034"));
        }
        DiscountEntity discountEntityValidation = discountRepository.findByCode(discountDTO.getCode());
        if (discountEntityValidation != null) {
            throw new CustomException("code discount already exist" , CommonUtils.putError("expireDateEnd" , "ERR_0034"));
        }
        CreatedResponse response = new CreatedResponse();
        List<DiscountEntity> discountEntitiesDB = discountRepository.findAllByStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        discountDTO.setCode(discountDTO.getCode().toUpperCase());
        DiscountEntity discountEntity = discountConverter.toEntity(discountDTO);
        if (discountEntitiesDB.size() > 0) {
            discountEntity.setStatus(AppConstant.ACTIVE.INACTIVE_STATUS);
        } else {
            discountEntity.setStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        }
        discountEntity.setCreatedDate(Instant.now());
        discountEntity.setModifiedDate(Instant.now());
        response.setIdInserted(discountRepository.save(discountEntity).getId());
        return response;
    }

    /**
     * findOne
     *
     * @param id
     * @return DiscountDTO {com.server.tradedoc.logic.dto}
     */
    @Override
    public DiscountDTO findOne(Long id) {
        DiscountDTO discountDTO = discountConverter.toDto(discountRepository.findById(id).get());
        return discountDTO;
    }

    /**
     * findDiscountForClient
     *
     * @return DiscountDTO {com.server.tradedoc.logic.dto}
     */
    @Override
    public DiscountClientResponse findDiscountForClient() {
        DiscountClientResponse response = new DiscountClientResponse();
        DiscountEntity resultEntity = discountRepository.findByStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        response.setDiscount(discountConverter.toDto(resultEntity));
        if (DateTimeUtils.equalsDateTimeNow(resultEntity.getExpireDateStart())) {
            return null;
        }
        if (!DateTimeUtils.compareAfterDateTimeNow(resultEntity.getExpireDateStart())) {
            return null;
        }
        if (resultEntity != null) {
            response.setTimeRemaining(Integer.parseInt(DateTimeUtils.minusDayAndDateTimeNow(resultEntity.getExpireDateEnd()).toString()));
        } else {
            response.setTimeRemaining(0);
        }
        return response;
    }

    /**
     * notificationDiscount
     *
     * @return NotificationDiscount {com.server.tradedoc.logic.dto.reponse}
     */
    @Override
    public NotificationDiscount notificationDiscount() {
        NotificationDiscount response = new NotificationDiscount();
        DiscountEntity discountEntity = discountRepository.findByStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        if (discountEntity == null) {
            return new NotificationDiscount();
        }
        Long timeRemaining = DateTimeUtils.minusDayAndDateTimeNow(discountEntity.getExpireDateEnd());
        if (timeRemaining >= 3) {
            response.setTimeRemaining(Integer.parseInt(timeRemaining.toString()));
            response.setMessage("");
        }
        if (timeRemaining < 3) {
            response.setTimeRemaining(Integer.parseInt(timeRemaining.toString()));
            response.setMessage("thời hạn discount còn lại dưới 3 ngày lưu ý tắt trạng thái hoạt động của discount");
        }
        return response;
    }
}
