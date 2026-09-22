package org.example.erp.search.product.service;

import lombok.RequiredArgsConstructor;
import org.example.erp.search.product.dto.ErpProductSearchRequest;
import org.example.erp.search.product.repository.ErpProductUserSearchRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ErpProductUserSearchService {

    private final ErpProductUserSearchRepository repository;

    public Object search(ErpProductSearchRequest request) {
        return repository.search(request);
    }
}