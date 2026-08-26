package org.example.erp_server.ext.service;

public interface ProductSearchSyncService {

    void sync(Long productId);

    void delete(Long productId);
}