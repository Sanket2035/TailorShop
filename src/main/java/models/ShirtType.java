
package models;

import java.math.BigDecimal;

public class ShirtType {
    private String shirtType;
    private BigDecimal price;

    public ShirtType() {}

    public ShirtType(String shirtType, BigDecimal price) {
        this.shirtType = shirtType;
        this.price = price;
    }

    // Getters and Setters
    public String getShirtType() { return shirtType; }
    public void setShirtType(String shirtType) { this.shirtType = shirtType; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    @Override
    public String toString() {
        return shirtType + " - $" + price;
    }
}
