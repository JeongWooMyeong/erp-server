package org.example.erp_server.ext.service;


import org.example.erp_server.ext.dto.Product;
import org.example.erp_server.ext.dto.ProductPageResponse;

public interface ProductService {
    ProductPageResponse getProducts(int page, int size);
    Product getProduct(Long productId);
    Product getProductByName(String name);
    void addProduct(Product product);
    void updateProduct(Product product);
    void removeProduct(Long productId);
}