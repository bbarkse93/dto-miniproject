package com.example.kakao.order;

import java.util.List;

import com.example.kakao.order.item.Item;
import com.example.kakao.product.Product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class OrderResponse {

    // (기능4) 주문상품 정보조회 (유저별)
    @ToString
    @Getter
    @Setter
    public static class FindAllByUserDTO {

    }

    // (기능5) 주문결과 확인
    @ToString
    @Getter
    @Setter
    public static class FindByIdDTO {

        private Integer totalPrice;
        private ProductDTO product;
        private List<Item> items;

        public FindByIdDTO(Integer totalPrice, Product product, List<Item> items) {
            this.totalPrice = totalPrice;
            this.product = product;
            this.items = items;
        }

        // product
        @Getter
        @Setter
        public class ProductDTO {
            private Integer productId;
            private String productName;

            public ProductDTO(Product product) {
                this.productId = product.getId();
                this.productName = product.getProductName();
            }

        }

    }
}
