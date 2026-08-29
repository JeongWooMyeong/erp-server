package org.example.erp_server.ext.controller;

import org.example.erp_server.ext.dto.ProductSearchCondition;
import org.example.erp_server.ext.dto.ProductSearchResponse;
import org.example.erp_server.ext.service.ProductSearchService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/products")
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    public ProductSearchController(
            ProductSearchService productSearchService
    ) {
        this.productSearchService = productSearchService;
    }

    // 상품 검색
    // Redis Cache → Elasticsearch
    @GetMapping("/search")
    public ProductSearchResponse search(
            ProductSearchCondition condition,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) throws IOException {

        System.out.println(
                "field = " + condition.getField()
        );

        System.out.println(
                "keyword = " + condition.getKeyword()
        );

        System.out.println(
                "page = " + page
        );

        System.out.println(
                "size = " + size
        );

        return productSearchService.search(
                condition,
                page,
                size
        );
    }

    // Elasticsearch 초기 색인
    @PostMapping("/initialize")
    public String initialize() {

        productSearchService.initialize();

        return "Elasticsearch 초기 적재 완료";
    }
}
