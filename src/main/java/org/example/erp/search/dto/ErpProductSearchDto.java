package org.example.erp.search.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ErpProductSearchDto {

    private Long companyId;
    private Long storeId;
    private String storeName;
    private String location;

    private String productCode;
    private String productName;

    private Long categoryId;
    private String categoryName;
    private Integer categoryLevel;

    private Integer stock;
    private BigDecimal price;
}