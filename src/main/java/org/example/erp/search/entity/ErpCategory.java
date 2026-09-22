package org.example.erp.search.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CATEGORY", schema = "ERP")
@Getter
@NoArgsConstructor
public class ErpCategory {

    @Id
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "CATEGORY_NAME", nullable = false, length = 100)
    private String categoryName;

    @Column(name = "CATEGORY_LEVEL", nullable = false)
    private Integer categoryLevel;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "USE_YN", nullable = false, length = 1)
    private String useYn;

}
