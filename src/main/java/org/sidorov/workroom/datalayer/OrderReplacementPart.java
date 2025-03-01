package org.sidorov.workroom.datalayer;

public class OrderReplacementPart {
    private int orderId;
    private int partId;
    private int numberParts;
    private String partName;
    private int cost;
    private int inStock;

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public int getNumberParts() {
        return numberParts;
    }

    public void setNumberParts(int numberParts) {
        this.numberParts = numberParts;
    }
}
