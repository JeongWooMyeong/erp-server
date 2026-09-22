package org.example.erp.search.product.repository;

import org.example.erp.search.product.dto.ErpProductSearchDto;

import java.util.List;

public interface ErpProductSearchRepository {

    List<ErpProductSearchDto> findProductSearchData();
}