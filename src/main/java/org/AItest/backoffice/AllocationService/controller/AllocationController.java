package org.AItest.backoffice.AllocationService.controller;

import org.AItest.backoffice.AllocationService.model.Allocation;
import org.AItest.backoffice.AllocationService.service.AllocationService;
import org.AItest.backoffice.AllocationService.util.FIXParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/api/allocation")
public class AllocationController {

    @Autowired
    private AllocationService allocationService;

    // Endpoint to allocate bonds via FIX message
    @PostMapping("/fix")
    public String allocateBonds(@RequestBody String fixMessage) {
        return allocationService.allocateBonds(fixMessage);
    }

    // Endpoint to retrieve allocations for a given tradeId in FIX format
    @GetMapping("/fix/{tradeId}")
    public String getAllocation(@PathVariable String tradeId) {
        Map<String, Allocation> allocations = allocationService.getAllocation(tradeId);

        // Build a FIX message from the allocation
        StringBuilder fixResponse = new StringBuilder();
        fixResponse.append("35=8\u0001"); // Execution Report

        for (Map.Entry<String, Allocation> entry : allocations.entrySet()) {
            Allocation allocation = entry.getValue();
            fixResponse.append("11=").append(allocation.getTradeId()).append("\u0001")  // ClOrdID (Trade ID)
                    .append("1=").append(allocation.getClientId()).append("\u0001")  // Account (Client ID)
                    .append("55=").append(allocation.getInstrument()).append("\u0001")  // Symbol (Instrument)
                    .append("38=").append(allocation.getBondQuantity()).append("\u0001")  // OrderQty (Bond Quantity)
                    .append("39=2\u0001");  // ExecType (Filled)
        }

        return fixResponse.toString();
    }
}