package org.AItest.backoffice.AllocationService.model;

public class Allocation {
    private String tradeId;
    private String clientId;
    private int bondQuantity;

    // Constructors
    public Allocation() {}

    public Allocation(String tradeId, String clientId, int bondQuantity) {
        this.tradeId = tradeId;
        this.clientId = clientId;
        this.bondQuantity = bondQuantity;
    }

    // Getters and Setters
    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getBondQuantity() {
        return bondQuantity;
    }

    public void setBondQuantity(int bondQuantity) {
        this.bondQuantity = bondQuantity;
    }
}

