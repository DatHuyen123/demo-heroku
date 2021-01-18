package com.server.tradedoc.logic.service;

import com.server.tradedoc.logic.dto.DiscountDTO;
import com.server.tradedoc.logic.dto.reponse.*;

import java.text.ParseException;

/**
 * DiscountService
 *
 * @author DatDV
 */
public interface DiscountService {
    CreatedResponse create(DiscountDTO discountDTO) throws ParseException;
    UpdateResponse updateStatusDiscount(Long id);
    DeleteResponse delete(Long id);
    DiscountDTO findOne(Long id);
    DiscountClientResponse findDiscountForClient();
    NotificationDiscount notificationDiscount();
}
