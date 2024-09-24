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

    // Endpoint to allocate bonds via FIX message
    @PostMapping("/fix")
    public String allocateBonds(@RequestBody String fixMessage) {
        return allocationService.allocateBonds(fixMessage);
    }

    // Endpoint to retrieve allocations for a given tradeId
    @GetMapping("/{tradeId}")
    public Map<String, Object> getAllocation(@PathVariable String tradeId) {
        return allocationService.getAllocation(tradeId);
    }
}
