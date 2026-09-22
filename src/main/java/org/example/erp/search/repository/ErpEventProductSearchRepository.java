package org.example.erp.search.repository;

import org.example.erp.search.dto.ErpEventProductSearchDto;

import java.util.List;

public interface ErpEventProductSearchRepository {

    List<ErpEventProductSearchDto> findEventProductSearchData();
}