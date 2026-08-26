package org.example.erp_server.ext.kafka.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FailedProductEvent {
    private Long id;

    private String eventType;

    private Long productId;

    private String status;

    private String errorMessage;

    private LocalDateTime createdAt;

    private LocalDateTime resolvedAt;
}
