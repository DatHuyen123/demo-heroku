package com.server.tradedoc.logic.api.client;

import com.server.tradedoc.logic.dto.CustomersDTO;
import com.server.tradedoc.logic.service.CustomersService;
import com.server.tradedoc.logic.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/api-client")
public class CustomersClientAPI {

    @Autowired
    private CustomersService customersService;

    @RequestMapping(value = "/create-sending" , method = RequestMethod.POST)
    public ResponseEntity<?> createAddSending(@RequestBody CustomersDTO customersDTO){
        return ResponseEntity.ok(customersService.createAddSending(customersDTO));
    }
}
