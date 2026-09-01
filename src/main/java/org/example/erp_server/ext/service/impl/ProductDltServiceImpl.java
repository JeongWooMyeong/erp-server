package org.example.erp_server.ext.service.impl;

import org.example.erp_server.ext.kafka.dto.FailedProductEvent;
import org.example.erp_server.ext.kafka.dto.ProductEvent;
import org.example.erp_server.ext.service.ProductDltService;
import org.example.erp_server.ext.service.ProductSearchSyncService;
import org.example.erp_server.ext.service.dao.oracle.FailedProductEventMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductDltServiceImpl
        implements ProductDltService {

    private final FailedProductEventMapper failedProductEventMapper;
    private final ProductSearchSyncService productSearchSyncService;

    public ProductDltServiceImpl(
            FailedProductEventMapper failedProductEventMapper,
            ProductSearchSyncService productSearchSyncService
    ) {
        this.failedProductEventMapper =
                failedProductEventMapper;

        this.productSearchSyncService =
                productSearchSyncService;
    }

    @Override
    public void saveDltEvent(ProductEvent event) {

        FailedProductEvent failedEvent =
                new FailedProductEvent();

        failedEvent.setEventType(
                event.getEventType()
        );

        failedEvent.setProductId(
                event.getProductId()
        );

        failedEvent.setVersion(
                event.getVersion()
        );

        failedEvent.setStatus("FAILED");
        failedEvent.setErrorMessage(
                "Kafka DLT 이동"
        );


        failedProductEventMapper.save(failedEvent);

        System.out.println(
                "DLT 이벤트 DB 저장 완료 = productId: "
                        + event.getProductId()
        );
    }

    @Override
    public void retry(Long id) {

        FailedProductEvent event =
                failedProductEventMapper.findById(id);

        ProductEvent retryEvent = new ProductEvent(
                event.getEventType(),
                event.getProductId(),
                event.getVersion()
        );

        if (event == null) {
            throw new RuntimeException(
                    "재처리할 이벤트가 없습니다. id=" + id
            );
        }

        if ("RESOLVED".equals(event.getStatus())) {
            throw new RuntimeException(
                    "이미 처리된 이벤트입니다. id=" + id
            );
        }

        System.out.println("PRODUCTID" + event.getProductId());

        if ("DELETE".equals(event.getEventType())) {

            productSearchSyncService.delete(
                    retryEvent
            );

        } else {

            productSearchSyncService.sync(
                    retryEvent
            );
        }

        // ES 처리가 성공했을 때만 실행
        failedProductEventMapper.resolve(id);

        System.out.println(
                "DLT 재처리 완료 = id: " + id
        );
    }

    @Override
    public List<FailedProductEvent> findAll() {
        return failedProductEventMapper.findAll();
    }
}