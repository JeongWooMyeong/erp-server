package org.example.erp.search.event.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErpEventProductSearchRequest {

    private String keyword;

    private Long companyId;
    private Long storeId;
    private Long categoryId;
    private Long eventId;

    private String activeYn;
}