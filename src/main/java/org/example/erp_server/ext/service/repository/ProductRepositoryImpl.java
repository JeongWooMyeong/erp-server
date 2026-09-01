package org.example.erp_server.ext.service.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.erp_server.ext.entity.ProductEntity;

import java.util.List;

import static org.example.erp_server.ext.entity.QProductEntity.productEntity;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ProductEntity> findProductsForIndex(
            Long lastId,
            int size
    ) {

        return queryFactory
                .selectFrom(productEntity)
                .where(
                        productEntity.productId.gt(lastId)
                )
                .orderBy(
                        productEntity.productId.asc()
                )
                .limit(size)
                .fetch();
    }
}