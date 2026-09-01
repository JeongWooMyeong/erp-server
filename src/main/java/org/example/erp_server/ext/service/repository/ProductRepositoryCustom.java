package org.example.erp_server.ext.service.repository;

import org.example.erp_server.ext.entity.ProductEntity;

import java.util.List;

public interface ProductRepositoryCustom {

    List<ProductEntity> findProductsForIndex(
            Long lastId,
            int size
    );
}