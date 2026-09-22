package org.example.erp.search.service;

import lombok.RequiredArgsConstructor;
import org.example.erp.search.document.ErpEventProductDocument;
import org.example.erp.search.dto.ErpEventProductSearchDto;
import org.example.erp.search.repository.ErpEventProductDocumentRepository;
import org.example.erp.search.repository.ErpEventProductSearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ErpEventProductSearchService {

    private final ErpEventProductSearchRepository searchRepository;
    private final ErpEventProductDocumentRepository documentRepository;

    public List<ErpEventProductSearchDto> findEventProducts() {
        return searchRepository.findEventProductSearchData();
    }

    public void syncEventProducts() {

        List<ErpEventProductSearchDto> data =
                searchRepository.findEventProductSearchData();

        List<ErpEventProductDocument> documents = data.stream()
                .map(this::toDocument)
                .toList();

        documentRepository.saveAll(documents);
    }

    private ErpEventProductDocument toDocument(
            ErpEventProductSearchDto dto
    ) {

        String id = dto.getCompanyId()
                + "_"
                + dto.getStoreId()
                + "_"
                + dto.getEventId()
                + "_"
                + dto.getProductCode();

        return ErpEventProductDocument.builder()
                .id(id)

                .companyId(dto.getCompanyId())
                .storeId(dto.getStoreId())

                .eventId(dto.getEventId())
                .eventName(dto.getEventName())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .activeYn(dto.getActiveYn())

                .productCode(dto.getProductCode())
                .productName(dto.getProductName())

                .categoryId(dto.getCategoryId())
                .categoryName(dto.getCategoryName())
                .categoryLevel(dto.getCategoryLevel())

                .storeName(dto.getStoreName())
                .location(dto.getLocation())

                .stock(dto.getStock())
                .price(dto.getPrice())

                .discountRate(dto.getDiscountRate())
                .specialPrice(dto.getSpecialPrice())

                .build();
    }

}
