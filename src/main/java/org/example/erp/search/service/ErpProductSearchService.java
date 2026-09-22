package org.example.erp.search.service;

import lombok.RequiredArgsConstructor;
import org.example.erp.search.document.ErpProductDocument;
import org.example.erp.search.dto.ErpProductSearchDto;
import org.example.erp.search.mapper.ErpProductDocumentMapper;
import org.example.erp.search.repository.ErpProductDocumentRepository;
import org.example.erp.search.repository.ErpProductSearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ErpProductSearchService {

    private final ErpProductSearchRepository searchRepository;
    private final ErpProductDocumentRepository documentRepository;
    private final ErpProductDocumentMapper documentMapper;

    public List<ErpProductSearchDto> findProducts() {
        return searchRepository.findProductSearchData();
    }

    public void indexProducts() {

        List<ErpProductSearchDto> products =
                searchRepository.findProductSearchData();

        List<ErpProductDocument> documents = products.stream()
                .map(documentMapper::toDocument)
                .toList();

        documentRepository.saveAll(documents);
    }
}