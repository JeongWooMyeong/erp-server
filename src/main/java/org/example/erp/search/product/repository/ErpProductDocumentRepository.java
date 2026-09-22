package org.example.erp.search.product.repository;

import org.example.erp.search.product.document.ErpProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ErpProductDocumentRepository
        extends ElasticsearchRepository<ErpProductDocument, String> {
}