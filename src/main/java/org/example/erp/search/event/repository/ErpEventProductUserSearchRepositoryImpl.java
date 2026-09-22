package org.example.erp.search.event.repository;

import lombok.RequiredArgsConstructor;
import org.example.erp.search.event.document.ErpEventProductDocument;
import org.example.erp.search.event.dto.ErpEventProductSearchRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ErpEventProductUserSearchRepositoryImpl
        implements ErpEventProductUserSearchRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<ErpEventProductDocument> search(
            ErpEventProductSearchRequest request
    ) {

        NativeQuery query = NativeQuery.builder()
                .withQuery(q -> q.bool(b -> {

                    // 검색어
                    if (request.getKeyword() != null
                            && !request.getKeyword().isBlank()) {

                        b.must(m -> m.multiMatch(mm -> mm
                                .query(request.getKeyword())
                                .fields(
                                        "eventName",
                                        "productName",
                                        "categoryName"
                                )
                        ));
                    }

                    // 회사
                    if (request.getCompanyId() != null) {
                        b.filter(f -> f.term(t -> t
                                .field("companyId")
                                .value(request.getCompanyId())
                        ));
                    }

                    // 점포
                    if (request.getStoreId() != null) {
                        b.filter(f -> f.term(t -> t
                                .field("storeId")
                                .value(request.getStoreId())
                        ));
                    }

                    // 카테고리
                    if (request.getCategoryId() != null) {
                        b.filter(f -> f.term(t -> t
                                .field("categoryId")
                                .value(request.getCategoryId())
                        ));
                    }

                    // 행사
                    if (request.getEventId() != null) {
                        b.filter(f -> f.term(t -> t
                                .field("eventId")
                                .value(request.getEventId())
                        ));
                    }

                    // 행사 활성 여부
                    if (request.getActiveYn() != null
                            && !request.getActiveYn().isBlank()) {

                        b.filter(f -> f.term(t -> t
                                .field("activeYn")
                                .value(request.getActiveYn())
                        ));
                    }

                    return b;
                }))
                .build();

        return elasticsearchOperations
                .search(query, ErpEventProductDocument.class)
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }
}