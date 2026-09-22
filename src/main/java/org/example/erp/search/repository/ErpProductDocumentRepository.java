package org.example.erp.search.repository;

import org.example.erp.search.document.ErpProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ErpProductDocumentRepository
        extends ElasticsearchRepository<ErpProductDocument, String> {
}