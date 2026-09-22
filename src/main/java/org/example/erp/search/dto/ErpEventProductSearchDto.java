package org.example.erp.search.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErpEventProductSearchDto {

    private Long companyId;
    private Long storeId;

    private Long eventId;
    private String eventName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String activeYn;

    private String productCode;
    private String productName;

    private Long categoryId;
    private String categoryName;
    private Integer categoryLevel;

    private String storeName;
    private String location;

    private Integer stock;
    private BigDecimal price;

    private BigDecimal discountRate;
    private BigDecimal specialPrice;
}
