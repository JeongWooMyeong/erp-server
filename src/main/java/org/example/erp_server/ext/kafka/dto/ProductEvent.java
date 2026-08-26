package org.example.erp_server.ext.kafka.dto;

public class ProductEvent {

    private String eventType;
    private Long productId;

    public ProductEvent() {
    }

    public ProductEvent(
            String eventType,
            Long productId
    ) {
        this.eventType = eventType;
        this.productId = productId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}