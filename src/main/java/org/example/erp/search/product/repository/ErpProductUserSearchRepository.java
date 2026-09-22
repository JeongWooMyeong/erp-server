package org.example.erp.search.product.repository;

import org.example.erp.search.product.dto.ErpProductSearchRequest;

public interface ErpProductUserSearchRepository {

    Object search(ErpProductSearchRequest request);
}