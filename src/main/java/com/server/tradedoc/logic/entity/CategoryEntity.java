package com.server.tradedoc.logic.entity;

import com.server.tradedoc.logic.dto.reponse.CategoryResponse;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * CategoryEntity
 *
 * @author DatDV
 */
@Entity
@Table(name = "category")
@SqlResultSetMapping(name = "findAllCategory" , classes = {
        @ConstructorResult(targetClass = CategoryResponse.class ,
                columns = {
                @ColumnResult(name = "category_id", type = Long.class),
                @ColumnResult(name = "category_name", type = String.class),
                @ColumnResult(name = "category_code", type = String.class),
                @ColumnResult(name = "created_by", type = String.class),
                @ColumnResult(name = "modified_by", type = String.class),
                @ColumnResult(name = "created_date", type = Instant.class),
                @ColumnResult(name = "modified_date", type = Instant.class)
        })
})
public class CategoryEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @ManyToMany(mappedBy = "categorys")
    private List<ProductsEntity> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ProductsEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsEntity> products) {
        this.products = products;
    }
}
