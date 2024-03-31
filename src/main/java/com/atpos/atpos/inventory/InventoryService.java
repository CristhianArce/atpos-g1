package com.atpos.atpos.inventory;

import com.atpos.atpos.user.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private InventoryDataRepository inventoryRepository;
    private UserRepository userRepository;

    InventoryService(InventoryDataRepository inventoryRepository, UserRepository userRepository){
        this.inventoryRepository = inventoryRepository;
        this.userRepository = userRepository;
    }

    public List<ProductInStock> getProvidersStock(Long providerId) {
        var requester = SecurityContextHolder.getContext().getAuthentication().getName();
        var inventoryOwner = userRepository.findById(providerId);
        if (inventoryOwner.isEmpty()) throw new IllegalArgumentException("There is not inventory for the provided parameters");
        if (!requester.equals(inventoryOwner.get().getUsername())) throw new IllegalArgumentException("The Inventory is not available for user: " + requester);
        return inventoryRepository.findByProviderId(providerId);
    }
}
