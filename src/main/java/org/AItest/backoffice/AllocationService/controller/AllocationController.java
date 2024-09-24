package org.AItest.backoffice.AllocationService.controller;

import org.AItest.backoffice.AllocationService.model.Allocation;
import org.AItest.backoffice.AllocationService.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/allocation")
public class AllocationController {

    @Autowired
    private AllocationService allocationService;

    // Endpoint to allocate bonds
    @PostMapping("/{tradeId}")
    public String allocateBonds(@PathVariable String tradeId, @RequestBody List<Allocation> allocations) {
        return allocationService.allocateBonds(tradeId, allocations);
    }

    // Endpoint to retrieve allocations for a given tradeId
    @GetMapping("/{tradeId}")
    public Map<String, Integer> getAllocation(@PathVariable String tradeId) {
        return allocationService.getAllocation(tradeId);
    }
}
