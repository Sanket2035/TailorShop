package com.tailorshop;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import components.*;
import services.OrderService;
import services.BillService;
import services.DatabaseService;
import models.OrderView;
import java.util.List;

public class Main {
    // Modern color scheme with better contrast
    private static final Color PRIMARY_COLOR = new Color(25, 39, 77); // Dark navy blue
    private static final Color SECONDARY_COLOR = new Color(55, 73, 130); // Medium blue
    private static final Color ACCENT_COLOR = new Color(255, 145, 77); // Orange accent
    private static final Color SUCCESS_COLOR = new Color(34, 197, 94); // Green
    private static final Color DANGER_COLOR = new Color(239, 68, 68); // Red
    private static final Color WARNING_COLOR = new Color(245, 158, 11); // Yellow
    private static final Color LIGHT_BG = new Color(249, 250, 251);
    private static final Color WHITE_BG = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(31, 41, 55); // Dark gray
    private static final Color HEADER_TEXT = Color.WHITE;
    private static final Color BORDER_COLOR = new Color(209, 213, 219);

    private static JPanel mainContentPanel;
    private static JPanel dashboardWrapper;
    private static CardLayout cardLayout;
    private static OrderFormPanel orderPanel;
//    private static MeasurementPanel measurementPanel;
//    private static OrderDisplayPanel orderDisplayPanel;

    private static void createAndShowGUI() {
        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Customize UI colors
            UIManager.put("Panel.background", LIGHT_BG);
            UIManager.put("Button.background", PRIMARY_COLOR);
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("Button.font", new Font("Arial", Font.BOLD, 12));
            UIManager.put("TextField.background", WHITE_BG);
            UIManager.put("TextField.border", BorderFactory.createLineBorder(BORDER_COLOR, 1));
            UIManager.put("ComboBox.background", WHITE_BG);
            UIManager.put("TextArea.background", WHITE_BG);

        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame jFrame = new JFrame("Posh Tailors Management System");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1400, 900);
        jFrame.setLayout(new BorderLayout());
        jFrame.setLocationRelativeTo(null);

        // Create header panel with logo and title
        JPanel headerPanel = createHeaderPanel();
        jFrame.add(headerPanel, BorderLayout.NORTH);

        // Create sidebar
        JPanel sidebarPanel = createSidebarPanel();
        jFrame.add(sidebarPanel, BorderLayout.WEST);

        // Create main content panel
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);
        mainContentPanel.setBackground(LIGHT_BG);
        mainContentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Initialize panels
        orderPanel=new OrderFormPanel();
//        measurementPanel = new MeasurementPanel();
//        orderDisplayPanel = new OrderDisplayPanel();
        dashboardWrapper = new JPanel(new BorderLayout());
        dashboardWrapper.add(createDashboardPanel(), BorderLayout.CENTER);
        
        // Add panels to card layout
        mainContentPanel.add(dashboardWrapper, "Dashboard");
        mainContentPanel.add(orderPanel, "New Order");
        mainContentPanel.add(createViewOrdersPanel(), "View Orders");
        mainContentPanel.add(createSearchPanel(), "Search");
        mainContentPanel.add(createBillPanel(), "Generate Bill");
        mainContentPanel.add(createSettingsPanel(), "Settings");

        jFrame.add(mainContentPanel, BorderLayout.CENTER);

        // Show dashboard by default
        cardLayout.show(mainContentPanel, "Dashboard");

        jFrame.setVisible(true);
    }

    private static JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(0, 85));
        headerPanel.setBorder(new EmptyBorder(15, 25, 15, 25));

        // Logo and title with gradient effect
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setBackground(PRIMARY_COLOR);

        JLabel logoLabel = new JLabel("POSH");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 36));
        logoLabel.setForeground(ACCENT_COLOR);

        JLabel titleLabel = new JLabel("TAILORS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(HEADER_TEXT);

        JLabel subtitleLabel = new JLabel("Management System");
        subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        subtitleLabel.setForeground(new Color(200, 200, 200));

        logoPanel.add(logoLabel);
        logoPanel.add(titleLabel);
        logoPanel.add(Box.createHorizontalStrut(10));
        logoPanel.add(subtitleLabel);

        // Admin info with styled panel
        JPanel adminPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        adminPanel.setBackground(PRIMARY_COLOR);

        JLabel adminLabel = new JLabel("👤 Admin: Anil Shelke");
        adminLabel.setFont(new Font("Arial", Font.BOLD, 16));
        adminLabel.setForeground(HEADER_TEXT);
        adminLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                new EmptyBorder(8, 15, 8, 15)));

        adminPanel.add(adminLabel);

        headerPanel.add(logoPanel, BorderLayout.WEST);
        headerPanel.add(adminPanel, BorderLayout.EAST);

        return headerPanel;
    }

    private static JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(PRIMARY_COLOR);
        sidebarPanel.setPreferredSize(new Dimension(250, 0));
        sidebarPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        String[] menuItems = { "Dashboard", "New Order", "View Orders", "Search", "Generate Bill", "Settings" };
        String[] icons = { "🏠", "➕", "📋", "🔍", "🧾", "⚙️" };

        for (int i = 0; i < menuItems.length; i++) {
            JButton menuButton = createSidebarButton(icons[i] + "  " + menuItems[i], menuItems[i]);
            menuButton.setForeground(TEXT_COLOR);
            sidebarPanel.add(menuButton);
            sidebarPanel.add(Box.createVerticalStrut(5));
        }
        
        sidebarPanel.add(Box.createVerticalGlue());

        return sidebarPanel;
    }

    private static JButton createSidebarButton(String text, String cardName) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(SECONDARY_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 90, 150), 1),
                new EmptyBorder(18, 25, 18, 25)));
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(ACCENT_COLOR);
                button.setForeground(TEXT_COLOR);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(SECONDARY_COLOR);
                button.setForeground(TEXT_COLOR);
            }
        });
        
        if(cardName=="Dashboard") {
        	button.addActionListener(e -> {
        	    refreshDashboard();  
        	});

        }else {
        	button.addActionListener(e -> cardLayout.show(mainContentPanel, cardName));
        }
        return button;
    }
    
    private static void refreshDashboard() {
        dashboardWrapper.removeAll();  // Clear old dashboard panel
        dashboardWrapper.add(createDashboardPanel(), BorderLayout.CENTER); // Re-add updated panel
        dashboardWrapper.revalidate();
        dashboardWrapper.repaint();

        cardLayout.show(mainContentPanel, "Dashboard");
    }


    private static JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(LIGHT_BG);

        GridBagConstraints gbc = new GridBagConstraints();

        // Welcome section
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBackground(WHITE_BG);
        welcomePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                new EmptyBorder(30, 30, 30, 30)));
        welcomePanel.setPreferredSize(new Dimension(800, 200));

        JLabel welcomeLabel = new JLabel(
                "<html><h1>Welcome to Posh Tailors</h1><p>Manage your tailoring business efficiently</p></html>");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        welcomeLabel.setForeground(TEXT_COLOR);
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        panel.add(welcomePanel, gbc);

        // Quick stats
        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        statsPanel.setBackground(LIGHT_BG);
        statsPanel.setPreferredSize(new Dimension(800, 150));

        // Total orders
        JPanel totalOrdersCard = createStatCard("Total Orders", String.valueOf(OrderService.getAllOrders().size()),
                SUCCESS_COLOR);
        statsPanel.add(totalOrdersCard);

        // Pending orders
        long pendingOrders = OrderService.getAllOrders().stream()
                .filter(order -> "Pending".equals(order.getOrder().getPaymentStatus()))
                .count();
        pendingOrders+=OrderService.getAllOrders().stream().filter(order -> "Partial".equals(order.getOrder().getPaymentStatus())).count();
        JPanel pendingOrdersCard = createStatCard("Pending Orders", String.valueOf(pendingOrders), ACCENT_COLOR);
        statsPanel.add(pendingOrdersCard);

        // Completed orders
        long completedOrders = OrderService.getAllOrders().stream()
                .filter(order -> "Paid".equals(order.getOrder().getPaymentStatus()))
                .count();
        JPanel completedOrdersCard = createStatCard("Completed Orders", String.valueOf(completedOrders), PRIMARY_COLOR);
        statsPanel.add(completedOrdersCard);

        gbc.gridy = 1;
        panel.add(statsPanel, gbc);

        return panel;
    }

    private static JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(WHITE_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                new EmptyBorder(20, 20, 20, 20)));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titleLabel.setForeground(TEXT_COLOR);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 36));
        valueLabel.setForeground(color);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

//    private static JPanel createViewOrdersPanel() {
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.setBackground(LIGHT_BG);
//
//        // Header
//        JLabel titleLabel = new JLabel("View Orders");
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//        titleLabel.setForeground(TEXT_COLOR);
//        titleLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
//        panel.add(titleLabel, BorderLayout.NORTH);
//
//        // Search section
//        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        searchPanel.setBackground(LIGHT_BG);
//
//        JTextField orderIdField = new JTextField(10);
//        JButton searchButton = createModernButton("Search by Order ID", PRIMARY_COLOR);
//        
//        JPanel resultPanel = new JPanel();
//        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
//        resultPanel.setBackground(Color.WHITE); // Optional: for contrast
//
//        JScrollPane scrollPane = new JScrollPane(resultPanel);
//        scrollPane.setPreferredSize(new Dimension(800, 400)); // Adjust height as needed
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
////        JTextArea resultArea = new JTextArea(25, 80);
////        resultArea.setBackground(WHITE_BG);
////        resultArea.setBorder(new EmptyBorder(15, 15, 15, 15));
////        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
//
//        searchButton.addActionListener(e -> {
//            try {
//                int orderId = Integer.parseInt(orderIdField.getText().trim());
//                OrderView orderView = OrderService.getOrderById(orderId);
//                if (orderView != null) {
//                	resultPanel.removeAll();
//                	JPanel detailsPanel = orderView.buildOrderDetailsPanel();
//                    resultPanel.add(detailsPanel, BorderLayout.CENTER);
//                } else {
//                    resultPanel.add(new JLabel("Order not found with ID: " + orderId), BorderLayout.CENTER);
//                }
//
//                resultPanel.revalidate();
//                resultPanel.repaint();
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(panel, "Please enter a valid order ID.", "Invalid Input",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//        });
//
//        searchPanel.add(new JLabel("Order ID:"));
//        searchPanel.add(orderIdField);
//        searchPanel.add(searchButton);
//
//        // Edit/Delete buttons
//        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        actionPanel.setBackground(LIGHT_BG);
//
//        JComboBox<String> paymentStatusCombo = new JComboBox<>(new String[] { "Pending", "Paid", "Cancelled" });
//        JTextField notesField = new JTextField(20);
//        JButton updateButton = createModernButton("Update Order", SUCCESS_COLOR);
//        updateButton.setForeground(PRIMARY_COLOR);
//        JButton deleteButton = createModernButton("Delete Order", DANGER_COLOR);
//        deleteButton.setForeground(WARNING_COLOR);
//        updateButton.addActionListener(e -> {
//            try {
//                int orderId = Integer.parseInt(orderIdField.getText().trim());
//                String paymentStatus = (String) paymentStatusCombo.getSelectedItem();
//                String notes = notesField.getText().trim();
//
//                if (OrderService.updateOrder(orderId, "", paymentStatus, notes)) {
//                	JOptionPane.showMessageDialog(panel, "Order Upadated Successfully", "Success",
//                            JOptionPane.ERROR_MESSAGE);
//                } else {
//                	JOptionPane.showMessageDialog(panel, "Failed to update order.", "Failed",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(panel, "Please enter a valid order ID.", "Invalid Input",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//        });
//
//        deleteButton.addActionListener(e -> {
//            try {
//                int orderId = Integer.parseInt(orderIdField.getText().trim());
//                int confirm = JOptionPane.showConfirmDialog(panel,
//                        "Are you sure you want to delete this order?",
//                        "Confirm Delete", JOptionPane.YES_NO_OPTION);
//
//                if (confirm == JOptionPane.YES_OPTION) {
//                    if (OrderService.deleteOrder(orderId)) {
//                    	JOptionPane.showMessageDialog(panel, "Order Deleted Successfully", "Success",
//                                JOptionPane.ERROR_MESSAGE);
//                        orderIdField.setText("");
//                        paymentStatusCombo.setSelectedIndex(0);
//                        notesField.setText("");
//                    } else {
//                    	JOptionPane.showMessageDialog(panel, "Failed to delete order.", "Failure",
//                                JOptionPane.ERROR_MESSAGE);
//                    }
//                }
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(panel, "Please enter a valid order ID.", "Invalid Input",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//        });
//
//        actionPanel.add(new JLabel("Payment Status:"));
//        actionPanel.add(paymentStatusCombo);
//        actionPanel.add(new JLabel("Notes:"));
//        actionPanel.add(notesField);
//        actionPanel.add(updateButton);
//        actionPanel.add(deleteButton);
//
//        // Main content
//        JPanel contentPanel = new JPanel(new BorderLayout());
//        contentPanel.setBackground(LIGHT_BG);
//        contentPanel.add(searchPanel, BorderLayout.NORTH);
//        contentPanel.add(actionPanel, BorderLayout.CENTER);
//        contentPanel.add(resultPanel, BorderLayout.SOUTH);
//
//        panel.add(contentPanel, BorderLayout.CENTER);
//
//        return panel;
//    }
    
    private static JPanel createViewOrdersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_BG);

        // Header
        JLabel titleLabel = new JLabel("View Orders");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Search section
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(LIGHT_BG);

        JTextField orderIdField = new JTextField(10);
        JButton searchButton = createModernButton("Search by Order ID", PRIMARY_COLOR);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setBackground(Color.WHITE); // Optional: for contrast

        JScrollPane scrollPane = new JScrollPane(resultPanel);
        scrollPane.setPreferredSize(new Dimension(800, 400)); // Adjust height as needed
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        searchButton.addActionListener(e -> {
            try {
                int orderId = Integer.parseInt(orderIdField.getText().trim());
                OrderView orderView = OrderService.getOrderById(orderId);
                resultPanel.removeAll();

                if (orderView != null) {
                    JPanel detailsPanel = orderView.buildOrderDetailsPanel();
                    resultPanel.add(detailsPanel);
                } else {
                    resultPanel.add(new JLabel("Order not found with ID: " + orderId));
                }

                resultPanel.revalidate();
                resultPanel.repaint();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Please enter a valid order ID.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        searchPanel.add(new JLabel("Order ID:"));
        searchPanel.add(orderIdField);
        searchPanel.add(searchButton);

        // Action Panel (Edit/Delete)
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actionPanel.setBackground(LIGHT_BG);

        JComboBox<String> paymentStatusCombo = new JComboBox<>(new String[]{"Pending", "Paid", "Cancelled"});
        JTextField notesField = new JTextField(20);
        JButton updateButton = createModernButton("Update Order", SUCCESS_COLOR);
        updateButton.setForeground(PRIMARY_COLOR);
        JButton deleteButton = createModernButton("Delete Order", DANGER_COLOR);
        deleteButton.setForeground(WARNING_COLOR);

        updateButton.addActionListener(e -> {
            try {
                int orderId = Integer.parseInt(orderIdField.getText().trim());
                String paymentStatus = (String) paymentStatusCombo.getSelectedItem();
                String notes = notesField.getText().trim();

                if (OrderService.updateOrder(orderId, "", paymentStatus, notes)) {
                    JOptionPane.showMessageDialog(panel, "Order Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(panel, "Failed to update order.", "Failed", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Please enter a valid order ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                int orderId = Integer.parseInt(orderIdField.getText().trim());
                int confirm = JOptionPane.showConfirmDialog(panel,
                        "Are you sure you want to delete this order?",
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    if (OrderService.deleteOrder(orderId)) {
                        JOptionPane.showMessageDialog(panel, "Order Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        orderIdField.setText("");
                        paymentStatusCombo.setSelectedIndex(0);
                        notesField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Failed to delete order.", "Failure", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Please enter a valid order ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        actionPanel.add(new JLabel("Payment Status:"));
        actionPanel.add(paymentStatusCombo);
        actionPanel.add(new JLabel("Notes:"));
        actionPanel.add(notesField);
        actionPanel.add(updateButton);
        actionPanel.add(deleteButton);

        // Main content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(LIGHT_BG);
        contentPanel.add(searchPanel, BorderLayout.NORTH);
        contentPanel.add(actionPanel, BorderLayout.CENTER);
        contentPanel.add(scrollPane, BorderLayout.SOUTH);

        panel.add(contentPanel, BorderLayout.CENTER);

        return panel;
    }


    private static JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_BG);

        // Header
        JLabel titleLabel = new JLabel("Search Orders");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Search controls
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(LIGHT_BG);

        JTextField customerNameField = new JTextField(20);
        JButton searchByNameButton = createModernButton("Search by Customer Name", PRIMARY_COLOR);
        searchByNameButton.setForeground(TEXT_COLOR);
        JButton showAllButton = createModernButton("Show All Orders", SECONDARY_COLOR);
        showAllButton.setForeground(TEXT_COLOR);
        
        JTable resultsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(resultsTable);

//        JTextArea resultsArea = new JTextArea(25, 80);
//        resultsArea.setBackground(WHITE_BG);
//        resultsArea.setBorder(new EmptyBorder(15, 15, 15, 15));
//        resultsArea.setFont(new Font("Consolas", Font.PLAIN, 12));

        searchByNameButton.addActionListener(e -> {
            String customerName = customerNameField.getText().trim();
            if (!customerName.isEmpty()) {
                List<OrderView> orders = OrderService.searchOrdersByCustomerName(customerName);
                displaySearchResults(orders, resultsTable);
            } else {
            	 new JLabel("Please enter a customer name to search.");
            }
        });

        showAllButton.addActionListener(e -> {
            List<OrderView> allOrders = OrderService.getAllOrders();
            displaySearchResults(allOrders, resultsTable);
        });

        searchPanel.add(new JLabel("Customer Name:"));
        searchPanel.add(customerNameField);
        searchPanel.add(searchByNameButton);
        searchPanel.add(showAllButton);

        // Main content
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(LIGHT_BG);
        contentPanel.add(searchPanel, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(scrollPane), BorderLayout.CENTER);

        panel.add(contentPanel, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel createBillPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_BG);

        // Header
        JLabel titleLabel = new JLabel("Generate Bill");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Input section
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setBackground(LIGHT_BG);

        JTextField orderIdField = new JTextField(15);
        JButton generateButton = createModernButton("Generate Bill", SUCCESS_COLOR);
        generateButton.setForeground(TEXT_COLOR);
        JButton printButton = createModernButton("Print Bill", PRIMARY_COLOR);
        printButton.setForeground(TEXT_COLOR);

        JTextArea billArea = new JTextArea(25, 80);
        billArea.setBackground(WHITE_BG);
        billArea.setBorder(new EmptyBorder(15, 15, 15, 15));
        billArea.setFont(new Font("Consolas", Font.PLAIN, 12));

        generateButton.addActionListener(e -> {
            try {
                int orderId = Integer.parseInt(orderIdField.getText().trim());
                OrderView orderView = OrderService.getOrderById(orderId);
                if (orderView != null) {
                    String bill = BillService.generateNewBill(orderView);
                    billArea.setText(bill);
                } else {
                    billArea.setText("Order not found with ID: " + orderId);
                }
            } catch (NumberFormatException ex) {
                billArea.setText("Please enter a valid order ID number.");
            }
        });

        printButton.addActionListener(e -> {
            if (!billArea.getText().trim().isEmpty()) {
                try {
                    billArea.print();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Error printing bill: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Please generate a bill first.");
            }
        });

        inputPanel.add(new JLabel("Order ID:"));
        inputPanel.add(orderIdField);
        inputPanel.add(generateButton);
        inputPanel.add(printButton);

        // Main content
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(LIGHT_BG);
        contentPanel.add(inputPanel, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(billArea), BorderLayout.CENTER);

        panel.add(contentPanel, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel createSettingsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_BG);

        // Header
        JLabel titleLabel = new JLabel("Settings - Manage Types & Prices");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Main content
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(LIGHT_BG);

        // Shirt Types tab
        JPanel shirtTypesPanel = createTypesManagementPanel("Shirt");
        tabbedPane.addTab("Shirt Types", shirtTypesPanel);

        // Pant Types tab
        JPanel pantTypesPanel = createTypesManagementPanel("Pant");
        tabbedPane.addTab("Pant Types", pantTypesPanel);

        panel.add(tabbedPane, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel createTypesManagementPanel(String itemType) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_BG);

        // Input section
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(LIGHT_BG);
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField typeField = new JTextField(20);
        JTextField priceField = new JTextField(10);
        JButton addButton = createModernButton("Add " + itemType + " Type", SUCCESS_COLOR);
        addButton.setForeground(TEXT_COLOR);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel(itemType + " Type:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(typeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Price (₹):"), gbc);
        gbc.gridx = 1;
        inputPanel.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        inputPanel.add(addButton, gbc);
        
        JTable typeTable = new JTable();
//        JScrollPane scrollPane = new JScrollPane(typeTable);

        // List section
//        JTextArea listArea = new JTextArea(15, 50);
//        listArea.setBackground(WHITE_BG);
//        listArea.setBorder(new EmptyBorder(15, 15, 15, 15));
//        listArea.setFont(new Font("Consolas", Font.PLAIN, 12));

        // Load current types
        refreshTypesList(typeTable, itemType);

        addButton.addActionListener(e -> {
            String type = typeField.getText().trim();
            String priceText = priceField.getText().trim();

            if (!type.isEmpty() && !priceText.isEmpty()) {
                try {
                    // In a real implementation, you would add this to the database
                    JOptionPane.showMessageDialog(panel,
                            itemType + " type '" + type + "' with price ₹" + priceText
                                    + " would be added to database.");
                    
                    if(itemType=="Shirt") {
                    	services.DatabaseService.insertShirtTypes(type, priceText);
                    }else {
                    	services.DatabaseService.insertPantTypes(type, priceText);
                    }
                    
                    typeField.setText("");
                    priceField.setText("");
                    refreshTypesList(typeTable, itemType);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Please enter a valid price.");
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Please fill in all fields.");
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(typeTable), BorderLayout.CENTER);

        return panel;
    }

//    private static void refreshTypesList(JTextArea listArea, String itemType) {
//        StringBuilder content = new StringBuilder();
//        content.append("Current ").append(itemType).append(" Types:\n");
//        content.append("================================\n\n");
//
//        if ("Shirt".equals(itemType)) {
//            List<String> shirtTypes = OrderService.getShirtTypes();
//            for (String type : shirtTypes) {
//                content.append(type).append(" - ₹").append(OrderService.getShirtPrice(type)).append("\n");
//            }
//        } else {
//            List<String> pantTypes = OrderService.getPantTypes();
//            for (String type : pantTypes) {
//                content.append(type).append(" - ₹").append(OrderService.getPantPrice(type)).append("\n");
//            }
//        }
//
//        listArea.setText(content.toString());
//    }
    
    private static void refreshTypesList(JTable listTable, String itemType) {
        // Define column names
        String[] columnNames = {"Type", "Price (₹)"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Fetch type list and prices
        if ("Shirt".equals(itemType)) {
            String[] shirtTypes = OrderService.getShirtTypes();
            for (String type : shirtTypes) {
                Object[] row = {type, OrderService.getShirtPrice(type)};
                tableModel.addRow(row);
            }
        } else {
            String[] pantTypes = OrderService.getPantTypes();
            for (String type : pantTypes) {
                Object[] row = {type, OrderService.getPantPrice(type)};
                tableModel.addRow(row);
            }
        }

        // Set the new model to the table
        listTable.setModel(tableModel);
    }


    private static JButton createModernButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(TEXT_COLOR);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(backgroundColor.darker(), 1),
                new EmptyBorder(12, 25, 12, 25)));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.brighter());
                button.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(backgroundColor.brighter(), 2),
                        new EmptyBorder(11, 24, 11, 24)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
                button.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(backgroundColor.darker(), 1),
                        new EmptyBorder(12, 25, 12, 25)));
            }
        });

        return button;
    }

//    private static void displaySearchResults(List<OrderView> orders, JTextArea resultsArea) {
//        if (orders.isEmpty()) {
//            resultsArea.setText("No orders found.");
//            return;
//        }
//
//        StringBuilder results = new StringBuilder();
//        results.append("Search Results (").append(orders.size()).append(" orders found):\n");
//        results.append("================================================================\n\n");
//        for (OrderView orderView : orders) {
//            results.append("Order ID: ").append(orderView.getOrder().getOrderID());
//            results.append(" | Customer: ").append(orderView.getOrder().getCustomerName());
//            results.append(" | Mobile: ").append(orderView.getOrder().getCustomerMobile());
//            results.append(" | Total: ₹").append(orderView.getOrder().getTotalPayment());
//            results.append(" | Status: ").append(orderView.getOrder().getPaymentStatus());
//            results.append("\n");
//        }
//
//        resultsArea.setText(results.toString());
//    }
    
    private static void displaySearchResults(List<OrderView> orders, JTable resultsTable) {
        // Define table column names
        String[] columnNames = {"Order ID", "Customer Name", "Mobile", "Total Payment (₹)", "Payment Status"};

        // Create table model and clear previous data
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        if (orders.isEmpty()) {
            // Show "No orders found" as a single row
            tableModel.addRow(new Object[]{"No orders found", "", "", "", ""});
        } else {
            // Add each order to the table
            for (OrderView orderView : orders) {
                Object[] row = {
                    orderView.getOrder().getOrderID(),
                    orderView.getOrder().getCustomerName(),
                    orderView.getOrder().getCustomerMobile(),
                    orderView.getOrder().getTotalPayment(),
                    orderView.getOrder().getPaymentStatus()
                };
                tableModel.addRow(row);
            }
        }

        // Set the model to the JTable
        resultsTable.setModel(tableModel);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }
}