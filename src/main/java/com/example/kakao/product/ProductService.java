package com.example.kakao.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kakao._core.errors.exception.Exception404;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {

    private final ProductJPARepository productJPARepository;

    // (기능1) 상품 목록보기
    public List<ProductResponse.FindAllDTO> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 9);
        Page<Product> productList = productJPARepository.findAll(pageable);
        List<ProductResponse.FindAllDTO> responseDTO = productList.stream()
                .map(p -> new ProductResponse.FindAllDTO(p))
                .collect(Collectors.toList());

        return responseDTO;
    }

    // (기능2) 상품 상세보기
    public ProductResponse.FindByIdDTO findById(int id) {
        Product productPS = productJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 id의 상품을 찾을 수 없습니다 : " + id));
        return new ProductResponse.FindByIdDTO(productPS);
    }
}
