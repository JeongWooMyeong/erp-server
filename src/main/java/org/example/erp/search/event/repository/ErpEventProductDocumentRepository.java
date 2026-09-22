package org.example.erp.search.event.repository;

import org.example.erp.search.event.document.ErpEventProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ErpEventProductDocumentRepository
        extends ElasticsearchRepository<ErpEventProductDocument, String> {
}