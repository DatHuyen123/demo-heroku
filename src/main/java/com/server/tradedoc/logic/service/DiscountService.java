package com.server.tradedoc.logic.service;

import com.server.tradedoc.logic.dto.DiscountDTO;
import com.server.tradedoc.logic.dto.reponse.CreatedResponse;

public interface DiscountService {
    CreatedResponse create(DiscountDTO discountDTO);
    DiscountDTO findOne(Long id);
    DiscountDTO findDiscountForClient();
}
