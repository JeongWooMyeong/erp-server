package org.example.erp_server.ext.controller;


import org.example.erp_server.ext.kafka.dto.FailedProductEvent;
import org.example.erp_server.ext.service.ProductDltService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ext/kafka/dlt")
public class ProductDltController {

    private final ProductDltService productDltService;

    public ProductDltController(
            ProductDltService productDltService
    ) {
        this.productDltService = productDltService;
    }

    @PostMapping("/retry/{id}")
    public void retry(
            @PathVariable Long id
    ) {

        productDltService.retry(id);
    }

    // 전체 실패 이벤트 조회
    @GetMapping
    public List<FailedProductEvent> findAll() {
        return productDltService.findAll();
    }
}