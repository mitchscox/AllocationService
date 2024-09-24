package org.AItest.backoffice.AllocationService.service;

import org.AItest.backoffice.AllocationService.model.Allocation;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AllocationService {

    // In-memory map to store allocations for each trade (tradeId -> clientId, bondQuantity)
    private final Map<String, Map<String, Integer>> allocationMap = new HashMap<>();

    // Method to perform allocation
    public String allocateBonds(String tradeId, List<Allocation> allocations) {
        Map<String, Integer> clientAllocations = new HashMap<>();

        // Store the allocation for each client
        for (Allocation allocation : allocations) {
            clientAllocations.put(allocation.getClientId(), allocation.getBondQuantity());
        }

        // Store the allocation for the tradeId
        allocationMap.put(tradeId, clientAllocations);

        return "Allocation successful for tradeId: " + tradeId;
    }

    // Method to retrieve allocations for a given tradeId
    public Map<String, Integer> getAllocation(String tradeId) {
        return allocationMap.getOrDefault(tradeId, new HashMap<>());
    }
}