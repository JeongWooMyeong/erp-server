package org.example.erp_server.ext.service.impl;

import org.example.erp_server.ext.kafka.dto.FailedProductEvent;
import org.example.erp_server.ext.kafka.dto.ProductEvent;
import org.example.erp_server.ext.service.ProductDltService;
import org.example.erp_server.ext.service.ProductSearchSyncService;
import org.example.erp_server.ext.service.dao.oracle.FailedProductEventMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDltServiceImpl
        implements ProductDltService {

    private final FailedProductEventMapper failedProductEventMapper;
    private final ProductSearchSyncService productSearchSyncService;

    public ProductDltServiceImpl(
            FailedProductEventMapper failedProductEventMapper,
            ProductSearchSyncService productSearchSyncService
    ) {
        this.failedProductEventMapper = failedProductEventMapper;
        this.productSearchSyncService = productSearchSyncService;
    }

    /**
     * Kafka DLT 이벤트 DB 저장
     */
    @Override
    public void saveDltEvent(ProductEvent event) {

        FailedProductEvent failedEvent =
                new FailedProductEvent();

        failedEvent.setEventType(event.getEventType());
        failedEvent.setProductId(event.getProductId());
        failedEvent.setVersion(event.getVersion());
        failedEvent.setDeleted(event.getDeleted());

        failedEvent.setProductName(event.getProductName());
        failedEvent.setPrice(event.getPrice());
        failedEvent.setStock(event.getStock());
        failedEvent.setProductCode(event.getProductCode());

        failedEvent.setStatus("FAILED");
        failedEvent.setErrorMessage("Kafka DLT 이동");

        failedProductEventMapper.save(failedEvent);

        System.out.println(
                "DLT 이벤트 DB 저장 완료 = productId: "
                        + event.getProductId()
        );
    }

    /**
     * DLT 이벤트 재처리
     */
    @Override
    public void retry(Long id) {

        FailedProductEvent event =
                failedProductEventMapper.findById(id);

        // 반드시 조회 결과부터 확인
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

        // DLT에 저장했던 원본 이벤트 복원
        ProductEvent retryEvent =
                new ProductEvent(
                        event.getEventType(),
                        event.getProductId(),
                        event.getVersion(),
                        event.getDeleted(),
                        event.getProductName(),
                        event.getPrice(),
                        event.getStock(),
                        event.getProductCode()
                );

        System.out.println(
                "DLT 재처리 시작 = id: "
                        + id
                        + ", productId: "
                        + event.getProductId()
                        + ", version: "
                        + event.getVersion()
        );

        /*
         * CREATE / UPDATE / DELETE 모두 sync()로 처리
         *
         * DELETE도 deleted = Y인 Document를 ES에 저장한다.
         * 따라서 saveIfNewer()에서 VERSION 비교가 가능하다.
         */
        productSearchSyncService.sync(retryEvent);

        /*
         * ES 처리가 정상적으로 끝난 경우에만
         * DLT 이벤트를 RESOLVED 처리
         */
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
