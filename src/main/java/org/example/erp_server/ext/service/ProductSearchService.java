package org.example.erp_server.ext.service;


import org.example.erp_server.ext.dto.ProductSearchCondition;
import org.example.erp_server.ext.dto.ProductSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface ProductSearchService {

    ProductSearchResponse search(
            ProductSearchCondition condition,
            int size,
            Long cursor
    ) throws IOException;
    void initialize();
}