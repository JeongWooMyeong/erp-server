package org.example.erp_server.ext.service.repository;

import org.example.erp_server.ext.dto.ProductDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductSearchRepository
        extends ElasticsearchRepository<ProductDocument, Long>,
        ProductSearchRepositoryCustom {

    Page<ProductDocument> findByProductNameContaining(
            String keyword,
            Pageable pageable
    );
    @Query("""
    {
      "wildcard": {
        "productName.keyword": {
          "value": "*?0*"
        }
      }
    }
    """)
    List<ProductDocument> searchByProductName(String keyword);
}