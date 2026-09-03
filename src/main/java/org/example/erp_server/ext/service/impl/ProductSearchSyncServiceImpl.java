package org.example.erp_server.ext.service.impl;

import org.example.erp_server.ext.dto.ProductDocument;
import org.example.erp_server.ext.kafka.dto.ProductEvent;
import org.example.erp_server.ext.service.ProductSearchSyncService;
import org.example.erp_server.ext.service.repository.ProductSearchRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductSearchSyncServiceImpl
        implements ProductSearchSyncService {

    private final ProductSearchRepository productSearchRepository;

    public ProductSearchSyncServiceImpl(
            ProductSearchRepository productSearchRepository
    ) {
        this.productSearchRepository = productSearchRepository;
    }

    @Override
    public void sync(ProductEvent event) {

        ProductDocument document =
                new ProductDocument(
                        event.getProductId(),
                        event.getProductName(),
                        event.getPrice(),
                        event.getStock(),
                        event.getProductCode(),
                        event.getVersion(),
                        event.getDeleted()
                );

        // CREATE / UPDATE / DELETE 모두 여기서 처리
        productSearchRepository.saveIfNewer(document);
    }

    @Override
    public void delete(ProductEvent event) {

        // 실제 삭제하지 않고 deleted = Y 상태를 저장
        sync(event);
    }
}
