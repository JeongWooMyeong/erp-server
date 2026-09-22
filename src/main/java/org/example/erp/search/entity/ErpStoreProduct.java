package org.example.erp.search.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "STORE_PRODUCT", schema = "ERP")
@Getter
@NoArgsConstructor
public class ErpStoreProduct {

    @EmbeddedId
    private ErpStoreProductId id;

    @Column(name = "STOCK")
    private Integer stock;

    @Column(name = "PRICE", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "EVENT_ID")
    private Long eventId;

    @Column(name = "DISCOUNT_RATE", precision = 5, scale = 2)
    private BigDecimal discountRate;
}