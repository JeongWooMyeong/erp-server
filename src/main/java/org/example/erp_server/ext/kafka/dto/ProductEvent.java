package org.example.erp_server.ext.kafka.dto;

public class ProductEvent {

    private String eventType;
    private Long productId;
    private Long version;

    public ProductEvent() {
    }

    public ProductEvent(
            String eventType,
            Long productId,
            Long version
    ) {
        this.eventType = eventType;
        this.productId = productId;
        this.version = version;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}