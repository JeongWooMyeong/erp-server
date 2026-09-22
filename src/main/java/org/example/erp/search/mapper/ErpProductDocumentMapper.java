package org.example.erp.search.mapper;

import org.example.erp.search.document.ErpProductDocument;
import org.example.erp.search.dto.ErpProductSearchDto;
import org.springframework.stereotype.Component;

@Component
public class ErpProductDocumentMapper {

    public ErpProductDocument toDocument(ErpProductSearchDto dto) {

        String id = dto.getCompanyId()
                + "_"
                + dto.getStoreId()
                + "_"
                + dto.getProductCode();

        return ErpProductDocument.builder()
                .id(id)
                .companyId(dto.getCompanyId())
                .storeId(dto.getStoreId())
                .storeName(dto.getStoreName())
                .location(dto.getLocation())
                .productCode(dto.getProductCode())
                .productName(dto.getProductName())
                .categoryId(dto.getCategoryId())
                .categoryName(dto.getCategoryName())
                .categoryLevel(dto.getCategoryLevel())
                .stock(dto.getStock())
                .price(dto.getPrice())
                .build();
    }
}