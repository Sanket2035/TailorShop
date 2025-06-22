
package models;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public class Order {
    private int orderID;
    private String customerName;
    private String customerMobile;
    private LocalDateTime orderDate;
    private LocalDateTime pickupDate;
    private int qtyShirts;
    private int qtyPants;
    private String note;
    private BigDecimal totalPayment;
    private String paymentStatus;
    private BigDecimal advancePayment;

    public Order() {
        this.orderDate = LocalDateTime.now();
    }

    public Order(String customerName, String customerMobile, int qtyShirts, int qtyPants) {
        this();
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.qtyShirts = qtyShirts;
        this.qtyPants = qtyPants;
    }

    // Getters and Setters
    public int getOrderID() { return orderID; }
    public void setOrderID(int orderID) { this.orderID = orderID; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerMobile() { return customerMobile; }
    public void setCustomerMobile(String customerMobile) { this.customerMobile = customerMobile; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public LocalDateTime getPickupDate() { return pickupDate; }
    public void setPickupDate(LocalDateTime pickupDate) { this.pickupDate = pickupDate; }

    public int getQtyShirts() { return qtyShirts; }
    public void setQtyShirts(int qtyShirts) { this.qtyShirts = qtyShirts; }

    public int getQtyPants() { return qtyPants; }
    public void setQtyPants(int qtyPants) { this.qtyPants = qtyPants; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public BigDecimal getTotalPayment() { return totalPayment; }
    public void setTotalPayment(BigDecimal totalPayment) { this.totalPayment = totalPayment; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    
    public BigDecimal getAdvancePayment() { return advancePayment; }
    public void setAdvancePayment(BigDecimal advancePayment) { this.advancePayment = advancePayment; }
    
    public BigDecimal getRemainingPayment(BigDecimal TotalPayment,BigDecimal AdvancePayment) {return TotalPayment.subtract(AdvancePayment);};
}
