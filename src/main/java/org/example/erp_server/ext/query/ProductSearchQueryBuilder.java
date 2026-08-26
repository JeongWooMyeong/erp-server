package org.example.erp_server.ext.query;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import org.example.erp_server.ext.dto.ProductSearchCondition;
import org.springframework.stereotype.Component;

@Component
public class ProductSearchQueryBuilder {

    public Query build(ProductSearchCondition condition) {

        String keyword = condition.getKeyword();

        if (keyword != null && !keyword.trim().isEmpty()) {

            return Query.of(q -> q
                    .wildcard(w -> w
                            .field("productName.keyword")
                            .value("*" + keyword.trim() + "*")
                    )
            );
        }

        return Query.of(q ->
                q.matchAll(m -> m)
        );
    }
}