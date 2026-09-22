package org.example.erp.search.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.erp.search.dto.ErpProductSearchDto;
import org.example.erp.search.entity.QErpCategory;
import org.example.erp.search.entity.QErpProductMaster;
import org.example.erp.search.entity.QErpStore;
import org.example.erp.search.entity.QErpStoreProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ErpProductSearchRepositoryImpl
        implements ErpProductSearchRepository {

    private final JPAQueryFactory queryFactory;

    private final QErpStoreProduct storeProduct =
            QErpStoreProduct.erpStoreProduct;

    private final QErpStore store =
            QErpStore.erpStore;

    private final QErpProductMaster productMaster =
            QErpProductMaster.erpProductMaster;

    private final QErpCategory category =
            QErpCategory.erpCategory;

    @Override
    public List<ErpProductSearchDto> findProductSearchData() {

        return queryFactory
                .select(Projections.constructor(
                        ErpProductSearchDto.class,

                        storeProduct.id.companyId,
                        storeProduct.id.storeId,
                        store.storeName,
                        store.location,

                        storeProduct.id.productCode,
                        productMaster.productName,

                        category.categoryId,
                        category.categoryName,
                        category.categoryLevel,

                        storeProduct.stock,
                        storeProduct.price
                ))
                .from(storeProduct)

                .join(store)
                .on(
                        store.storeId.eq(storeProduct.id.storeId)
                                .and(store.companyId.eq(storeProduct.id.companyId))
                )

                .join(productMaster)
                .on(
                        productMaster.productCode.eq(
                                storeProduct.id.productCode
                        )
                )

                .leftJoin(category)
                .on(
                        category.categoryId.eq(
                                productMaster.categoryId
                        )
                )

                .fetch();
    }
}