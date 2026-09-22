package org.example.erp.search.event.controller;

import lombok.RequiredArgsConstructor;
import org.example.erp.search.event.document.ErpEventProductDocument;
import org.example.erp.search.event.dto.ErpEventProductSearchRequest;
import org.example.erp.search.event.service.ErpEventProductUserSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/erp/events")
public class ErpEventProductUserSearchController {

    private final ErpEventProductUserSearchService service;

    @GetMapping("/search")
    public List<ErpEventProductDocument> search(
            ErpEventProductSearchRequest request
    ) {
        return service.search(request);
    }
}