package com.example.kakao.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kakao._core.errors.exception.Exception404;
import com.example.kakao.product.ProductResponse.FindByIdV1DTO;
import com.example.kakao.product.option.Option;
import com.example.kakao.product.option.OptionJPARepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {

    private final ProductJPARepository productJPARepository;
    private final OptionJPARepository optionJPARepository;

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
    // 상품조회 + 옵션 조회
    public ProductResponse.FindByIdV1DTO findByIdV1(int id) {
        Product productPS = productJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 id의 상품을 찾을 수 없습니다 : " + id));
        List<Option> optionsPS = optionJPARepository.findByProductId(id);
        return new ProductResponse.FindByIdV1DTO(productPS, optionsPS);
    }

    // 양방향맵핑
    public ProductResponse.FindByIdV2DTO findByIdV2(int id) {
        Product productPS = productJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 id의 상품을 찾을 수 없습니다 : " + id));
        return new ProductResponse.FindByIdV2DTO(productPS);
    }
}
