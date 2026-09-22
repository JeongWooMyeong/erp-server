package org.example.erp.search.repository;

import org.example.erp.search.dto.ErpProductSearchDto;

import java.util.List;

public interface ErpProductSearchRepository {

    List<ErpProductSearchDto> findProductSearchData();
}