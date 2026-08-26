package org.example.erp_server.ext.controller;


import org.example.erp_server.ext.dto.Product;
import org.example.erp_server.ext.dto.ProductPageResponse;
import org.example.erp_server.ext.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ext/products")
public class ProductExtController {

    private final ProductService productService;

    public ProductExtController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ProductPageResponse getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        return productService.getProducts(page, size);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @GetMapping("/name/{name}")
    public Product getProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setProductId(id);
        productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.removeProduct(id);
    }
}