package com.server.tradedoc.logic.service;

import com.server.tradedoc.logic.dto.DiscountDTO;
import com.server.tradedoc.logic.dto.reponse.CreatedResponse;
import com.server.tradedoc.logic.dto.reponse.DiscountClientResponse;
import com.server.tradedoc.logic.dto.reponse.NotificationDiscount;

/**
 * DiscountService
 *
 * @author DatDV
 */
public interface DiscountService {
    CreatedResponse create(DiscountDTO discountDTO);
    DiscountDTO findOne(Long id);
    DiscountClientResponse findDiscountForClient();
    NotificationDiscount notificationDiscount();
}
