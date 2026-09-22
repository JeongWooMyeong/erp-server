package org.example.erp.search.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "erp_products")
public class ErpProductDocument {

    @Id
    private String id;

    @Field(type = FieldType.Long)
    private Long companyId;

    @Field(type = FieldType.Long)
    private Long storeId;

    @Field(type = FieldType.Keyword)
    private String storeName;

    @Field(type = FieldType.Text)
    private String location;

    @Field(type = FieldType.Keyword)
    private String productCode;

    @Field(type = FieldType.Text)
    private String productName;

    @Field(type = FieldType.Long)
    private Long categoryId;

    @Field(type = FieldType.Text)
    private String categoryName;

    @Field(type = FieldType.Integer)
    private Integer categoryLevel;

    @Field(type = FieldType.Integer)
    private Integer stock;

    @Field(type = FieldType.Double)
    private BigDecimal price;
}