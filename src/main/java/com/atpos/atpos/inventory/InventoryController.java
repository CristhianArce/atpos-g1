package com.atpos.atpos.inventory;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PreAuthorize("hasRole('PROVIDER')")
    @GetMapping("/inventory/{providerId}")
    public List<ProductInStock> getProductsByProviderId(@PathVariable Long providerId) {
        return inventoryService.getProvidersStock(providerId);
    }
}
