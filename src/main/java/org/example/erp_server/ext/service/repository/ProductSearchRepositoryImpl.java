package org.example.erp_server.ext.service.repository;

import lombok.RequiredArgsConstructor;
import org.example.erp_server.ext.dto.ProductDocument;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductSearchRepositoryImpl
        implements ProductSearchRepositoryCustom {

    private final ElasticsearchOperations operations;

    @Override
    public void saveIfNewer(ProductDocument document) {

        ProductDocument current =
                operations.get(
                        String.valueOf(document.getProductId()),
                        ProductDocument.class
                );

        if (current != null
                && current.getVersion() >= document.getVersion()) {

            return;
        }

        operations.save(document);
    }

    @Override
    public void deleteIfNewer(
            Long productId,
            Long version
    ) {

        ProductDocument current =
                operations.get(
                        String.valueOf(productId),
                        ProductDocument.class
                );

        if (current == null) {
            return;
        }

        if (current.getVersion() >= version) {
            return;
        }

        operations.delete(
                String.valueOf(productId),
                ProductDocument.class
        );
    }
}