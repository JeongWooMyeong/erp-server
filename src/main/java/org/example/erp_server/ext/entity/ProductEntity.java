package org.example.erp_server.ext.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT", schema = "ERP")
public class ProductEntity {

    @Id
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRODUCT_NAME", nullable = false, length = 100)
    private String productName;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "STOCK")
    private Integer stock;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "PRODUCT_CODE", length = 20)
    private String productCode;

    @Column(name = "VERSION", nullable = false)
    private Long version;

    // getter, setter
}