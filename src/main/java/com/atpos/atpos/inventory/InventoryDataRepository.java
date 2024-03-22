package com.atpos.atpos.inventory;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InventoryDataRepository extends CrudRepository<ProductInStock, Long> {
    List<ProductInStock> findByProviderId(Long providerId);
}
