package com.juliomesquita.picpay_simplificado.core.commom.generics;

public record PaginationGeneric(
        Integer page,
        Integer perPage,
        Long totalElements,
        Integer totalPages
) {
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer page;
        private Integer perPage;
        private Long totalElements;
        private Integer totalPages;

        private Builder() {
        }

        public Builder page(Integer val) {
            page = val;
            return this;
        }

        public Builder perPage(Integer val) {
            perPage = val;
            return this;
        }

        public Builder totalElements(Long val) {
            totalElements = val;
            return this;
        }

        public Builder totalPages(Integer val) {
            totalPages = val;
            return this;
        }

        public PaginationGeneric build() {
            return new PaginationGeneric(
                    this.page,
                    this.perPage,
                    this.totalElements,
                    this.totalPages
            );
        }
    }
}
