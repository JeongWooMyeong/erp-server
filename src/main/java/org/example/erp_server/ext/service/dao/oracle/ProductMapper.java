package org.example.erp_server.ext.service.dao.oracle;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.erp_server.ext.dto.Product;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> findAll();
    Product findById(Long productId);
    Product findByName(@Param("productname") String name);
    void insert(Product product);
    int update(Product product);
    int delete(Product product);
    List<Product> findProductsForIndex(
            @Param("lastId") Long lastId,
            @Param("size") int size
    );

    // 페이지 조회
    List<Product> findPage(
            @Param("offset") int offset,
            @Param("size") int size
    );

    // 전체 상품 개수
    long countProducts();
    Product findByIdIncludingDeleted(Long productId);
}