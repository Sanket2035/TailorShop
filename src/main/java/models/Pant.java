package models;

import java.math.BigDecimal;

public class Pant {
    private int pantID;
    private int orderID;
    private String pantType;
    private BigDecimal price;
    private BigDecimal height;
    private BigDecimal waist;
    private BigDecimal seat;
    private BigDecimal thigh;
    private BigDecimal knee;
    private BigDecimal bottom;
    private BigDecimal hang;
    private String other;

    public Pant() {}

    public Pant(int orderID, String pantType, BigDecimal price) {
        this.orderID = orderID;
        this.pantType = pantType;
        this.price = price;
    }

    // Getters and Setters
    public int getPantID() { return pantID; }
    public void setPantID(int pantID) { this.pantID = pantID; }

    public int getOrderID() { return orderID; }
    public void setOrderID(int orderID) { this.orderID = orderID; }

    public String getPantType() { return pantType; }
    public void setPantType(String pantType) { this.pantType = pantType; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public BigDecimal getHeight() { return height; }
    public void setHeight(BigDecimal height) { this.height = height; }

    public BigDecimal getWaist() { return waist; }
    public void setWaist(BigDecimal waist) { this.waist = waist; }

    public BigDecimal getSeat() { return seat; }
    public void setSeat(BigDecimal seat) { this.seat = seat; }

    public BigDecimal getThigh() { return thigh; }
    public void setThigh(BigDecimal thigh) { this.thigh = thigh; }

    public BigDecimal getKnee() { return knee; }
    public void setKnee(BigDecimal knee) { this.knee = knee; }

    public BigDecimal getBottom() { return bottom; }
    public void setBottom(BigDecimal bottom) { this.bottom = bottom; }

    public BigDecimal getHang() { return hang; }
    public void setHang(BigDecimal hang) { this.hang = hang; }

    public String getOther() { return other; }
    public void setOther(String other) { this.other = other; }
}