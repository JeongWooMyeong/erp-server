package org.example.erp.search.event.service;

import lombok.RequiredArgsConstructor;
import org.example.erp.search.event.document.ErpEventProductDocument;
import org.example.erp.search.event.dto.ErpEventProductSearchRequest;
import org.example.erp.search.event.repository.ErpEventProductUserSearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ErpEventProductUserSearchService {

    private final ErpEventProductUserSearchRepository repository;

    public List<ErpEventProductDocument> search(
            ErpEventProductSearchRequest request
    ) {
        return repository.search(request);
    }
}