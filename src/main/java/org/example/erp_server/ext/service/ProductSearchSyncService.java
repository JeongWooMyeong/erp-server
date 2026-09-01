package org.example.erp_server.ext.service;

import org.example.erp_server.ext.kafka.dto.ProductEvent;

public interface ProductSearchSyncService {

    void sync(ProductEvent event);

    void delete(ProductEvent event);
}