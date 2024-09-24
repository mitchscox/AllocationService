package org.AItest.backoffice.AllocationService.service;

import org.AItest.backoffice.AllocationService.model.Allocation;
import org.AItest.backoffice.AllocationService.util.FIXParser;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AllocationService {

    // In-memory map to store allocations for each trade (tradeId -> clientId, bondQuantity)
    private final Map<String, Map<String, Allocation>> allocationMap = new HashMap<>();

    // Method to perform allocation from a FIX message
    public String allocateBonds(String fixMessage) {
        Map<String, String> fixFields = FIXParser.parseFIXMessage(fixMessage);

        String tradeId = fixFields.get("11");  // Tag 11: ClOrdID (Client Order ID)
        String clientId = fixFields.get("1");  // Tag 1: Account (Client ID)
        String instrument = fixFields.get("55"); // Tag 55: Instrument (Bond Symbol)
        int bondQuantity = Integer.parseInt(fixFields.get("38"));  // Tag 38: OrderQty

        // Create an allocation object
        Allocation allocation = new Allocation(tradeId, clientId, instrument, bondQuantity);

        // Store the allocation in-memory (you could replace this with a database)
        allocationMap.computeIfAbsent(tradeId, k -> new HashMap<>()).put(clientId, allocation);

        return "35=8\u0001" + "11=" + tradeId + "\u0001" + "1=" + clientId + "\u0001" + "55=" + instrument + "\u0001" +
                "38=" + bondQuantity + "\u0001" + "39=2\u0001";  // Execution Report (Tag 35=8), Status 39=Filled
    }

    // Method to retrieve allocations for a given tradeId
    public Map<String, Allocation> getAllocation(String tradeId) {
        return allocationMap.getOrDefault(tradeId, new HashMap<>());
    }
}