package org.example.erp_server.ext.kafka.consumer;

import org.example.erp_server.ext.kafka.dto.ProductEvent;
import org.example.erp_server.ext.service.ProductSearchSyncService;
import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Component;

@Component
public class ProductEventConsumer {

    private final ProductSearchSyncService productSearchSyncService;

    public ProductEventConsumer(
            ProductSearchSyncService productSearchSyncService
    ) {
        this.productSearchSyncService =
                productSearchSyncService;
    }

    @RetryableTopic(
            attempts = "4",
            backOff = @BackOff(
                    delay = 2000,
                    multiplier = 2
            ),
            dltTopicSuffix = ".DLT"
    )
    @KafkaListener(
            topics = "product-events",
            groupId = "product-search"
    )
    public void consume(ProductEvent event) {

        if ("CREATE".equals(event.getEventType())
                || "UPDATE".equals(event.getEventType())) {

            productSearchSyncService.sync(
                    event.getProductId()
            );

        } else if ("DELETE".equals(event.getEventType())) {

            productSearchSyncService.delete(
                    event.getProductId()
            );
        }
    }
}