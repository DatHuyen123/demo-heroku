package com.server.tradedoc.logic.api.admin;

import com.server.tradedoc.logic.dto.DiscountDTO;
import com.server.tradedoc.logic.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * DiscountAdminAPI
 *
 * @author DatDV
 */
@RestController
@RequestMapping("/api/admin")
public class DiscountAdminAPI {

    @Autowired
    private DiscountService discountService;

    /**
     * create
     *
     * @param discountDTO
     * @return
     */
    @RequestMapping(value = "/create-discount" , method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody DiscountDTO discountDTO) {
        return ResponseEntity.ok(discountService.create(discountDTO));
    }

    /**
     * findOne
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/find-one-discount" , method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@RequestParam("id") Long id){
        return ResponseEntity.ok(discountService.findOne(id));
    }
}
