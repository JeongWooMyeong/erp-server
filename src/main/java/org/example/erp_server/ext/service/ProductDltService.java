package org.example.erp_server.ext.service;


import org.example.erp_server.ext.kafka.dto.FailedProductEvent;
import org.example.erp_server.ext.kafka.dto.ProductEvent;

import java.util.List;

public interface ProductDltService {

    void saveDltEvent(ProductEvent event);

    void retry(Long productId);

    List<FailedProductEvent> findAll();

}
