package org.example.erp.search.event.controller;

import lombok.RequiredArgsConstructor;
import org.example.erp.search.event.dto.ErpEventProductSearchDto;
import org.example.erp.search.event.service.ErpEventProductSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ErpEventProductSearchController {

    private final ErpEventProductSearchService service;

    @GetMapping("/api/erp/search/event-products")
    public List<ErpEventProductSearchDto> findEventProducts() {
        return service.findEventProducts();
    }

    @PostMapping("/api/erp/search/event-products/sync")
    public void syncEventProducts() {
        service.syncEventProducts();
    }
}