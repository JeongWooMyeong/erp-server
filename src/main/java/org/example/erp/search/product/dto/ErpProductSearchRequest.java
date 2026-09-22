package org.example.erp.search.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErpProductSearchRequest {

    private String keyword;

    private Long companyId;

    private Long storeId;

    private Long categoryId;
}