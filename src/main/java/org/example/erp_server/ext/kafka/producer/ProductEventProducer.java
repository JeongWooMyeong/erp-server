package org.example.erp_server.ext.kafka.producer;

import org.example.erp_server.ext.kafka.dto.ProductEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductEventProducer {

    private final KafkaTemplate<String, ProductEvent> kafkaTemplate;

    public ProductEventProducer(
            KafkaTemplate<String, ProductEvent> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(ProductEvent event) {

        kafkaTemplate.send(
                "product-events",
                event.getProductId().toString(),
                event
        );
    }
}