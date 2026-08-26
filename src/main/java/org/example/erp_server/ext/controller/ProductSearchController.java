package org.example.erp_server.ext.controller;


import org.example.erp_server.ext.dto.ProductSearchCondition;
import org.example.erp_server.ext.dto.ProductSearchResponse;
import org.example.erp_server.ext.service.ProductSearchService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/es/products")
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    public ProductSearchController(
            ProductSearchService productSearchService
    ) {
        this.productSearchService = productSearchService;
    }

    @GetMapping("/search")
    public ProductSearchResponse search(
            ProductSearchCondition condition,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(required = false) Long cursor
    ) throws IOException {

        return productSearchService.search(
                condition,
                size,
                cursor
        );
    }

    @PostMapping("/initialize")
    public String initialize() {

        productSearchService.initialize();

        return "Elasticsearch 초기 적재 완료";
    }
}