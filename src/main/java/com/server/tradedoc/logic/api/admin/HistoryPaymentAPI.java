package com.server.tradedoc.logic.api.admin;

import com.server.tradedoc.logic.builder.SearchHistoryPaymentBuilder;
import com.server.tradedoc.logic.service.HistoryPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/history-payment")
public class HistoryPaymentAPI {

    @Autowired
    private HistoryPaymentService historyPaymentService;

    @RequestMapping(value = "/get-all-payment-type" , method = RequestMethod.GET)
    public ResponseEntity<Map<String , String>> getPaymentType(){
        return ResponseEntity.ok(historyPaymentService.getAllPaymentType());
    }

    @RequestMapping(value = "/get-all-history-payment" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllHistoryPayment(Map<String , String> model){
        SearchHistoryPaymentBuilder builder = initSearchBuilder(model);
        Pageable pageable = PageRequest.of(Integer.parseInt(model.get("page")) - 1 , Integer.parseInt(model.get("maxPageItem")));
        return ResponseEntity.ok(historyPaymentService.getAllHistoryPayment(builder , pageable));
    }

    private SearchHistoryPaymentBuilder initSearchBuilder(Map<String , String> model) {
        SearchHistoryPaymentBuilder builder = new SearchHistoryPaymentBuilder.builder()
                .setEmailCustomer(model.get("emailCustomer"))
                .setProductName(model.get("productName"))
                .setPaymentDateFrom(model.get("paymentDateFrom"))
                .setPaymentDateTo(model.get("paymentDateTo"))
                .setCustomerName(model.get("customerName"))
                .setPhoneNumber(model.get("phoneNumber"))
                .setPriceTo(model.get("priceTo"))
                .setPriceForm(model.get("priceForm"))
                .builder();
        return builder;
    }
}
