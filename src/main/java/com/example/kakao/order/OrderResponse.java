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

        @Getter
        @Setter
        public class CartDTO {
            private Integer cartId;
            private Integer optionId;
            private String productOptionName;
            private Integer quantity;
            private Integer cartPrice;

            public CartDTO(Cart cart) {
                this.cartId = cart.getId();
                this.optionId = cart.getOption().getId();
                this.productOptionName = cart.getOption().getProduct().getProductName() + " "
                        + cart.getOption().getOptionName();
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
        private List<ProductDTO> products;

        public FindByIdDTO(List<Item> itemList) {
            this.totalPrice = itemList.stream().mapToInt(i -> i.getPrice()).sum();

            this.products = itemList.stream()
                    .map(i -> i.getOption().getProduct()).distinct()
                    .map(p -> new ProductDTO(p, itemList))
                    .collect(Collectors.toList());

        }

        // product
        @Getter
        @Setter
        public class ProductDTO {
            private Integer productId;
            private String productName;
            private List<ItemDTO> items;

            public ProductDTO(Product product, List<Item> itemList) {
                this.productId = product.getId();
                this.productName = product.getProductName();
                this.items = itemList.stream()
                        .filter(i -> i.getOption().getProduct().getId() == product.getId())
                        .map(i -> new ItemDTO(i))
                        .collect(Collectors.toList());
            }

        }

        @Getter
        @Setter
        public class ItemDTO {
            private Integer itemId;
            private Integer optionId;
            private String optionName;
            private Integer quantity;
            private Integer itemPrice;

            public ItemDTO(Item item) {
                this.itemId = item.getId();
                this.optionId = item.getOption().getId();
                this.optionName = item.getOption().getOptionName();
                this.quantity = item.getQuantity();
                this.itemPrice = item.getPrice();
            }

        }
    }

}
