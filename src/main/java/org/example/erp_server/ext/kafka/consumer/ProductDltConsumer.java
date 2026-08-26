package org.example.erp_server.ext.kafka.consumer;

import org.example.erp_server.ext.kafka.dto.ProductEvent;
import org.example.erp_server.ext.service.ProductDltService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductDltConsumer {

    private final ProductDltService productDltService;

    public ProductDltConsumer(
            ProductDltService productDltService
    ) {
        this.productDltService = productDltService;
    }

    @KafkaListener(
            topics = "product-events.DLT",
            groupId = "product-dlt"
    )
    public void consume(ProductEvent event) {

        System.out.println(
                "===== DLT 이벤트 수신 ====="
        );

        System.out.println(
                "eventType = "
                        + event.getEventType()
        );

        System.out.println(
                "productId = "
                        + event.getProductId()
        );

        productDltService.saveDltEvent(event);
    }
}