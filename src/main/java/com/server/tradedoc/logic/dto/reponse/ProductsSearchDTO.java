package com.server.tradedoc.logic.dto.reponse;

import com.server.tradedoc.logic.dto.ImageDTO;

import java.time.Instant;
import java.util.List;

public class ProductsSearchDTO {
    private Long productId;
    private String productName;
    private String categoryName;
    private Integer price;
    private String pathFile;
    private String description;
    private String type;
    private String avatar;
    private String title;
    private List<ImageDTO> imageDTOS;
    private String createdBy;
    private String modifiedBy;
    private Instant createdDate;
    private Instant modifiedDate;

    public ProductsSearchDTO() {
    }

    public ProductsSearchDTO(Long productId, String productName, String categoryName, Integer price, String pathFile, String description, String type, String avatar, String title, String createdBy, String modifiedBy, Instant createdDate, Instant modifiedDate) {
        this.productId = productId;
        this.productName = productName;
        this.categoryName = categoryName;
        this.price = price;
        this.pathFile = pathFile;
        this.description = description;
        this.type = type;
        this.avatar = avatar;
        this.title = title;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ImageDTO> getImageDTOS() {
        return imageDTOS;
    }

    public void setImageDTOS(List<ImageDTO> imageDTOS) {
        this.imageDTOS = imageDTOS;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
