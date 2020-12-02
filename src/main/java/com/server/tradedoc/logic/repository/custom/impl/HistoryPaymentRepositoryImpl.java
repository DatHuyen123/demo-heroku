package com.server.tradedoc.logic.repository.custom.impl;

import com.server.tradedoc.logic.builder.SearchHistoryPaymentBuilder;
import com.server.tradedoc.logic.dto.reponse.HistoryPaymentSearchDTO;
import com.server.tradedoc.logic.entity.HistoryPaymentEntity;
import com.server.tradedoc.logic.repository.custom.HistoryPaymentRepositoryCustom;
import com.server.tradedoc.utils.BuildMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * HistoryPaymentRepositoryImpl
 *
 * @author DatDV
 */
@Repository
public class HistoryPaymentRepositoryImpl extends RepositoryCustomUtils<HistoryPaymentEntity> implements HistoryPaymentRepositoryCustom {

    @Autowired
    private BuildMapUtils buildMapUtils;

    @Override
    public List<HistoryPaymentSearchDTO> findAllHistoryPayment(SearchHistoryPaymentBuilder builder, Pageable pageable) {
        Map<String, Object> parameter = buildMapUtils.buildMapSearch(builder);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT                                            " +
                "   his.paymenttype AS payment_type,              " +
                "   his.total AS payment_total,                   " +
                "   his.customerid AS customer_id,                " +
                "   CASE                                            " +
                "       WHEN his.status = 1 THEN \"Thành Công\"   " +
                "       WHEN his.status = 0 THEN \"Thất Bại\"     " +
                "   END history_status,                           " +
                "   cus.customername AS customer_name,              " +
                "   cus.email AS customer_email,                  " +
                "   cus.phonenumber AS customer_phone,            " +
                "   pro.price AS product_price,                   " +
                "   pro.productname AS product_name,              " +
                "   pro.avatar AS product_avatar,                 " +
                "   his.createddate AS created_date,              " +
                "   his.modifieddate AS modified_date            " +
                "FROM                                             " +
                "customers cus                                    " +
                "INNER JOIN history_payment his ON cus.id = his.customerid " +
                "INNER JOIN products pro ON pro.id = his.productid " +
                "WHERE 1=1 ");
        if (!builder.getCustomerName().equals("")) {
            sql.append("AND cus.customername LIKE :customername ");
        }
        if (!builder.getEmailCustomer().equals("")) {
            sql.append("AND cus.email LIKE :emailcustomer ");
        }
        if (!builder.getPhoneNumber().equals("")) {
            sql.append("AND cus.phonenumber LIKE :phonenumber ");
        }
        if (builder.getPriceForm() != null) {
            sql.append("AND pro.price >= :priceform ");
        }
        if (builder.getPriceTo() != null) {
            sql.append("AND pro.price <= :priceto ");
        }
        if (!builder.getProductName().equals("")) {
            sql.append("AND pro.productname LIKE :productname ");
        }
        if (!builder.getPaymentDateFrom().equals("")) {
            sql.append("AND CONCAT(DATE_FORMAT(his.createddate,\"%Y%m%d\") , \"0101\") >= DATE_FORMAT(STR_TO_DATE(:paymentdatefrom, '%d/%m/%Y') , '%Y%m%d') ");
        }
        if (!builder.getPriceTo().equals("")) {
            sql.append("AND CONCAT(DATE_FORMAT(his.createddate,\"%Y%m%d\") , \"0101\") <= DATE_FORMAT(STR_TO_DATE(:paymentdateto, '%d/%m/%Y') + 1, '%Y%m%d') ");
        }
        return this.getResultList(sql.toString(), parameter, "findAllHistoryPayment", pageable);
    }
}
