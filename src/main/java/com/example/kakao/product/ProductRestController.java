package com.example.kakao.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProductRestController {

    private final ProductService productService;

    // (기능1) 상품 목록보기
    @GetMapping("/products")
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page) {
        return null;
    }

    // (기능2) 상품 상세보기
    @GetMapping("/products/{id}/v1") // (상품조회+옵션조회, 조회2번)
    public ResponseEntity<?> findByIdV1(@PathVariable int id) {
        ProductResponse.FindByIdV1DTO responseDTO = productService.findByIdV1(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/products/{id}/v2") // (상품만조회, 양방향 맵핑)
    public ResponseEntity<?> findByIdV2(@PathVariable int id) {
        ProductResponse.FindByIdV2DTO responseDTO = productService.findByIdV2(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/products/{id}/v3") // (옵션만 조회)
    public ResponseEntity<?> findByIdV3(@PathVariable int id) {
        return null;
    }
}