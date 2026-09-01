package org.example.erp_server.ext.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
//직렬화 필요
public class Product implements Serializable {
    private Long productId;     // 상품 ID
    private String productName; // 상품명
    private Double price;       // 가격
    private Integer stock;      // 재고
    private String productCode;
    private Long version;

    // 기본 생성자
    public Product() {}

    // 전체 필드 생성자
    public Product(Long productId, String productName, Double price, Integer stock, String productCode, Long version) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.productCode = productCode;
        this.version = version;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", productCode=" + productCode +
                ", version=" + version +
                '}';
    }
}