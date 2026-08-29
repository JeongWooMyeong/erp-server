package org.example.erp_server.ext.service;

import org.example.erp_server.ext.dto.ProductSearchCondition;
import org.example.erp_server.ext.dto.ProductSearchResponse;

import java.io.IOException;

public interface ProductSearchService {

    ProductSearchResponse search(
            ProductSearchCondition condition,
            int page,
            int size
    ) throws IOException;

    void initialize();
}
