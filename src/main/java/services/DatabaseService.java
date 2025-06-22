
package services;

import java.sql.*;
import java.util.Properties;

public class DatabaseService {
    private static final String DB_URL = System.getenv("DATABASE_URL");
    private static Connection connection;

    static {
        try {
            // Load PostgreSQL driver
            Class.forName("org.postgresql.Driver");
            initializeConnection();
            createTables();
        } catch (Exception e) {
            System.err.println("Warning: Database connection failed. Application will run with limited functionality.");
            System.err.println("Error: " + e.getMessage());
            // Set connection to null so methods can handle gracefully
            connection = null;
        }
    }

    private static void initializeConnection() throws SQLException {
        if (DB_URL != null && !DB_URL.isEmpty()) {
            connection = DriverManager.getConnection(DB_URL);
        } else {
            // Fallback to local connection for development
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "sanket");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/poshtailors", props);
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            initializeConnection();
        }
        if (connection == null) {
            throw new SQLException(
                    "Database connection could not be established. Please check if PostgreSQL is running and the database exists.");
        }
        return connection;
    }

    private static void createTables() {
        try (Statement stmt = connection.createStatement()) {
            // Create Orders table
            String createOrdersTable = "CREATE TABLE IF NOT EXISTS orders (OrderID SERIAL PRIMARY KEY,CustomerName VARCHAR(100) NOT NULL,CustomerMobile VARCHAR(15) NOT NULL,OrderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,PickupDate TIMESTAMP,QtyShirts INT NOT NULL,QtyPants INT NOT NULL,Note TEXT,TotalPayment DECIMAL(10, 2),PaymentStatus VARCHAR(50),advancePayment NUMERIC(10, 2) DEFAULT 0)";

            // Create Shirts table
            String createShirtsTable = "CREATE TABLE IF NOT EXISTS shirts (ShirtID SERIAL PRIMARY KEY,OrderID INT NOT NULL,ShirtType VARCHAR(50) NOT NULL,Price DECIMAL(10,2) NOT NULL,Height DECIMAL(10,2),Chest DECIMAL(10,2),Front1 DECIMAL(10,2),Front2 DECIMAL(10,2),Shoulder DECIMAL(10,2),Belly DECIMAL(10,2),Sleeve DECIMAL(10,2),Collar DECIMAL(10,2),Cup DECIMAL(10,2),Other TEXT,FOREIGN KEY(OrderID) REFERENCES Orders(OrderID))";

            // Create Pants table
            String createPantsTable = "CREATE TABLE IF NOT EXISTS pants (PantID SERIAL PRIMARY KEY,OrderID INT NOT NULL,PantType VARCHAR(50) NOT NULL,Price DECIMAL(10,2) NOT NULL,Height DECIMAL(10,2),Waist DECIMAL(10,2),Seat DECIMAL(10,2),Thigh DECIMAL(10,2),Knee DECIMAL(10,2),Bottom DECIMAL(10,2),Hang DECIMAL(10,2),Other TEXT,FOREIGN KEY(OrderID) REFERENCES Orders(OrderID))";

            // Create ShirtTypes table
            String createShirtTypesTable = "CREATE TABLE IF NOT EXISTS shirttypes (ShirtType VARCHAR(50) PRIMARY KEY,Price DECIMAL(10,2) NOT NULL)";
            // Create PantTypes table
            String createPantTypesTable = "CREATE TABLE IF NOT EXISTS panttypes (PantType VARCHAR(50) PRIMARY KEY,Price DECIMAL(10,2) NOT NULL)";

            stmt.execute(createOrdersTable);
            stmt.execute(createShirtsTable);
            stmt.execute(createPantsTable);
            stmt.execute(createShirtTypesTable);
            stmt.execute(createPantTypesTable);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertShirtTypes(String type,String priceText) {
        try (Connection conn = getConnection()) {
            String insertShirtTypes = "INSERT INTO shirttypes (shirttype, price) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertShirtTypes)) {
                stmt.setString(1, type);
                stmt.setBigDecimal(2,new java.math.BigDecimal(priceText));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void insertPantTypes(String type,String priceText) {
        try (Connection conn = getConnection()) {
            String insertPantTypes = "INSERT INTO panttypes (panttype, price) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertPantTypes)) {
                stmt.setString(1, type);
                stmt.setBigDecimal(2,new java.math.BigDecimal(priceText));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
