package org.example.erp.search.product.repository;

import lombok.RequiredArgsConstructor;
import org.example.erp.search.product.document.ErpProductDocument;
import org.example.erp.search.product.dto.ErpProductSearchRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ErpProductUserSearchRepositoryImpl
        implements ErpProductUserSearchRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<ErpProductDocument> search(ErpProductSearchRequest request) {

        Query query = NativeQuery.builder()
                .withQuery(q -> q
                        .bool(b -> {

                            // 검색어
                            if (request.getKeyword() != null
                                    && !request.getKeyword().isBlank()) {

                                b.must(m -> m
                                        .multiMatch(mm -> mm
                                                .query(request.getKeyword())
                                                .fields(
                                                        "productName",
                                                        "productCode",
                                                        "categoryName"
                                                )
                                        )
                                );
                            }

                            // 회사 필터
                            if (request.getCompanyId() != null) {
                                b.filter(f -> f
                                        .term(t -> t
                                                .field("companyId")
                                                .value(request.getCompanyId())
                                        )
                                );
                            }

                            // 점포 필터
                            if (request.getStoreId() != null) {
                                b.filter(f -> f
                                        .term(t -> t
                                                .field("storeId")
                                                .value(request.getStoreId())
                                        )
                                );
                            }

                            // 카테고리 필터
                            if (request.getCategoryId() != null) {
                                b.filter(f -> f
                                        .term(t -> t
                                                .field("categoryId")
                                                .value(request.getCategoryId())
                                        )
                                );
                            }

                            return b;
                        })
                )
                .build();

        return elasticsearchOperations
                .search(query, ErpProductDocument.class)
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }
}