package org.example.erp.search.event.repository;

import org.example.erp.search.event.dto.ErpEventProductSearchDto;

import java.util.List;

public interface ErpEventProductSearchRepository {

    List<ErpEventProductSearchDto> findEventProductSearchData();
}