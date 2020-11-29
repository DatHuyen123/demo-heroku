package com.server.tradedoc.logic.service;

import com.server.tradedoc.logic.dto.CustomersDTO;
import com.server.tradedoc.logic.dto.reponse.MessageSuccess;

/**
 * CustomersService
 *
 * @author DatDV
 */
public interface CustomersService {
    MessageSuccess createAddSending(CustomersDTO customersDTO);
}
