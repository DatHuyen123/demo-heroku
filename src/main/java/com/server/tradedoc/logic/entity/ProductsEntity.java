package com.server.tradedoc.logic.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductsEntity extends BaseEntity {

    @Column(name = "productname")
    private String productName;

    @Column(name = "price")
    private Integer price;

    @Column(name = "description" , columnDefinition = "TEXT")
    private String description;

    @Column(name = "pathfile")
    private String pathFile;

    @Column(name = "title")
    private String title;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "products" , fetch = FetchType.LAZY)
    private List<CommentsEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "products" , fetch = FetchType.LAZY)
    private List<ImageEntity> images = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category" , joinColumns = @JoinColumn(name = "productid") ,
            inverseJoinColumns = @JoinColumn(name = "categoryid"))
    private List<CategoryEntity> categorys;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CommentsEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentsEntity> comments) {
        this.comments = comments;
    }

    public List<ImageEntity> getImages() {
        return images;
    }

    public void setImages(List<ImageEntity> images) {
        this.images = images;
    }

    public List<CategoryEntity> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<CategoryEntity> categorys) {
        this.categorys = categorys;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
