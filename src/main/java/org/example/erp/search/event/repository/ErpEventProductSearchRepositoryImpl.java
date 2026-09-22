package org.example.erp.search.event.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.erp.search.event.dto.ErpEventProductSearchDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.example.erp.search.entity.QErpCategory.erpCategory;
import static org.example.erp.search.entity.QErpEvent.erpEvent;
import static org.example.erp.search.entity.QErpEventProduct.erpEventProduct;
import static org.example.erp.search.entity.QErpProductMaster.erpProductMaster;
import static org.example.erp.search.entity.QErpStore.erpStore;
import static org.example.erp.search.entity.QErpStoreProduct.erpStoreProduct;

@Repository
@RequiredArgsConstructor
public class ErpEventProductSearchRepositoryImpl
        implements ErpEventProductSearchRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ErpEventProductSearchDto> findEventProductSearchData() {

        return queryFactory
                .select(Projections.constructor(
                        ErpEventProductSearchDto.class,

                        // 회사 / 점포
                        erpEventProduct.id.companyId,
                        erpEventProduct.id.storeId,

                        // 행사
                        erpEventProduct.id.eventId,
                        erpEvent.eventName,
                        erpEvent.startDate,
                        erpEvent.endDate,
                        erpEvent.activeYn,

                        // 상품
                        erpEventProduct.id.productCode,
                        erpProductMaster.productName,

                        // 카테고리
                        erpCategory.categoryId,
                        erpCategory.categoryName,
                        erpCategory.categoryLevel,

                        // 점포
                        erpStore.storeName,
                        erpStore.location,

                        // 점포 상품
                        erpStoreProduct.stock,
                        erpStoreProduct.price,

                        // 행사 상품
                        erpEventProduct.discountRate,
                        erpEventProduct.specialPrice
                ))
                .from(erpEventProduct)

                .join(erpEvent)
                .on(
                        erpEvent.eventId.eq(
                                erpEventProduct.id.eventId
                        )
                )

                .join(erpProductMaster)
                .on(
                        erpProductMaster.productCode.eq(
                                erpEventProduct.id.productCode
                        )
                )

                .join(erpStore)
                .on(
                        erpStore.storeId.eq(
                                        erpEventProduct.id.storeId
                                )
                                .and(
                                        erpStore.companyId.eq(
                                                erpEventProduct.id.companyId
                                        )
                                )
                )

                .join(erpStoreProduct)
                .on(
                        erpStoreProduct.id.companyId.eq(
                                        erpEventProduct.id.companyId
                                )
                                .and(
                                        erpStoreProduct.id.storeId.eq(
                                                erpEventProduct.id.storeId
                                        )
                                )
                                .and(
                                        erpStoreProduct.id.productCode.eq(
                                                erpEventProduct.id.productCode
                                        )
                                )
                )

                .leftJoin(erpCategory)
                .on(
                        erpCategory.categoryId.eq(
                                erpProductMaster.categoryId
                        )
                )

                .fetch();
    }
}