package org.example.erp.search.event.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "erp_event_products")
public class ErpEventProductDocument {

    @Id
    private String id;

    @Field(type = FieldType.Long)
    private Long companyId;

    @Field(type = FieldType.Long)
    private Long storeId;

    @Field(type = FieldType.Long)
    private Long eventId;

    @Field(type = FieldType.Text)
    private String eventName;

    @Field(type = FieldType.Date)
    private LocalDateTime startDate;

    @Field(type = FieldType.Date)
    private LocalDateTime endDate;

    @Field(type = FieldType.Keyword)
    private String activeYn;

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

    @Field(type = FieldType.Keyword)
    private String storeName;

    @Field(type = FieldType.Text)
    private String location;

    @Field(type = FieldType.Integer)
    private Integer stock;

    @Field(type = FieldType.Double)
    private BigDecimal price;

    @Field(type = FieldType.Double)
    private BigDecimal discountRate;

    @Field(type = FieldType.Double)
    private BigDecimal specialPrice;
}