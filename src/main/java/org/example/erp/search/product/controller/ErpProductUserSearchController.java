package org.example.erp.search.product.controller;

import lombok.RequiredArgsConstructor;
import org.example.erp.search.product.dto.ErpProductSearchRequest;
import org.example.erp.search.product.service.ErpProductUserSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/erp/products")
public class ErpProductUserSearchController {

    private final ErpProductUserSearchService service;

    @GetMapping("/search")
    public Object search(ErpProductSearchRequest request) {
        return service.search(request);
    }
}