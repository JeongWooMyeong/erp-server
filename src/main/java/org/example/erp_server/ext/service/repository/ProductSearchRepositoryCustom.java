package org.example.erp_server.ext.service.repository;

import org.example.erp_server.ext.dto.ProductDocument;

public interface ProductSearchRepositoryCustom {

    void saveIfNewer(ProductDocument document);

    void deleteIfNewer(Long productId, Long version);
}