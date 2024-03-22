package com.atpos.atpos.inventory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private InventoryDataRepository inventoryRepository;

    InventoryService(InventoryDataRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    public List<ProductInStock> getProvidersStock(Long providerId) {
        return inventoryRepository.findByProviderId(providerId);
    }
}
