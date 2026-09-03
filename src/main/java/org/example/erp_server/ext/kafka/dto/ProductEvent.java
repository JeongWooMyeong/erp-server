package org.example.erp_server.ext.kafka.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductEvent {

    private String eventType;

    private Long productId;
    private Long version;
    private String deleted;

    private String productName;
    private Double price;
    private Integer stock;
    private String productCode;

    public ProductEvent() {
    }

    public ProductEvent(
            String eventType,
            Long productId,
            Long version,
            String deleted,
            String productName,
            Double price,
            Integer stock,
            String productCode
    ) {
        this.eventType = eventType;
        this.productId = productId;
        this.version = version;
        this.deleted = deleted;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.productCode = productCode;
    }
}
