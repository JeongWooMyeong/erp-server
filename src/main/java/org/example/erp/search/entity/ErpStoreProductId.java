package org.example.erp.search.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class ErpStoreProductId implements Serializable {

    @Column(name = "COMPANY_ID")
    private Long companyId;

    @Column(name = "STORE_ID")
    private Long storeId;

    @Column(name = "PRODUCT_CODE", length = 20)
    private String productCode;
}