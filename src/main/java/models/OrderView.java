
package models;

import java.time.LocalDateTime;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

public class OrderView {
    private Order order;
    private List<Shirt> shirts;
    private List<Pant> pants;
    
    public OrderView() {
        this.shirts = new ArrayList<>();
        this.pants = new ArrayList<>();
    }
    
    public OrderView(Order order) {
        this();
        this.order = order;
    }
    
    // Getters and Setters
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
    
    public List<Shirt> getShirts() { return shirts; }
    public void setShirts(List<Shirt> shirts) { this.shirts = shirts; }
    
    public List<Pant> getPants() { return pants; }
    public void setPants(List<Pant> pants) { this.pants = pants; }
    
    public void addShirt(Shirt shirt) { this.shirts.add(shirt); }
    public void addPant(Pant pant) { this.pants.add(pant); }
    
    // Utility methods
//    public String getFormattedOrderDetails() {
//        if (order == null) {
//            return "Order not found.";
//        }
//        
//        StringBuilder details = new StringBuilder();
//        details.append("================== ORDER DETAILS ==================\n");
//        details.append("Order ID: ").append(order.getOrderID()).append("\n");
//        details.append("Customer Name: ").append(order.getCustomerName()).append("\n");
//        details.append("Customer Mobile: ").append(order.getCustomerMobile()).append("\n");
//        details.append("Order Date: ").append(order.getOrderDate()).append("\n");
//        details.append("Pickup Date: ").append(order.getPickupDate()).append("\n");
//        details.append("Quantity Shirts: ").append(order.getQtyShirts()).append("\n");
//        details.append("Quantity Pants: ").append(order.getQtyPants()).append("\n");
//        details.append("Total Payment: $").append(order.getTotalPayment()).append("\n");
//        details.append("Payment Status: ").append(order.getPaymentStatus()).append("\n");
//        details.append("Note: ").append(order.getNote()).append("\n\n");
//        
//        if (!shirts.isEmpty()) {
//            details.append("================ SHIRT MEASUREMENTS ================\n");
//            for (int i = 0; i < shirts.size(); i++) {
//                Shirt shirt = shirts.get(i);
//                details.append("SHIRT ").append(i + 1).append(":\n");
//                details.append("  Type: ").append(shirt.getShirtType()).append("\n");
//                details.append("  Price: $").append(shirt.getPrice()).append("\n");
//                details.append("  Height: ").append(shirt.getHeight()).append("\n");
//                details.append("  Chest: ").append(shirt.getChest()).append("\n");
//                details.append("  Front1: ").append(shirt.getFront1()).append("\n");
//                details.append("  Front2: ").append(shirt.getFront2()).append("\n");
//                details.append("  Shoulder: ").append(shirt.getShoulder()).append("\n");
//                details.append("  Belly: ").append(shirt.getBelly()).append("\n");
//                details.append("  Sleeve: ").append(shirt.getSleeve()).append("\n");
//                details.append("  Collar: ").append(shirt.getCollar()).append("\n");
//                details.append("  Cup: ").append(shirt.getCup()).append("\n");
//                details.append("  Other: ").append(shirt.getOther()).append("\n\n");
//            }
//        }
//        
//        if (!pants.isEmpty()) {
//            details.append("================ PANT MEASUREMENTS ================\n");
//            for (int i = 0; i < pants.size(); i++) {
//                Pant pant = pants.get(i);
//                details.append("PANT ").append(i + 1).append(":\n");
//                details.append("  Type: ").append(pant.getPantType()).append("\n");
//                details.append("  Price: $").append(pant.getPrice()).append("\n");
//                details.append("  Height: ").append(pant.getHeight()).append("\n");
//                details.append("  Waist: ").append(pant.getWaist()).append("\n");
//                details.append("  Seat: ").append(pant.getSeat()).append("\n");
//                details.append("  Thigh: ").append(pant.getThigh()).append("\n");
//                details.append("  Knee: ").append(pant.getKnee()).append("\n");
//                details.append("  Bottom: ").append(pant.getBottom()).append("\n");
//                details.append("  Hang: ").append(pant.getHang()).append("\n");
//                details.append("  Other: ").append(pant.getOther()).append("\n\n");
//            }
//        }
//        
//        details.append("================================================\n");
//        return details.toString();
//    }
    
    public JPanel buildOrderDetailsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // === Order Summary ===
        JPanel orderInfoPanel = new JPanel(new GridLayout(0, 2, 10, 5));
        orderInfoPanel.setBorder(BorderFactory.createTitledBorder("Order Details"));

        if (order == null) {
            orderInfoPanel.add(new JLabel("Order not found."));
            panel.add(orderInfoPanel, BorderLayout.NORTH);
            return panel;
        }

        orderInfoPanel.add(new JLabel("Order ID:"));
        orderInfoPanel.add(new JLabel(String.valueOf(order.getOrderID())));
        orderInfoPanel.add(new JLabel("Customer Name:"));
        orderInfoPanel.add(new JLabel(order.getCustomerName()));
        orderInfoPanel.add(new JLabel("Mobile:"));
        orderInfoPanel.add(new JLabel(order.getCustomerMobile()));
        orderInfoPanel.add(new JLabel("Order Date:"));
        orderInfoPanel.add(new JLabel(String.valueOf(order.getOrderDate())));
        orderInfoPanel.add(new JLabel("Pickup Date:"));
        orderInfoPanel.add(new JLabel(String.valueOf(order.getPickupDate())));
        orderInfoPanel.add(new JLabel("Shirts Qty:"));
        orderInfoPanel.add(new JLabel(String.valueOf(order.getQtyShirts())));
        orderInfoPanel.add(new JLabel("Pants Qty:"));
        orderInfoPanel.add(new JLabel(String.valueOf(order.getQtyPants())));
        orderInfoPanel.add(new JLabel("Total Payment:"));
        orderInfoPanel.add(new JLabel("₹" + order.getTotalPayment()));
        orderInfoPanel.add(new JLabel("Payment Status:"));
        orderInfoPanel.add(new JLabel(order.getPaymentStatus()));
        orderInfoPanel.add(new JLabel("Advance Payment:"));
        orderInfoPanel.add(new JLabel("₹" + order.getAdvancePayment()));
        orderInfoPanel.add(new JLabel("Note:"));
        orderInfoPanel.add(new JLabel(order.getNote()));

        panel.add(orderInfoPanel, BorderLayout.NORTH);
        JPanel resultPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(resultPanel);
        scrollPane.setPreferredSize(new Dimension(800, 400)); // Adjust height as needed
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // === Shirt Measurements Table ===
        if (!shirts.isEmpty()) {
            String[] shirtCols = {"#", "Type", "Price", "Height", "Chest", "Front1", "Front2", "Shoulder", "Belly", "Sleeve", "Caller", "Cup", "Other"};
            DefaultTableModel shirtModel = new DefaultTableModel(shirtCols, 0);

            for (int i = 0; i < shirts.size(); i++) {
                Shirt s = shirts.get(i);
                shirtModel.addRow(new Object[]{
                    i + 1, s.getShirtType(), s.getPrice(), s.getHeight(), s.getChest(),
                    s.getFront1(), s.getFront2(), s.getShoulder(), s.getBelly(),
                    s.getSleeve(), s.getCollar(), s.getCup(), s.getOther()
                });
            }

            JTable shirtTable = new JTable(shirtModel);
            JScrollPane shirtScroll = new JScrollPane(shirtTable);
            shirtScroll.setBorder(BorderFactory.createTitledBorder("Shirt Measurements"));
            panel.add(shirtScroll, BorderLayout.CENTER);
        }

        // === Pant Measurements Table ===
        if (!pants.isEmpty()) {
            String[] pantCols = {"#", "Type", "Price", "Height", "Waist", "Seat", "Thigh", "Knee", "Bottom", "Hang", "Other"};
            DefaultTableModel pantModel = new DefaultTableModel(pantCols, 0);

            for (int i = 0; i < pants.size(); i++) {
                Pant p = pants.get(i);
                pantModel.addRow(new Object[]{
                    i + 1, p.getPantType(), p.getPrice(), p.getHeight(), p.getWaist(),
                    p.getSeat(), p.getThigh(), p.getKnee(), p.getBottom(), p.getHang(), p.getOther()
                });
            }

            JTable pantTable = new JTable(pantModel);
            JScrollPane pantScroll = new JScrollPane(pantTable);
            pantScroll.setBorder(BorderFactory.createTitledBorder("Pant Measurements"));
            panel.add(pantScroll, BorderLayout.SOUTH);
        }

        return panel;
    }

    
    public String getFormattedSearchResult() {
        if (order == null) {
            return "No order found.";
        }
        
        StringBuilder result = new StringBuilder();
        result.append("Order ID: ").append(order.getOrderID()).append("\n");
        result.append("Customer: ").append(order.getCustomerName()).append("\n");
        result.append("Mobile: ").append(order.getCustomerMobile()).append("\n");
        result.append("Order Date: ").append(order.getOrderDate().toLocalDate()).append("\n");
        result.append("Status: ").append(order.getPaymentStatus()).append("\n");
        result.append("Items: ").append(order.getQtyShirts()).append(" Shirts, ").append(order.getQtyPants()).append(" Pants\n");
        result.append("Total: $").append(order.getTotalPayment()).append("\n\n");
        
        return result.toString();
    }
}
