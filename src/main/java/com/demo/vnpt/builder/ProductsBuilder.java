package com.demo.vnpt.builder;

public class ProductsBuilder {
    private Long id;
    private String name;
    private String note;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getNote() {
        return note;
    }

    public ProductsBuilder(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.note = builder.note;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String note;

        public Builder setId(Long id){
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setNote(String note) {
            this.note = note;
            return this;
        }

        public ProductsBuilder builder(){
            return new ProductsBuilder(this);
        }
    }
}
