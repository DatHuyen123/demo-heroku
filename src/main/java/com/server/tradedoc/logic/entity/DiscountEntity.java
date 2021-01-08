package com.server.tradedoc.logic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "discount")
public class DiscountEntity extends BaseEntity {
    
    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "discountsale")
    private Integer discountSale;

    @Column(name = "expiredatestart")
    private Date expireDateStart;

    @Column(name = "expiredateend")
    private Date expireDateEnd;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDiscountSale() {
        return discountSale;
    }

    public void setDiscountSale(Integer discountSale) {
        this.discountSale = discountSale;
    }

    public Date getExpireDateStart() {
        return expireDateStart;
    }

    public void setExpireDateStart(Date expireDateStart) {
        this.expireDateStart = expireDateStart;
    }

    public Date getExpireDateEnd() {
        return expireDateEnd;
    }

    public void setExpireDateEnd(Date expireDateEnd) {
        this.expireDateEnd = expireDateEnd;
    }
}
