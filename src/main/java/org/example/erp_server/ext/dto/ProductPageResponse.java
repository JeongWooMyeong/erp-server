package org.example.erp_server.ext.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
public class ProductPageResponse implements Serializable {

    private List<Product> content;
    private int page;
    private int size;
    private long totalCount;
    private long totalPages;
}