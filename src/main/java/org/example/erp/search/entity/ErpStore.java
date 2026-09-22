package org.example.erp.search.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "STORE", schema = "ERP")
@Getter
@NoArgsConstructor
public class ErpStore {

    @Id
    @Column(name = "STORE_ID")
    private Long storeId;

    @Column(name = "COMPANY_ID", nullable = false)
    private Long companyId;

    @Column(name = "STORE_NAME", nullable = false, length = 200)
    private String storeName;

    @Column(name = "LOCATION", length = 200)
    private String location;
}