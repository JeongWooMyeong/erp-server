package org.example.erp_server.ext.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchCondition {

    // 전체 / 상품명 / 상품코드
    private String field;

    // 검색어
    private String keyword;
}
