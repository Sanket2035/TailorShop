package models;

import java.math.BigDecimal;

public class Shirt {
    private int shirtID;
    private int orderID;
    private String shirtType;
    private BigDecimal price;
    private BigDecimal height;
    private BigDecimal chest;
    private BigDecimal front1;
    private BigDecimal front2;
    private BigDecimal shoulder;
    private BigDecimal belly;
    private BigDecimal sleeve;
    private BigDecimal collar;
    private BigDecimal cup;
    private String other;

    public Shirt() {}

    public Shirt(int orderID, String shirtType, BigDecimal price) {
        this.orderID = orderID;
        this.shirtType = shirtType;
        this.price = price;
    }

    // Getters and Setters
    public int getShirtID() { return shirtID; }
    public void setShirtID(int shirtID) { this.shirtID = shirtID; }

    public int getOrderID() { return orderID; }
    public void setOrderID(int orderID) { this.orderID = orderID; }

    public String getShirtType() { return shirtType; }
    public void setShirtType(String shirtType) { this.shirtType = shirtType; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public BigDecimal getHeight() { return height; }
    public void setHeight(BigDecimal height) { this.height = height; }

    public BigDecimal getChest() { return chest; }
    public void setChest(BigDecimal chest) { this.chest = chest; }

    public BigDecimal getFront1() { return front1; }
    public void setFront1(BigDecimal front1) { this.front1 = front1; }

    public BigDecimal getFront2() { return front2; }
    public void setFront2(BigDecimal front2) { this.front2 = front2; }

    public BigDecimal getShoulder() { return shoulder; }
    public void setShoulder(BigDecimal shoulder) { this.shoulder = shoulder; }

    public BigDecimal getBelly() { return belly; }
    public void setBelly(BigDecimal belly) { this.belly = belly; }

    public BigDecimal getSleeve() { return sleeve; }
    public void setSleeve(BigDecimal sleeve) { this.sleeve = sleeve; }

    public BigDecimal getCollar() { return collar; }
    public void setCollar(BigDecimal collar) { this.collar = collar; }

    public BigDecimal getCup() { return cup; }
    public void setCup(BigDecimal cup) { this.cup = cup; }

    public String getOther() { return other; }
    public void setOther(String other) { this.other = other; }
}