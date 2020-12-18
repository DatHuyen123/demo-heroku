package com.server.tradedoc.logic.builder;

/**
 * SearchProductBuilder
 *
 * @author DatDV
 */
public class SearchProductBuilder {

    private String categoryName;
    private String productName;
    private Integer priceTo;
    private Integer priceForm;
    private String productType;

    public String getCategoryName() {
        return categoryName;
    }
    public String getProductName() {
        return productName;
    }
    public Integer getPriceTo() {
        return priceTo;
    }
    public Integer getPriceForm() {
        return priceForm;
    }
    public String getProductType() {
        return productType;
    }

    public SearchProductBuilder(builder builder){
        this.categoryName = builder.categoryName;
        this.productName = builder.productName;
        this.priceTo = builder.priceTo;
        this.priceForm = builder.priceForm;
        this.productType = builder.productType;
    }

    public static class builder{
        private String categoryName;
        private String productName;
        private Integer priceTo;
        private Integer priceForm;
        private String productType;

        public builder setCategoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }
        public builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }
        public builder setPriceTo(Integer priceTo) {
            this.priceTo = priceTo;
            return this;
        }
        public builder setPriceForm(Integer priceForm) {
            this.priceForm = priceForm;
            return this;
        }
        public builder setProductType(String productType) {
            this.productType = productType;
            return this;
        }

        public SearchProductBuilder builder(){ return new SearchProductBuilder(this); }
    }
}
