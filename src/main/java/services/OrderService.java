
package services;

import models.*;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public static int saveOrder(OrderView orderView) {
        try (Connection conn = DatabaseService.getConnection()) {
            conn.setAutoCommit(false);

            // Insert order
            String insertOrder = "INSERT INTO Orders (CustomerName, CustomerMobile, OrderDate, PickupDate, QtyShirts, QtyPants, Note, TotalPayment, PaymentStatus, advancePayment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING OrderID";

            int orderId;
            try (PreparedStatement stmt = conn.prepareStatement(insertOrder)) {
                Order order = orderView.getOrder();
                stmt.setString(1, order.getCustomerName());
                stmt.setString(2, order.getCustomerMobile());
                stmt.setTimestamp(3, Timestamp.valueOf(order.getOrderDate()));
                stmt.setTimestamp(4, order.getPickupDate() != null ? Timestamp.valueOf(order.getPickupDate()) : null);
                stmt.setInt(5, order.getQtyShirts());
                stmt.setInt(6, order.getQtyPants());
                stmt.setString(7, order.getNote());
                stmt.setBigDecimal(8, order.getTotalPayment());
                stmt.setString(9, order.getPaymentStatus());
                stmt.setBigDecimal(10, order.getAdvancePayment());

                ResultSet rs = stmt.executeQuery();
                rs.next();
                orderId = rs.getInt(1);
                order.setOrderID(orderId);
            }

            // Insert shirts
            if (!orderView.getShirts().isEmpty()) {
                String insertShirt = "INSERT INTO Shirts (OrderID, ShirtType, Price, Height, Chest, Fron1, Front2, Shoulder, Belly, Sleeve, Caller, Cup, Other) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement stmt = conn.prepareStatement(insertShirt)) {
                    for (Shirt shirt : orderView.getShirts()) {
                        stmt.setInt(1, orderId);
                        stmt.setString(2, shirt.getShirtType());
                        stmt.setBigDecimal(3, shirt.getPrice());
                        stmt.setBigDecimal(4, shirt.getHeight());
                        stmt.setBigDecimal(5, shirt.getChest());
                        stmt.setBigDecimal(6, shirt.getFront1());
                        stmt.setBigDecimal(7, shirt.getFront2());
                        stmt.setBigDecimal(8, shirt.getShoulder());
                        stmt.setBigDecimal(9, shirt.getBelly());
                        stmt.setBigDecimal(10, shirt.getSleeve());
                        stmt.setBigDecimal(11, shirt.getCollar());
                        stmt.setBigDecimal(12, shirt.getCup());
                        stmt.setString(13, shirt.getOther());
                        stmt.executeUpdate();
                    }
                }
            }

            // Insert pants
            if (!orderView.getPants().isEmpty()) {
                String insertPant = "INSERT INTO Pants (OrderID, PantType, Price, Height, Waist, Seat, Thigh, Knee, Bottom, Hang, Other) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement stmt = conn.prepareStatement(insertPant)) {
                    for (Pant pant : orderView.getPants()) {
                        stmt.setInt(1, orderId);
                        stmt.setString(2, pant.getPantType());
                        stmt.setBigDecimal(3, pant.getPrice());
                        stmt.setBigDecimal(4, pant.getHeight());
                        stmt.setBigDecimal(5, pant.getWaist());
                        stmt.setBigDecimal(6, pant.getSeat());
                        stmt.setBigDecimal(7, pant.getThigh());
                        stmt.setBigDecimal(8, pant.getKnee());
                        stmt.setBigDecimal(9, pant.getBottom());
                        stmt.setBigDecimal(10, pant.getHang());
                        stmt.setString(11, pant.getOther());
                        stmt.executeUpdate();
                    }
                }
            }

            conn.commit();
            return orderId;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public static int newOrderId() {
    	try (Connection conn = DatabaseService.getConnection()) {
            // Get order
    		int newOrderId = 0;
            String getOrderId = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1;";
            Statement stmt= conn.createStatement();
            ResultSet rs=stmt.executeQuery(getOrderId);
            while(rs.next()) {
            	newOrderId = rs.getInt(1);
            }
            return newOrderId+1;
    	} catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static OrderView getOrderById(int orderId) {
        try (Connection conn = DatabaseService.getConnection()) {
            // Get order
            String getOrder = "SELECT * FROM Orders WHERE OrderID = ?";
            Order order = null;

            try (PreparedStatement stmt = conn.prepareStatement(getOrder)) {
                stmt.setInt(1, orderId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    order = new Order();
                    order.setOrderID(rs.getInt("OrderID"));
                    order.setCustomerName(rs.getString("CustomerName"));
                    order.setCustomerMobile(rs.getString("CustomerMobile"));
                    order.setOrderDate(rs.getTimestamp("OrderDate").toLocalDateTime());
                    Timestamp pickup = rs.getTimestamp("PickupDate");
                    if (pickup != null)
                        order.setPickupDate(pickup.toLocalDateTime());
                    order.setQtyShirts(rs.getInt("QtyShirts"));
                    order.setQtyPants(rs.getInt("QtyPants"));
                    order.setNote(rs.getString("Note"));
                    order.setTotalPayment(rs.getBigDecimal("TotalPayment"));
                    order.setPaymentStatus(rs.getString("PaymentStatus"));
                    order.setAdvancePayment(rs.getBigDecimal("advancePayment"));
                }
            }

            if (order == null)
                return null;

            OrderView orderView = new OrderView(order);

            // Get shirts
            String getShirts = "SELECT * FROM Shirts WHERE OrderID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(getShirts)) {
                stmt.setInt(1, orderId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Shirt shirt = new Shirt();
                    shirt.setShirtID(rs.getInt("ShirtID"));
                    shirt.setOrderID(rs.getInt("OrderID"));
                    shirt.setShirtType(rs.getString("ShirtType"));
                    shirt.setPrice(rs.getBigDecimal("Price"));
                    shirt.setHeight(rs.getBigDecimal("Height"));
                    shirt.setChest(rs.getBigDecimal("Chest"));
                    shirt.setFront1(rs.getBigDecimal("Fron1"));
                    shirt.setFront2(rs.getBigDecimal("Front2"));
                    shirt.setShoulder(rs.getBigDecimal("Shoulder"));
                    shirt.setBelly(rs.getBigDecimal("Belly"));
                    shirt.setSleeve(rs.getBigDecimal("Sleeve"));
                    shirt.setCollar(rs.getBigDecimal("Caller"));
                    shirt.setCup(rs.getBigDecimal("Cup"));
                    shirt.setOther(rs.getString("Other"));
                    orderView.addShirt(shirt);
                }
            }

            // Get pants
            String getPants = "SELECT * FROM Pants WHERE OrderID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(getPants)) {
                stmt.setInt(1, orderId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Pant pant = new Pant();
                    pant.setPantID(rs.getInt("PantID"));
                    pant.setOrderID(rs.getInt("OrderID"));
                    pant.setPantType(rs.getString("PantType"));
                    pant.setPrice(rs.getBigDecimal("Price"));
                    pant.setHeight(rs.getBigDecimal("Height"));
                    pant.setWaist(rs.getBigDecimal("Waist"));
                    pant.setSeat(rs.getBigDecimal("Seat"));
                    pant.setThigh(rs.getBigDecimal("Thigh"));
                    pant.setKnee(rs.getBigDecimal("Knee"));
                    pant.setBottom(rs.getBigDecimal("Bottom"));
                    pant.setHang(rs.getBigDecimal("Hang"));
                    pant.setOther(rs.getString("Other"));
                    orderView.addPant(pant);
                }
            }

            return orderView;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<OrderView> searchOrdersByCustomerName(String customerName) {
        List<OrderView> results = new ArrayList<>();

        try (Connection conn = DatabaseService.getConnection()) {
            String searchQuery = "SELECT OrderID FROM Orders WHERE LOWER(CustomerName) LIKE LOWER(?)";

            try (PreparedStatement stmt = conn.prepareStatement(searchQuery)) {
                stmt.setString(1, "%" + customerName + "%");
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int orderId = rs.getInt("OrderID");
                    OrderView orderView = getOrderById(orderId);
                    if (orderView != null) {
                        results.add(orderView);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    public static boolean updateOrder(int orderId, String orderStatus, String paymentStatus, String notes) {
        try (Connection conn = DatabaseService.getConnection()) {
            String updateQuery = "UPDATE Orders SET PaymentStatus = ?, Note = ? WHERE OrderID = ?";

            try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                stmt.setString(1, paymentStatus);
                stmt.setString(2, notes);
                stmt.setInt(3, orderId);

                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteOrder(int orderId) {
        try (Connection conn = DatabaseService.getConnection()) {
            conn.setAutoCommit(false);

            // Delete shirts
            try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Shirts WHERE OrderID = ?")) {
                stmt.setInt(1, orderId);
                stmt.executeUpdate();
            }

            // Delete pants
            try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Pants WHERE OrderID = ?")) {
                stmt.setInt(1, orderId);
                stmt.executeUpdate();
            }

            // Delete order
            try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Orders WHERE OrderID = ?")) {
                stmt.setInt(1, orderId);
                int result = stmt.executeUpdate();
                conn.commit();
                return result > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<OrderView> getAllOrders() {
        List<OrderView> orders = new ArrayList<>();

        try (Connection conn = DatabaseService.getConnection()) {
            String getAllOrdersQuery = "SELECT OrderID FROM Orders ORDER BY OrderDate DESC";

            try (PreparedStatement stmt = conn.prepareStatement(getAllOrdersQuery)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int orderId = rs.getInt("OrderID");
                    OrderView orderView = getOrderById(orderId);
                    if (orderView != null) {
                        orders.add(orderView);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public static String[] getShirtTypes() {
        List<String> shirtTypes = new ArrayList<>();

        try (Connection conn = DatabaseService.getConnection()) {
            String query = "SELECT ShirtType FROM ShirtTypes ORDER BY ShirtType";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    shirtTypes.add(rs.getString("ShirtType"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] types=shirtTypes.toArray(new String[shirtTypes.size()]);
        return types;
    }

    public static String[] getPantTypes() {
        List<String> pantTypes = new ArrayList<>();

        try (Connection conn = DatabaseService.getConnection()) {
            String query = "SELECT PantType FROM PantTypes ORDER BY PantType";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    pantTypes.add(rs.getString("PantType"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] types=pantTypes.toArray(new String[pantTypes.size()]);
        return types;
    }

    public static BigDecimal getShirtPrice(String shirtType) {
        try (Connection conn = DatabaseService.getConnection()) {
            String query = "SELECT Price FROM ShirtTypes WHERE ShirtType = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, shirtType);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    return rs.getBigDecimal("Price");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new BigDecimal("500.00"); // Default price
    }

    public static BigDecimal getPantPrice(String pantType) {
        try (Connection conn = DatabaseService.getConnection()) {
            String query = "SELECT Price FROM PantTypes WHERE PantType = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, pantType);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    return rs.getBigDecimal("Price");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new BigDecimal("500.00"); // Default price
    }
}
