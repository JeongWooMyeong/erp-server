package org.example.erp_server.ext.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;

import org.example.erp_server.ext.dto.Product;
import org.example.erp_server.ext.dto.ProductDocument;
import org.example.erp_server.ext.dto.ProductSearchCondition;
import org.example.erp_server.ext.dto.ProductSearchResponse;
import org.example.erp_server.ext.entity.ProductEntity;
import org.example.erp_server.ext.query.ProductSearchQueryBuilder;
import org.example.erp_server.ext.service.ProductSearchService;
import org.example.erp_server.ext.service.dao.oracle.ProductMapper;
import org.example.erp_server.ext.service.repository.ProductRepository;
import org.example.erp_server.ext.service.repository.ProductSearchRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {

    private final ProductMapper productMapper;
    private final ProductSearchRepository repository;
    private final ElasticsearchClient elasticsearchClient;
    private final ProductSearchQueryBuilder queryBuilder;
    private final ProductRepository productRepository;

    public ProductSearchServiceImpl(
            ProductMapper productMapper,
            ProductSearchRepository repository,
            ElasticsearchClient elasticsearchClient,
            ProductSearchQueryBuilder queryBuilder,
            ProductRepository productRepository
    ) {
        this.productMapper = productMapper;
        this.repository = repository;
        this.elasticsearchClient = elasticsearchClient;
        this.queryBuilder = queryBuilder;
        this.productRepository = productRepository;
    }


    // Elasticsearch 검색 + Offset Pagination
    @Override
    @Cacheable(
            value = "productSearch",
            key = "#condition.field + ':' + #condition.keyword + ':' + #page + ':' + #size"
    )
    public ProductSearchResponse search(
            ProductSearchCondition condition,
            int page,
            int size
    ) throws IOException {

        // 페이지 번호 및 페이지 크기 검증
        final int safePage =
                Math.max(page, 0);

        final int safeSize =
                size > 0 ? size : 50;


        // Elasticsearch Query 생성
        Query query =
                queryBuilder.build(condition);


        // Offset 계산
        int from =
                safePage * safeSize;


        // Elasticsearch 검색
        SearchResponse<ProductDocument> response =
                elasticsearchClient.search(s -> {

                    s.index("products")
                            .from(from)
                            .size(safeSize)

                            // 전체 검색 결과 수 계산
                            .trackTotalHits(
                                    t -> t.enabled(true)
                            )

                            // 검색 조건
                            .query(query)

                            // 상품 ID 기준 정렬
                            .sort(sort -> sort
                                    .field(field -> field
                                            .field("productId")
                                            .order(SortOrder.Asc)
                                    )
                            );

                    return s;

                }, ProductDocument.class);


        // 전체 검색 결과 수
        long totalCount =
                response.hits().total() != null
                        ? response.hits().total().value()
                        : 0;


        // 전체 페이지 수
        long totalPages =
                (totalCount + safeSize - 1)
                        / safeSize;


        // 검색 결과
        List<ProductDocument> products =
                new ArrayList<>();


        for (Hit<ProductDocument> hit :
                response.hits().hits()) {

            if (hit.source() != null) {

                products.add(
                        hit.source()
                );
            }
        }


        // 다음 페이지 존재 여부
        boolean hasNext =
                safePage < totalPages - 1;


        return new ProductSearchResponse(
                products,
                safePage,
                safeSize,
                totalCount,
                totalPages,
                hasNext
        );
    }


    // Oracle → Elasticsearch 초기 적재
    @Override
    public void initialize() {

        Long lastId = 0L;
        int batchSize = 10000;

        while (true) {

            // Oracle에서 10,000건씩 조회
//            List<Product> products =
//                    productMapper.findProductsForIndex(
//                            lastId,
//                            batchSize
//                    );
            List<ProductEntity> products =
                    productRepository.findProductsForIndex(
                            lastId,
                            batchSize
                    );

            // 더 이상 데이터가 없으면 종료
            if (products.isEmpty()) {
                break;
            }


            // Product → ProductDocument 변환
            List<ProductDocument> documents =
                    products.stream()
                            .map(product ->
                                    new ProductDocument(
                                            product.getProductId(),
                                            product.getProductName(),
                                            product.getPrice(),
                                            product.getStock(),
                                            product.getProductCode(),
                                            product.getVersion()
                                    )
                            )
                            .toList();


            // Elasticsearch에 저장
            repository.saveAll(documents);


            // 마지막 상품 ID 저장
            lastId =
                    products
                            .get(products.size() - 1)
                            .getProductId();


            System.out.println(
                    "ES 초기 적재 완료 : lastId = " + lastId
            );
        }
    }
}
