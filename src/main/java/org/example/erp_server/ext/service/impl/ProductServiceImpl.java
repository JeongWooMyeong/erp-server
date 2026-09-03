package org.example.erp_server.ext.service.impl;


import org.example.erp_server.ext.dto.Product;
import org.example.erp_server.ext.dto.ProductPageResponse;
import org.example.erp_server.ext.kafka.dto.ProductEvent;
import org.example.erp_server.ext.kafka.producer.ProductEventProducer;
import org.example.erp_server.ext.service.ProductService;
import org.example.erp_server.ext.service.dao.oracle.ProductMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductEventProducer productEventProducer;

    public ProductServiceImpl(ProductMapper productMapper, ProductEventProducer productEventProducer) {
        this.productMapper = productMapper;
        this.productEventProducer = productEventProducer;
    }

    // 전체 조회
    @Override
    @Cacheable(
            value = "productList",
            key = "#page + ':' + #size"
    )
    public ProductPageResponse getProducts(int page, int size) {

        // 잘못된 페이지 방지
        if (page < 0) {
            page = 0;
        }

        if (size <= 0) {
            size = 50;
        }

        // Oracle OFFSET 계산
        int offset = page * size;

        // 현재 페이지 상품 조회
        List<Product> products =
                productMapper.findPage(offset, size);

        // 전체 상품 개수
        long totalCount =
                productMapper.countProducts();

        // 전체 페이지 수
        long totalPages =
                (totalCount + size - 1) / size;

        return new ProductPageResponse(
                products,
                page,
                size,
                totalCount,
                totalPages
        );
    }

    // ID 조회
    @Override
    @Cacheable(value = "product", key = "#productId")
    public Product getProduct(Long productId) {
        System.out.println("DB에서 상품 조회");
        return productMapper.findById(productId);
    }

    // 이름 조회
    @Override
    @Cacheable(value = "productByName", key = "#name")
    public Product getProductByName(String name) {
        System.out.println("DB에서 이름 조회");
        return productMapper.findByName(name);
    }

    // 등록
    @Override
    @CacheEvict(value = "productList", allEntries = true)
    public void addProduct(Product product) {
        // 1. Oracle 등록
        productMapper.insert(product);

        System.out.println(
                "생성된 상품 ID = " + product.getProductId()
        );

        // 2. Oracle 등록 성공 후 Kafka 이벤트 발행
        ProductEvent event =
                new ProductEvent(
                        "CREATE",
                        product.getProductId(),
                        product.getVersion(),
                        "N",
                        product.getProductName(),
                        product.getPrice(),
                        product.getStock(),
                        product.getProductCode()
                );

        productEventProducer.send(event);
    }

    // 수정
    @Override
    @Caching(evict = {
            @CacheEvict(
                    value = "product",
                    key = "#product.productId"
            ),
            @CacheEvict(
                    value = "productList",
                    allEntries = true
            ),
            @CacheEvict(
                    value = "productByName",
                    allEntries = true
            )
    })
    public void updateProduct(Product product) {

        int result = productMapper.update(product);

        if (result == 0) {
            throw new RuntimeException("상품 수정 충돌");
        }

        // DB UPDATE에서 VERSION + 1 되었으므로
        Long newVersion = product.getVersion() + 1;

        ProductEvent event =
                new ProductEvent(
                        "UPDATE",
                        product.getProductId(),
                        newVersion,
                        "N",
                        product.getProductName(),
                        product.getPrice(),
                        product.getStock(),
                        product.getProductCode()
                );

        productEventProducer.send(event);
    }

    // 삭제
    // 삭제
    @Override
    @Caching(evict = {
            @CacheEvict(value = "product", key = "#productId"),
            @CacheEvict(value = "productList", allEntries = true),
            @CacheEvict(value = "productByName", allEntries = true)
    })
    public void removeProduct(Long productId) {

        // 1. 삭제 전에 상품 조회
        Product product =
                productMapper.findById(productId);

        if (product == null) {
            throw new RuntimeException(
                    "상품을 찾을 수 없습니다. productId=" + productId
            );
        }

        // 2. 조회 당시 VERSION
        Long version = product.getVersion();

        // 3. 삭제 대상 객체 생성
        Product deleteProduct = new Product();
        deleteProduct.setProductId(productId);
        deleteProduct.setVersion(version);

        // 4. Soft Delete + VERSION 증가
        int result = productMapper.delete(deleteProduct);

        // 다른 요청이 먼저 수정/삭제했다면 실패
        if (result == 0) {
            throw new RuntimeException("상품 삭제 충돌");
        }

        // 5. 삭제 후 실제 DB 데이터 조회
        Product deletedProduct =
                productMapper.findByIdIncludingDeleted(productId);

        // 6. Kafka 이벤트
        ProductEvent event =
                new ProductEvent(
                        "DELETE",
                        deletedProduct.getProductId(),
                        deletedProduct.getVersion(),
                        "Y",
                        null,
                        null,
                        null,
                        null
                );

        productEventProducer.send(event);
    }
}