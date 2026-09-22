package org.example.erp.search.event.repository;

import org.example.erp.search.event.document.ErpEventProductDocument;
import org.example.erp.search.event.dto.ErpEventProductSearchRequest;

import java.util.List;

public interface ErpEventProductUserSearchRepository {

    List<ErpEventProductDocument> search(
            ErpEventProductSearchRequest request
    );
}