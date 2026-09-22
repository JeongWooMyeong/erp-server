package org.example.erp.search.repository;

import org.example.erp.search.document.ErpEventProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ErpEventProductDocumentRepository
        extends ElasticsearchRepository<ErpEventProductDocument, String> {
}