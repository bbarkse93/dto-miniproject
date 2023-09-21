package com.example.kakao.order;

import java.util.List;
import java.util.stream.Collectors;

import com.example.kakao.cart.Cart;
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

        private Integer totalPrice;
        private List<CartDTO> carts;
        
        public FindAllByUserDTO(Integer totalPrice, List<Cart> carts) {
            this.totalPrice = totalPrice;
            this.carts = carts.stream()
                    .map(c -> new CartDTO(c))
                    .collect(Collectors.toList());
        }

        @Getter @Setter
        public class CartDTO{
            private Integer cartId;
            private Integer optionId;
            private String productOptionName;
            private Integer quantity;
            private Integer cartPrice;


            public CartDTO(Cart cart) {
                this.cartId = cart.getId();
                this.optionId = cart.getOption().getId();
                this.productOptionName = cart.getOption().getProduct().getProductName()+" "+cart.getOption().getOptionName();
                this.quantity = cart.getQuantity();
                this.cartPrice = cart.getPrice();
            }
        }
    }

    // (기능5) 주문결과 확인
    @ToString
    @Getter
    @Setter
    public static class FindByIdDTO {

        private Integer totalPrice;
        private ProductDTO product;
        private List<Item> items;

        public FindByIdDTO(Integer totalPrice, ProductDTO product, List<Item> items) {
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
