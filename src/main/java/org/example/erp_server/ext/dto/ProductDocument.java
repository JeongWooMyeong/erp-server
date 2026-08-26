package org.example.erp_server.ext.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Getter
@Setter
@Document(indexName = "products")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDocument {

    @Id
    private Long productId;

    @MultiField(
            mainField = @Field(type = FieldType.Text),
            otherFields = {
                    @InnerField(
                            suffix = "keyword",
                            type = FieldType.Keyword
                    )
            }
    )
    private String productName;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Integer)
    private Integer stock;

    public ProductDocument() {
    }

    public ProductDocument(Long productId,
                           String productName,
                           Double price,
                           Integer stock) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }
}