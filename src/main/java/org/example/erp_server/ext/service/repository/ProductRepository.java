package org.example.erp_server.ext.service.repository;

import org.example.erp_server.ext.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>,
        ProductRepositoryCustom {
}