package org.example.erp.search.controller;

import lombok.RequiredArgsConstructor;
import org.example.erp.search.dto.ErpProductSearchDto;
import org.example.erp.search.service.ErpProductSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ErpProductSearchController {

    private final ErpProductSearchService service;

    @GetMapping("/api/erp/search/products")
    public List<ErpProductSearchDto> findProducts() {
        return service.findProducts();
    }

    @PostMapping("/api/erp/search/products/index")
    public void indexProducts() {
        service.indexProducts();
    }

}