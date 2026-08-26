package org.example.erp_server.ext.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductSearchResponse {

    private List<ProductDocument> content;

    // 다음 검색에 사용할 마지막 productId
    private Long nextCursor;

    // 다음 페이지 존재 여부
    private boolean hasNext;

    private long totalPages;
}