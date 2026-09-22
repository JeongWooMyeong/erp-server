package org.example.erp.search.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "EVENT_PRODUCT", schema = "ERP")
@Getter
@NoArgsConstructor
public class ErpEventProduct {

    @EmbeddedId
    private ErpEventProductId id;

    @Column(name = "DISCOUNT_RATE", precision = 5, scale = 2)
    private BigDecimal discountRate;

    @Column(name = "SPECIAL_PRICE", precision = 10, scale = 2)
    private BigDecimal specialPrice;
}