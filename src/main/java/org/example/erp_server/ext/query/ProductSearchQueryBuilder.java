package org.example.erp_server.ext.query;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import org.example.erp_server.ext.dto.ProductSearchCondition;
import org.springframework.stereotype.Component;

@Component
public class ProductSearchQueryBuilder {

    public Query build(ProductSearchCondition condition) {

        String keyword = condition.getKeyword();
        String field = condition.getField();

        // 검색어가 없으면 전체 상품
        if (keyword == null || keyword.trim().isEmpty()) {

            return Query.of(q ->
                    q.matchAll(m -> m)
            );
        }

        keyword = keyword.trim();

        // 상품명 검색
        if ("productName".equals(field)) {

            String searchKeyword = keyword;

            return Query.of(q ->
                    q.wildcard(w ->
                            w.field("productName.keyword")
                                    .value("*" + searchKeyword + "*")
                    )
            );
        }

        // 상품코드 검색
        if ("productCode".equals(field)) {

            String searchKeyword = keyword;

            return Query.of(q ->
                    q.wildcard(w ->
                            w.field("productCode.keyword")
                                    .value("*" + searchKeyword + "*")
                    )
            );
        }

        // 잘못된 필터
        return Query.of(q ->
                q.matchAll(m -> m)
        );
    }

}
