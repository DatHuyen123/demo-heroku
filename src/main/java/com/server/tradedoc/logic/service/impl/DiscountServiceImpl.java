package com.server.tradedoc.logic.service.impl;

import com.server.tradedoc.logic.repository.DiscountRepository;
import com.server.tradedoc.logic.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;
}
