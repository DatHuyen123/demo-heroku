package com.server.tradedoc.logic.entity;

import javax.persistence.*;

/**
 * CommentsEntity
 *
 * @author DatDV
 */
@Entity
@Table(name = "comments")
public class CommentsEntity extends BaseEntity {

    @Column(name = "content")
    private String content;

    @Column(name = "usernamecomment")
    private String usernameComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    private ProductsEntity products;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsernameComment() {
        return usernameComment;
    }

    public void setUsernameComment(String usernameComment) {
        this.usernameComment = usernameComment;
    }

    public ProductsEntity getProducts() {
        return products;
    }

    public void setProducts(ProductsEntity products) {
        this.products = products;
    }
}
