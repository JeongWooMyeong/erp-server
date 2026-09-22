package org.example.erp.search.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCT_MASTER", schema = "ERP")
@Getter
@NoArgsConstructor
public class ErpProductMaster {

    @Id
    @Column(name = "PRODUCT_CODE", length = 20)
    private String productCode;

    @Column(name = "PRODUCT_NAME", nullable = false, length = 200)
    private String productName;

    @Column(name = "CATEGORY_ID")
    private Long categoryId;
}
