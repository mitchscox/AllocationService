package org.AItest.backoffice.AllocationService;


import org.AItest.backoffice.AllocationService.service.AllocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AllocationControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(AllocationControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AllocationService allocationService;

    private String fixMessage;

    @BeforeEach
    public void setup() {
        logger.info("Setting up test data...");

        // Create a sample FIX message
        fixMessage = "35=D\u000111=TRADE123\u000138=1000\u000155=US1234567890\u00011=CLIENT123\u0001";

        logger.info("Test FIX message created: {}", fixMessage);
    }

    @Test
    public void testAllocateBonds() throws Exception {
        logger.info("Running test for allocateBonds()");

        mockMvc.perform(post("/api/allocation/fix")
                        .content(fixMessage)
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("35=8\u000111=TRADE123\u00011=CLIENT123\u000155=US1234567890\u000138=1000\u000139=2\u0001"));

        logger.info("Test allocateBonds() completed successfully");
    }

    @Test
    public void testGetAllocation() throws Exception {
        logger.info("Running test for getAllocation()");

        // First, allocate bonds (POST request)
        mockMvc.perform(post("/api/allocation/fix")
                        .content(fixMessage)
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());

        logger.info("Bonds allocated, now fetching allocation with tradeId=TRADE123");

        // Test GET /api/allocation/fix/{tradeId}
        mockMvc.perform(get("/api/allocation/fix/TRADE123"))
                .andExpect(status().isOk())
                .andExpect(content().string("35=8\u000111=TRADE123\u00011=CLIENT123\u000155=US1234567890\u000138=1000\u000139=2\u0001"));

        logger.info("Test getAllocation() completed successfully");
    }
}
