package com.server.tradedoc.logic.builder;

/**
 * SearchCategoryBuilder
 *
 * @author DatDV
 */
public class SearchCategoryBuilder {
    private String name;
    private Long productId;

    public String getName() {
        return name;
    }
    public Long getProductId() {
        return productId;
    }

    public SearchCategoryBuilder(builder builder) {
        this.name = builder.name;
        this.productId = builder.productId;
    }

    public static class builder {
        private String name;
        private Long productId;

        public builder setName(String name) {
            this.name = name;
            return this;
        }
        public builder setProductId(Long productId) {
            this.productId = productId;
            return this;
        }

        public SearchCategoryBuilder builder() { return new SearchCategoryBuilder(this); }
    }
}
