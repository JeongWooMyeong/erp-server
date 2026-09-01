package org.example.erp_server.ext.service.impl;

import org.example.erp_server.ext.dto.Product;
import org.example.erp_server.ext.dto.ProductDocument;
import org.example.erp_server.ext.kafka.dto.ProductEvent;
import org.example.erp_server.ext.service.ProductSearchSyncService;
import org.example.erp_server.ext.service.dao.oracle.ProductMapper;
import org.example.erp_server.ext.service.repository.ProductSearchRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductSearchSyncServiceImpl
        implements ProductSearchSyncService {

    private final ProductMapper productMapper;
    private final ProductSearchRepository productSearchRepository;

    public ProductSearchSyncServiceImpl(
            ProductMapper productMapper,
            ProductSearchRepository productSearchRepository
    ) {
        this.productMapper = productMapper;
        this.productSearchRepository = productSearchRepository;
    }

//    @Override
//    public void sync(ProductEvent event) {
//
//        Product product =
//                productMapper.findById(event.getProductId());
//
//        if (product == null) {
//            throw new RuntimeException(
//                    "상품을 찾을 수 없습니다. productId="
//                            + event.getProductId()
//            );
//        }
//
//        // ===== Retry / DLT 테스트 =====
////        if (productId.equals(1000005L)) {
////            throw new RuntimeException(
////                    "Retry 테스트 : productId=" + productId
////            );
////        }
//
//
//        ProductDocument document =
//                new ProductDocument(
//                        product.getProductId(),
//                        product.getProductName(),
//                        product.getPrice(),
//                        product.getStock(),
//                        product.getProductCode()
//                );
//
//        productSearchRepository.save(document);
//    }

    @Override
    public void sync(ProductEvent event) {

        Long productId = event.getProductId();
        Long eventVersion = event.getVersion();

        // 1. ES 현재 데이터 확인
        ProductDocument current =
                productSearchRepository
                        .findById(productId)
                        .orElse(null);

        // 2. 이미 같은 버전 또는 더 최신 버전이 처리됐다면 무시
        if (current != null
                && current.getVersion() >= eventVersion) {

            System.out.println(
                    "오래된 이벤트 무시 : productId="
                            + productId
                            + ", eventVersion="
                            + eventVersion
                            + ", esVersion="
                            + current.getVersion()
            );

            return;
        }

        // 3. Oracle에서 상품 조회
        Product product =
                productMapper.findById(productId);

        if (product == null) {
            throw new RuntimeException(
                    "상품을 찾을 수 없습니다. productId=" + productId
            );
        }

        // 4. ES 문서 생성
        ProductDocument document =
                new ProductDocument(
                        product.getProductId(),
                        product.getProductName(),
                        product.getPrice(),
                        product.getStock(),
                        product.getProductCode(),
                        eventVersion
                );

        // 5. ES 저장
        productSearchRepository.save(document);
    }

    @Override
    public void delete(ProductEvent event) {

        productSearchRepository.deleteById(event.getProductId());
    }
}