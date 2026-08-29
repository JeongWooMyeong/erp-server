package org.example.erp_server.ext.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductSearchResponse {

    // 현재 페이지 검색 결과
    private List<ProductDocument> content;

    // 현재 페이지 번호
    private int page;

    // 페이지 크기
    private int size;

    // 전체 검색 결과 수
    private long totalCount;

    // 전체 페이지 수
    private long totalPages;

    // 다음 페이지 존재 여부
    private boolean hasNext;
}
