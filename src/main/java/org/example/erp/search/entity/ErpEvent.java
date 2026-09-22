package org.example.erp.search.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "EVENT", schema = "ERP")
@Getter
@NoArgsConstructor
public class ErpEvent {

    @Id
    @Column(name = "EVENT_ID")
    private Long eventId;

    @Column(name = "EVENT_NAME", nullable = false, length = 200)
    private String eventName;

    @Column(name = "DISCOUNT_RATE", precision = 5, scale = 2)
    private BigDecimal discountRate;

    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "ACTIVE_YN", nullable = false, length = 1)
    private String activeYn;
}