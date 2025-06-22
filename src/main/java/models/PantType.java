
package models;

import java.math.BigDecimal;

public class PantType {
    private String pantType;
    private BigDecimal price;

    public PantType() {}

    public PantType(String pantType, BigDecimal price) {
        this.pantType = pantType;
        this.price = price;
    }

    // Getters and Setters
    public String getPantType() { return pantType; }
    public void setPantType(String pantType) { this.pantType = pantType; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    @Override
    public String toString() {
        return pantType + " - $" + price;
    }
}
