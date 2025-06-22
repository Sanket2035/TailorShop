//
//package components;
//
//import models.*;
//import services.OrderService;
//import javax.swing.*;
//import javax.swing.SpinnerNumberModel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
//
////import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrderFormPanel extends JPanel {
//    private JTextField customerNameField;
//    private JTextField customerMobileField;
//    private JSpinner qtyShirtsSpinner;
//    private JSpinner qtyPantsSpinner;
//    private JTextArea noteArea;
//    private JTextField totalPaymentField;
//    private JComboBox<String> paymentStatusCombo;
//    private JTextField pickupDateField;
//    private JButton saveButton;
//    private JButton clearButton;
//
//    // Measurement panels
//    private JPanel measurementPanel;
//    private JPanel shirtMeasurementsPanel;
//    private JPanel pantMeasurementsPanel;
//    private List<ShirtMeasurementPanel> shirtPanels;
//    private List<PantMeasurementPanel> pantPanels;
//
//    public OrderFormPanel() {
//        shirtPanels = new ArrayList<ShirtMeasurementPanel>();
//        pantPanels = new ArrayList<PantMeasurementPanel>();
//        initializeComponents();
//        setupLayout();
//        setupEventListeners();
//    }
//
//    private void initializeComponents() {
//        customerNameField = new JTextField(25);
//        customerMobileField = new JTextField(20);
//        qtyShirtsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
//        qtyPantsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
//        noteArea = new JTextArea(4, 30);
//        totalPaymentField = new JTextField(15);
//        paymentStatusCombo = new JComboBox<>(new String[] { "Pending", "Partial", "Paid", "Overdue" });
//        pickupDateField = new JTextField(20);
//        saveButton = new JButton("Save Order");
//        clearButton = new JButton("Clear Form");
//
//        measurementPanel = new JPanel();
//        measurementPanel.setLayout(new BoxLayout(measurementPanel, BoxLayout.Y_AXIS));
//        shirtMeasurementsPanel = new JPanel();
//        shirtMeasurementsPanel.setLayout(new BoxLayout(shirtMeasurementsPanel, BoxLayout.Y_AXIS));
//        pantMeasurementsPanel = new JPanel();
//        pantMeasurementsPanel.setLayout(new BoxLayout(pantMeasurementsPanel, BoxLayout.Y_AXIS));
//    }
//
//    private void setupLayout() {
//        setLayout(new BorderLayout());
//
//        // Create main container with proper scrolling
//        JPanel mainContainer = new JPanel(new BorderLayout());
//
//        // Customer details panel with order ID display
//        JPanel customerPanel = createCustomerDetailsPanel();
//
//        // Setup measurement panels
//        setupMeasurementPanels();
//
//        // Create measurement container with title
//        JPanel measurementContainer = new JPanel(new BorderLayout());
//        measurementContainer.setBorder(BorderFactory.createTitledBorder(
//                BorderFactory.createLineBorder(Color.GRAY, 1),
//                "Measurement Details",
//                0, 0, new Font("Arial", Font.BOLD, 16)));
//
//        // Add measurement panel with proper scrolling
//        JScrollPane measurementScrollPane = new JScrollPane(measurementPanel);
//        measurementScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        measurementScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        measurementScrollPane.setPreferredSize(new Dimension(0, 400));
//        measurementContainer.add(measurementScrollPane, BorderLayout.CENTER);
//
//        // Add components to main container
//        mainContainer.add(customerPanel, BorderLayout.NORTH);
//        mainContainer.add(measurementContainer, BorderLayout.CENTER);
//
//        // Add main container with scrolling to the panel
//        JScrollPane mainScrollPane = new JScrollPane(mainContainer);
//        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        add(mainScrollPane, BorderLayout.CENTER);
//    }
//
//    private JPanel createCustomerDetailsPanel() {
//        JPanel formPanel = new JPanel(new GridBagLayout());
//        formPanel.setBorder(BorderFactory.createTitledBorder(
//                BorderFactory.createLineBorder(Color.GRAY, 1),
//                "Customer & Order Details",
//                0, 0, new Font("Arial", Font.BOLD, 16)));
//        formPanel.setBackground(Color.WHITE);
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(8, 8, 8, 8);
//        gbc.anchor = GridBagConstraints.WEST;
//
//        // Order ID display (will be shown after saving)
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        formPanel.add(new JLabel("Order ID:"), gbc);
//        gbc.gridx = 1;
//        int newId=OrderService.newOrderId();
//        JLabel orderIdLabel = new JLabel(""+newId);
//        orderIdLabel.setFont(new Font("Arial", Font.ITALIC, 12));
//        orderIdLabel.setForeground(Color.GRAY);
//        formPanel.add(orderIdLabel, gbc);
//
//        // Customer Name
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        formPanel.add(new JLabel("Customer Name:"), gbc);
//        gbc.gridx = 1;
//        formPanel.add(customerNameField, gbc);
//
//        // Customer Mobile
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        formPanel.add(new JLabel("Mobile:"), gbc);
//        gbc.gridx = 1;
//        formPanel.add(customerMobileField, gbc);
//
//        // Qty Shirts
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        formPanel.add(new JLabel("Qty Shirts:"), gbc);
//        gbc.gridx = 1;
//        formPanel.add(qtyShirtsSpinner, gbc);
//
//        // Qty Pants
//        gbc.gridx = 0;
//        gbc.gridy = 4;
//        formPanel.add(new JLabel("Qty Pants:"), gbc);
//        gbc.gridx = 1;
//        formPanel.add(qtyPantsSpinner, gbc);
//
//        // Pickup Date
//        gbc.gridx = 0;
//        gbc.gridy = 5;
//        formPanel.add(new JLabel("Pickup Date (YYYY-MM-DD or MM/DD/YYYY):"), gbc);
//        gbc.gridx = 1;
//        pickupDateField.setToolTipText("Enter date in YYYY-MM-DD or MM/DD/YYYY format");
//        formPanel.add(pickupDateField, gbc);
//
//        // Total Payment
//        gbc.gridx = 0;
//        gbc.gridy = 6;
//        formPanel.add(new JLabel("Total Payment:"), gbc);
//        gbc.gridx = 1;
//        formPanel.add(totalPaymentField, gbc);
//
//        // Payment Status
//        gbc.gridx = 0;
//        gbc.gridy = 7;
//        formPanel.add(new JLabel("Payment Status:"), gbc);
//        gbc.gridx = 1;
//        formPanel.add(paymentStatusCombo, gbc);
//
//        // Note
//        gbc.gridx = 0;
//        gbc.gridy = 8;
//        formPanel.add(new JLabel("Note:"), gbc);
//        gbc.gridx = 1;
//        JScrollPane noteScrollPane = new JScrollPane(noteArea);
//        noteScrollPane.setPreferredSize(new Dimension(300, 80));
//        formPanel.add(noteScrollPane, gbc);
//
//        // Buttons
//        gbc.gridx = 0;
//        gbc.gridy = 9;
//        gbc.gridwidth = 2;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        JPanel buttonPanel = new JPanel(new FlowLayout());
//
//        // Style buttons with better colors
//        saveButton.setBackground(new Color(34, 197, 94));
//        saveButton.setForeground(Color.black);
//        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
//        saveButton.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(new Color(34, 197, 94).darker(), 1),
//                new EmptyBorder(12, 25, 12, 25)));
//
//        clearButton.setBackground(new Color(239, 68, 68));
//        clearButton.setForeground(Color.black);
//        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
//        clearButton.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(new Color(239, 68, 68).darker(), 1),
//                new EmptyBorder(12, 25, 12, 25)));
//
//        buttonPanel.add(saveButton);
//        buttonPanel.add(clearButton);
//        formPanel.add(buttonPanel, gbc);
//
//        return formPanel;
//    }
//
//    private void setupMeasurementPanels() {
////        measurementPanel.removeAll();
//
//        // Shirt measurements section
//        if (!shirtPanels.isEmpty()) {
//            JLabel shirtLabel = new JLabel("Shirt Measurements");
//            shirtLabel.setFont(new Font("Arial", Font.BOLD, 16));
//            measurementPanel.add(shirtLabel);
//            measurementPanel.add(shirtMeasurementsPanel);
//        }
//
//        // Pant measurements section
//        if (!pantPanels.isEmpty()) {
//            JLabel pantLabel = new JLabel("Pant Measurements");
//            pantLabel.setFont(new Font("Arial", Font.BOLD, 16));
//            measurementPanel.add(pantLabel);
//            measurementPanel.add(pantMeasurementsPanel);
//        }
//
//        measurementPanel.revalidate();
//        measurementPanel.repaint();
//    }
//
//    private void setupEventListeners() {
//        saveButton.addActionListener(e -> saveOrder());
//        clearButton.addActionListener(e -> clearForm());
//
//        // Listen for quantity changes
//        qtyShirtsSpinner.addChangeListener(new ChangeListener() {
//            public void stateChanged(ChangeEvent e) {
//            	updateShirtMeasurements();
//            }
//        });
//        qtyPantsSpinner.addChangeListener(new ChangeListener() {
//            public void stateChanged(ChangeEvent e) {
//        		updatePantMeasurements();
//            }
//        });
//    }
//
//    private void updateShirtMeasurements() {
//        int qty = (Integer) qtyShirtsSpinner.getValue();
//        shirtMeasurementsPanel.removeAll();
//        shirtPanels.clear();
//
//        for (int i = 0; i < qty; i++) {
//            ShirtMeasurementPanel panel = new ShirtMeasurementPanel(i + 1);
//            shirtPanels.add(panel);
//            shirtMeasurementsPanel.add(panel);
//        }
//
//        setupMeasurementPanels();
//    }
//
//    private void updatePantMeasurements() {
//        int qty = (Integer) qtyPantsSpinner.getValue();
//        pantMeasurementsPanel.removeAll();
//        pantPanels.clear();
//
//        for (int i = 0; i < qty; i++) {
//            PantMeasurementPanel panel = new PantMeasurementPanel(i + 1);
//            pantPanels.add(panel);
//            pantMeasurementsPanel.add(panel);
//        }
//
//        setupMeasurementPanels();
//    }
//
//    private void saveOrder() {
//        try {
//            // Validate required fields
//            if (customerNameField.getText().trim().isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Customer name is required", "Validation Error",
//                        JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            if (customerMobileField.getText().trim().isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Customer mobile is required", "Validation Error",
//                        JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            Order order = new Order();
//            order.setCustomerName(customerNameField.getText().trim());
//            order.setCustomerMobile(customerMobileField.getText().trim());
//            order.setQtyShirts((Integer) qtyShirtsSpinner.getValue());
//            order.setQtyPants((Integer) qtyPantsSpinner.getValue());
//            order.setNote(noteArea.getText().trim());
//            order.setPaymentStatus((String) paymentStatusCombo.getSelectedItem());
//
//            // Validate and set total payment
//            if (!totalPaymentField.getText().trim().isEmpty()) {
//                try {
//                    order.setTotalPayment(new BigDecimal(totalPaymentField.getText().trim()));
//                } catch (NumberFormatException e) {
//                    JOptionPane.showMessageDialog(this, "Invalid payment amount format", "Validation Error",
//                            JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//            }
//
//            // Validate and set pickup date
//            if (!pickupDateField.getText().trim().isEmpty()) {
//                try {
//                    String dateStr = pickupDateField.getText().trim();
//                    // Support multiple date formats
//                    if (dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
//                        order.setPickupDate(LocalDateTime.parse(dateStr + "T10:00:00"));
//                    } else if (dateStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
//                        // Convert MM/dd/yyyy to yyyy-MM-dd
//                        String[] parts = dateStr.split("/");
//                        String converted = parts[2] + "-" + String.format("%02d", Integer.parseInt(parts[0])) + "-"
//                                + String.format("%02d", Integer.parseInt(parts[1]));
//                        order.setPickupDate(LocalDateTime.parse(converted + "T10:00:00"));
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD or MM/DD/YYYY",
//                                "Validation Error", JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD or MM/DD/YYYY",
//                            "Validation Error", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//            }
//
//            // Validate measurements are filled
//            for (ShirtMeasurementPanel panel : shirtPanels) {
//                if (!panel.isValid()) {
//                    JOptionPane.showMessageDialog(this,
//                            "Please fill all required shirt measurements (Height, Chest, Price)", "Validation Error",
//                            JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//            }
//
//            for (PantMeasurementPanel panel : pantPanels) {
//                if (!panel.isValid()) {
//                    JOptionPane.showMessageDialog(this,
//                            "Please fill all required pant measurements (Height, Waist, Price)", "Validation Error",
//                            JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//            }
//
//            // Create OrderView with measurements
//            OrderView orderView = new OrderView(order);
//
//            // Add shirt measurements to OrderView
//            for (ShirtMeasurementPanel panel : shirtPanels) {
//                Shirt shirt = panel.getShirtData();
//                orderView.addShirt(shirt);
//            }
//
//            // Add pant measurements to OrderView
//            for (PantMeasurementPanel panel : pantPanels) {
//                Pant pant = panel.getPantData();
//                orderView.addPant(pant);
//            }
//
//            // Save order with measurements to database
//            int orderId = services.OrderService.saveOrder(orderView);
//
//            if (orderId > 0) {
//                order.setOrderID(orderId);
//
//                // Show success message with order number
//                String successMessage = String.format(
//                        "✅ ORDER CREATED SUCCESSFULLY!\n\n" +
//                                "Order Number: #%d\n" +
//                                "Customer: %s\n" +
//                                "Mobile: %s\n" +
//                                "Shirts: %d | Pants: %d\n" +
//                                "Total Payment: ₹%s\n\n" +
//                                "All measurements have been saved!",
//                        orderId,
//                        order.getCustomerName(),
//                        order.getCustomerMobile(),
//                        order.getQtyShirts(),
//                        order.getQtyPants(),
//                        order.getTotalPayment() != null ? order.getTotalPayment().toString() : "0.00");
//
//                JOptionPane.showMessageDialog(this, successMessage, "Order Created - #" + orderId,
//                        JOptionPane.INFORMATION_MESSAGE);
//                clearForm();
//            } else {
//                JOptionPane.showMessageDialog(this,
//                        "❌ Failed to save order to database.\nPlease check database connection.", "Error",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error saving order: " + ex.getMessage(), "Error",
//                    JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void clearForm() {
//        customerNameField.setText("");
//        customerMobileField.setText("");
//        qtyShirtsSpinner.setValue(0);
//        qtyPantsSpinner.setValue(0);
//        noteArea.setText("");
//        totalPaymentField.setText("");
//        pickupDateField.setText("");
//        paymentStatusCombo.setSelectedIndex(0);
//
//        shirtPanels.clear();
//        pantPanels.clear();
//        shirtMeasurementsPanel.removeAll();
//        pantMeasurementsPanel.removeAll();
//        setupMeasurementPanels();
//    }
//
//    // Inner class for individual shirt measurement
//    private class ShirtMeasurementPanel extends JPanel {
//        private int shirtNumber;
//        private JComboBox<String> shirtTypeCombo;
//        private JTextField priceField;
//        private JTextField heightField, chestField, front1Field, front2Field;
//        private JTextField shoulderField, bellyField, sleeveField, collarField, cupField;
//        private JTextField otherField;
//
//        public ShirtMeasurementPanel(int shirtNumber) {
//            this.shirtNumber = shirtNumber;
//            setBorder(BorderFactory.createTitledBorder("Shirt " + shirtNumber));
//            initializeComponents();
//            setupLayout();
//        }
//
//        private void initializeComponents() {
//            shirtTypeCombo = new JComboBox<>(
//                    new String[] { "Regular Shirt", "Formal Shirt", "Casual Shirt", "T-Shirt" });
//            priceField = new JTextField(12);
//            heightField = new JTextField(12);
//            chestField = new JTextField(12);
//            front1Field = new JTextField(12);
//            front2Field = new JTextField(12);
//            shoulderField = new JTextField(12);
//            bellyField = new JTextField(12);
//            sleeveField = new JTextField(12);
//            collarField = new JTextField(12);
//            cupField = new JTextField(12);
//            otherField = new JTextField(20);
//        }
//
//        private void setupLayout() {
//            setLayout(new GridBagLayout());
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.insets = new Insets(2, 2, 2, 2);
//            gbc.anchor = GridBagConstraints.WEST;
//
//            // Type and Price
//            gbc.gridx = 0;
//            gbc.gridy = 0;
//            add(new JLabel("Type:"), gbc);
//            gbc.gridx = 1;
//            add(shirtTypeCombo, gbc);
//            gbc.gridx = 2;
//            add(new JLabel("Price:"), gbc);
//            gbc.gridx = 3;
//            add(priceField, gbc);
//
//            // Measurements
//            gbc.gridx = 0;
//            gbc.gridy = 1;
//            add(new JLabel("Height:"), gbc);
//            gbc.gridx = 1;
//            add(heightField, gbc);
//            gbc.gridx = 2;
//            add(new JLabel("Chest:"), gbc);
//            gbc.gridx = 3;
//            add(chestField, gbc);
//
//            gbc.gridx = 0;
//            gbc.gridy = 2;
//            add(new JLabel("Front1:"), gbc);
//            gbc.gridx = 1;
//            add(front1Field, gbc);
//            gbc.gridx = 2;
//            add(new JLabel("Front2:"), gbc);
//            gbc.gridx = 3;
//            add(front2Field, gbc);
//
//            gbc.gridx = 0;
//            gbc.gridy = 3;
//            add(new JLabel("Shoulder:"), gbc);
//            gbc.gridx = 1;
//            add(shoulderField, gbc);
//            gbc.gridx = 2;
//            add(new JLabel("Belly:"), gbc);
//            gbc.gridx = 3;
//            add(bellyField, gbc);
//
//            gbc.gridx = 0;
//            gbc.gridy = 4;
//            add(new JLabel("Sleeve:"), gbc);
//            gbc.gridx = 1;
//            add(sleeveField, gbc);
//            gbc.gridx = 2;
//            add(new JLabel("Collar:"), gbc);
//            gbc.gridx = 3;
//            add(collarField, gbc);
//
//            gbc.gridx = 0;
//            gbc.gridy = 5;
//            add(new JLabel("Cup:"), gbc);
//            gbc.gridx = 1;
//            add(cupField, gbc);
//            gbc.gridx = 2;
//            add(new JLabel("Other:"), gbc);
//            gbc.gridx = 3;
//            add(otherField, gbc);
//        }
//
//        public boolean isValid() {
//            return !heightField.getText().trim().isEmpty() &&
//                    !chestField.getText().trim().isEmpty() &&
//                    !priceField.getText().trim().isEmpty();
//        }
//
//        public Shirt getShirtData() {
//            Shirt shirt = new Shirt();
//            shirt.setShirtType((String) shirtTypeCombo.getSelectedItem());
//
//            try {
//                if (!priceField.getText().trim().isEmpty()) {
//                    shirt.setPrice(new BigDecimal(priceField.getText().trim()));
//                }
//                if (!heightField.getText().trim().isEmpty()) {
//                    shirt.setHeight(new BigDecimal(heightField.getText().trim()));
//                }
//                if (!chestField.getText().trim().isEmpty()) {
//                    shirt.setChest(new BigDecimal(chestField.getText().trim()));
//                }
//                if (!front1Field.getText().trim().isEmpty()) {
//                    shirt.setFront1(new BigDecimal(front1Field.getText().trim()));
//                }
//                if (!front2Field.getText().trim().isEmpty()) {
//                    shirt.setFront2(new BigDecimal(front2Field.getText().trim()));
//                }
//                if (!shoulderField.getText().trim().isEmpty()) {
//                    shirt.setShoulder(new BigDecimal(shoulderField.getText().trim()));
//                }
//                if (!bellyField.getText().trim().isEmpty()) {
//                    shirt.setBelly(new BigDecimal(bellyField.getText().trim()));
//                }
//                if (!sleeveField.getText().trim().isEmpty()) {
//                    shirt.setSleeve(new BigDecimal(sleeveField.getText().trim()));
//                }
//                if (!collarField.getText().trim().isEmpty()) {
//                    shirt.setCollar(new BigDecimal(collarField.getText().trim()));
//                }
//                if (!cupField.getText().trim().isEmpty()) {
//                    shirt.setCup(new BigDecimal(cupField.getText().trim()));
//                }
//                shirt.setOther(otherField.getText().trim());
//            } catch (NumberFormatException e) {
//                throw new RuntimeException("Invalid number format in shirt measurements");
//            }
//
//            return shirt;
//        }
//    }
//
//    // Inner class for individual pant measurement
//    private class PantMeasurementPanel extends JPanel {
//        private int pantNumber;
//        private JComboBox<String> pantTypeCombo;
//        private JTextField priceField;
//        private JTextField heightField, waistField, seatField, thighField;
//        private JTextField kneeField, bottomField, hangField;
//        private JTextField otherField;
//
//        public PantMeasurementPanel(int pantNumber) {
//            this.pantNumber = pantNumber;
//            setBorder(BorderFactory.createTitledBorder("Pant " + pantNumber));
//            initializeComponents();
//            setupLayout();
//        }
//
//        private void initializeComponents() {
//            pantTypeCombo = new JComboBox<>(new String[] { "Regular Pant", "Formal Pant", "Casual Pant", "Jeans" });
//            priceField = new JTextField(12);
//            heightField = new JTextField(12);
//            waistField = new JTextField(12);
//            seatField = new JTextField(12);
//            thighField = new JTextField(12);
//            kneeField = new JTextField(12);
//            bottomField = new JTextField(12);
//            hangField = new JTextField(12);
//            otherField = new JTextField(20);
//        }
//
//        private void setupLayout() {
//            setLayout(new GridBagLayout());
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.insets = new Insets(2, 2, 2, 2);
//            gbc.anchor = GridBagConstraints.WEST;
//
//            // Type and Price
//            gbc.gridx = 0;
//            gbc.gridy = 0;
//            add(new JLabel("Type:"), gbc);
//            gbc.gridx = 1;
//            add(pantTypeCombo, gbc);
//            gbc.gridx = 2;
//            add(new JLabel("Price:"), gbc);
//            gbc.gridx = 3;
//            add(priceField, gbc);
//
//            // Measurements
//            gbc.gridx = 0;
//            gbc.gridy = 1;
//            add(new JLabel("Height:"), gbc);
//            gbc.gridx = 1;
//            add(heightField, gbc);
//            gbc.gridx = 2;
//            add(new JLabel("Waist:"), gbc);
//            gbc.gridx = 3;
//            add(waistField, gbc);
//
//            gbc.gridx = 0;
//            gbc.gridy = 2;
//            add(new JLabel("Seat:"), gbc);
//            gbc.gridx = 1;
//            add(seatField, gbc);
//            gbc.gridx = 2;
//            add(new JLabel("Thigh:"), gbc);
//            gbc.gridx = 3;
//            add(thighField, gbc);
//
//            gbc.gridx = 0;
//            gbc.gridy = 3;
//            add(new JLabel("Knee:"), gbc);
//            gbc.gridx = 1;
//            add(kneeField, gbc);
//            gbc.gridx = 2;
//            add(new JLabel("Bottom:"), gbc);
//            gbc.gridx = 3;
//            add(bottomField, gbc);
//
//            gbc.gridx = 0;
//            gbc.gridy = 4;
//            add(new JLabel("Hang:"), gbc);
//            gbc.gridx = 1;
//            add(hangField, gbc);
//            gbc.gridx = 2;
//            add(new JLabel("Other:"), gbc);
//            gbc.gridx = 3;
//            add(otherField, gbc);
//        }
//
//        public boolean isValid() {
//            return !heightField.getText().trim().isEmpty() &&
//                    !waistField.getText().trim().isEmpty() &&
//                    !priceField.getText().trim().isEmpty();
//        }
//
//        public Pant getPantData() {
//            Pant pant = new Pant();
//            pant.setPantType((String) pantTypeCombo.getSelectedItem());
//
//            try {
//                if (!priceField.getText().trim().isEmpty()) {
//                    pant.setPrice(new BigDecimal(priceField.getText().trim()));
//                }
//                if (!heightField.getText().trim().isEmpty()) {
//                    pant.setHeight(new BigDecimal(heightField.getText().trim()));
//                }
//                if (!waistField.getText().trim().isEmpty()) {
//                    pant.setWaist(new BigDecimal(waistField.getText().trim()));
//                }
//                if (!seatField.getText().trim().isEmpty()) {
//                    pant.setSeat(new BigDecimal(seatField.getText().trim()));
//                }
//                if (!thighField.getText().trim().isEmpty()) {
//                    pant.setThigh(new BigDecimal(thighField.getText().trim()));
//                }
//                if (!kneeField.getText().trim().isEmpty()) {
//                    pant.setKnee(new BigDecimal(kneeField.getText().trim()));
//                }
//                if (!bottomField.getText().trim().isEmpty()) {
//                    pant.setBottom(new BigDecimal(bottomField.getText().trim()));
//                }
//                if (!hangField.getText().trim().isEmpty()) {
//                    pant.setHang(new BigDecimal(hangField.getText().trim()));
//                }
//                pant.setOther(otherField.getText().trim());
//            } catch (NumberFormatException e) {
//                throw new RuntimeException("Invalid number format in pant measurements");
//            }
//
//            return pant;
//        }
//    }
//}

package components;

import models.*;
import services.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderFormPanel extends JPanel {
    private JTextField customerNameField;
    private JTextField customerMobileField;
    private JSpinner qtyShirtsSpinner;
    private JSpinner qtyPantsSpinner;
    private JTextArea noteArea;
    private JTextField totalPaymentField;
    private JComboBox<String> paymentStatusCombo;
    private JTextField advancePayment;
    private JTextField pickupDateField;
    private JButton saveButton;
    private JButton clearButton;

    // Measurement panels
    private JPanel measurementPanel;
    private JPanel shirtMeasurementsPanel;
    private JPanel pantMeasurementsPanel;
    private List<ShirtMeasurementPanel> shirtPanels;
    private List<PantMeasurementPanel> pantPanels;

    public OrderFormPanel() {
        shirtPanels = new ArrayList<>();
        pantPanels = new ArrayList<>();
        initializeComponents();
        setupLayout();
        setupEventListeners();
    }

    private void initializeComponents() {
        customerNameField = new JTextField(25);
        customerMobileField = new JTextField(20);
        qtyShirtsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        qtyPantsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        noteArea = new JTextArea(4, 30);
        String payment=getTotalPayment().toString();
        totalPaymentField = new JTextField(15);
        totalPaymentField.setText(payment);
        paymentStatusCombo = new JComboBox<>(new String[]{"Pending", "Partial", "Paid", "Overdue"});
        advancePayment=new JTextField(15);
        pickupDateField = new JTextField(20);
        saveButton = new JButton("Save Order");
        clearButton = new JButton("Clear Form");

        // Initialize measurement panels with proper settings
        measurementPanel = new JPanel();
        measurementPanel.setLayout(new BoxLayout(measurementPanel, BoxLayout.Y_AXIS));
        measurementPanel.setBackground(Color.WHITE);
        
        shirtMeasurementsPanel = new JPanel();
        shirtMeasurementsPanel.setLayout(new BoxLayout(shirtMeasurementsPanel, BoxLayout.Y_AXIS));
        shirtMeasurementsPanel.setBackground(Color.WHITE);
        shirtMeasurementsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        pantMeasurementsPanel = new JPanel();
        pantMeasurementsPanel.setLayout(new BoxLayout(pantMeasurementsPanel, BoxLayout.Y_AXIS));
        pantMeasurementsPanel.setBackground(Color.WHITE);
        pantMeasurementsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
    
    private BigDecimal getTotalPayment() {
    
    	BigDecimal subtotal = BigDecimal.ZERO;

        try {
			for (ShirtMeasurementPanel panel : shirtPanels) {
			    Shirt shirt = panel.getShirtData();
			    subtotal=subtotal.add(shirt.getPrice());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Add pant measurements to OrderView
        try {
			for (PantMeasurementPanel panel : pantPanels) {
			    Pant pant = panel.getPantData();
			    subtotal=subtotal.add(pant.getPrice());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return subtotal;
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        // Create main container with proper scrolling
        JPanel mainContainer = new JPanel(new BorderLayout());

        // Customer details panel with order ID display
        JPanel customerPanel = createCustomerDetailsPanel();

        // Setup measurement panels
        setupMeasurementPanels();

        // Create measurement container with title
        JPanel measurementContainer = new JPanel(new BorderLayout());
        measurementContainer.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            "Measurement Details",
            0, 0, new Font("Arial", Font.BOLD, 16)
        ));

        // Add measurement panel with proper scrolling
        JScrollPane measurementScrollPane = new JScrollPane(measurementPanel);
        measurementScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        measurementScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        measurementScrollPane.setPreferredSize(new Dimension(0, 400));
        measurementContainer.add(measurementScrollPane, BorderLayout.CENTER);

        // Add components to main container
        mainContainer.add(customerPanel, BorderLayout.NORTH);
        mainContainer.add(measurementContainer, BorderLayout.CENTER);

        // Add main container with scrolling to the panel
        JScrollPane mainScrollPane = new JScrollPane(mainContainer);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(mainScrollPane, BorderLayout.CENTER);
    }

    private JPanel createCustomerDetailsPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            "Customer & Order Details",
            0, 0, new Font("Arial", Font.BOLD, 16)
        ));
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        // Order ID display (will be shown after saving)
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Order ID:"), gbc);
        gbc.gridx = 1;
        int orderId=services.OrderService.newOrderId()+1;
        JLabel orderIdLabel = new JLabel("(Will be generated on Saving the order)");
        orderIdLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        orderIdLabel.setForeground(Color.GRAY);
        formPanel.add(orderIdLabel, gbc);

        // Customer Name
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Customer Name:"), gbc);
        gbc.gridx = 1;
        formPanel.add(customerNameField, gbc);

        // Customer Mobile
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Mobile:"), gbc);
        gbc.gridx = 1;
        formPanel.add(customerMobileField, gbc);

        // Qty Shirts
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Qty Shirts:"), gbc);
        gbc.gridx = 1;
        formPanel.add(qtyShirtsSpinner, gbc);

        // Qty Pants
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Qty Pants:"), gbc);
        gbc.gridx = 1;
        formPanel.add(qtyPantsSpinner, gbc);

        // Pickup Date
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Pickup Date (YYYY-MM-DD or MM/DD/YYYY):"), gbc);
        gbc.gridx = 1;
        pickupDateField.setToolTipText("Enter date in YYYY-MM-DD or MM/DD/YYYY format");
        formPanel.add(pickupDateField, gbc);

        // Total Payment
        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(new JLabel("Total Payment:"), gbc);
        gbc.gridx = 1;
        formPanel.add(totalPaymentField, gbc);

        // Payment Status
        gbc.gridx = 0; gbc.gridy = 7;
        formPanel.add(new JLabel("Payment Status:"), gbc);
        gbc.gridx = 1;
        formPanel.add(paymentStatusCombo, gbc);
        
     // Total Payment
        gbc.gridx = 0; gbc.gridy = 8;
        formPanel.add(new JLabel("Advance Payment:"), gbc);
        gbc.gridx = 1;
        formPanel.add(advancePayment, gbc);

        // Note
        gbc.gridx = 0; gbc.gridy = 9;
        formPanel.add(new JLabel("Note:"), gbc);
        gbc.gridx = 1;
        JScrollPane noteScrollPane = new JScrollPane(noteArea);
        noteScrollPane.setPreferredSize(new Dimension(300, 80));
        formPanel.add(noteScrollPane, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Style buttons with better colors
        saveButton.setBackground(new Color(34, 197, 94));
        saveButton.setForeground(Color.black);
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(34, 197, 94).darker(), 1),
            new EmptyBorder(12, 25, 12, 25)
        ));

        clearButton.setBackground(new Color(239, 68, 68));
        clearButton.setForeground(Color.black);
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(239, 68, 68).darker(), 1),
            new EmptyBorder(12, 25, 12, 25)
        ));

        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        formPanel.add(buttonPanel, gbc);

        return formPanel;
    }

    private void setupMeasurementPanels() {
        measurementPanel.removeAll();

        // Add some spacing
        measurementPanel.add(Box.createVerticalStrut(10));

        // Shirt measurements section
        if (!shirtPanels.isEmpty()) {
            JLabel shirtLabel = new JLabel("Shirt Measurements (" + shirtPanels.size() + " shirts)");
            shirtLabel.setFont(new Font("Arial", Font.BOLD, 16));
            shirtLabel.setForeground(new Color(25, 39, 77));
            shirtLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            measurementPanel.add(shirtLabel);
            
            // Ensure shirt measurements panel is visible
            shirtMeasurementsPanel.setVisible(true);
            measurementPanel.add(shirtMeasurementsPanel);
            measurementPanel.add(Box.createVerticalStrut(20));
        }

        // Pant measurements section
        if (!pantPanels.isEmpty()) {
            JLabel pantLabel = new JLabel("Pant Measurements (" + pantPanels.size() + " pants)");
            pantLabel.setFont(new Font("Arial", Font.BOLD, 16));
            pantLabel.setForeground(new Color(25, 39, 77));
            pantLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            measurementPanel.add(pantLabel);
            
            // Ensure pant measurements panel is visible
            pantMeasurementsPanel.setVisible(true);
            measurementPanel.add(pantMeasurementsPanel);
            measurementPanel.add(Box.createVerticalStrut(20));
        }

        // Show a message when no measurements are needed
        if (shirtPanels.isEmpty() && pantPanels.isEmpty()) {
            JLabel noMeasurementsLabel = new JLabel("No measurements needed - Set quantity of shirts or pants above");
            noMeasurementsLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            noMeasurementsLabel.setForeground(Color.GRAY);
            noMeasurementsLabel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
            measurementPanel.add(noMeasurementsLabel);
        }

        measurementPanel.revalidate();
        measurementPanel.repaint();
    }

    private void setupEventListeners() {
        saveButton.addActionListener(e -> saveOrder());
        clearButton.addActionListener(e -> clearForm());

        // Listen for quantity changes with immediate UI updates
        qtyShirtsSpinner.addChangeListener(e -> {
            SwingUtilities.invokeLater(() -> {
                updateShirtMeasurements();
                setupMeasurementPanels();
                measurementPanel.revalidate();
                measurementPanel.repaint();
                this.revalidate();
                this.repaint();
            });
        });
        
        qtyPantsSpinner.addChangeListener(e -> {
            SwingUtilities.invokeLater(() -> {
                updatePantMeasurements();
                setupMeasurementPanels();
                measurementPanel.revalidate();
                measurementPanel.repaint();
                this.revalidate();
                this.repaint();
            });
        });

        // Initialize with default values
        updateShirtMeasurements();
        updatePantMeasurements();
        setupMeasurementPanels();
    }

    private void updateShirtMeasurements() {
        int qty = (Integer) qtyShirtsSpinner.getValue();
        
        // Clear existing panels
        shirtMeasurementsPanel.removeAll();
        shirtPanels.clear();

        // Create new panels based on quantity
        for (int i = 0; i < qty; i++) {
            ShirtMeasurementPanel panel = new ShirtMeasurementPanel(i + 1);
            panel.setVisible(true);
            shirtPanels.add(panel);
            shirtMeasurementsPanel.add(panel);
            
            // Add some spacing between panels
            if (i < qty - 1) {
                shirtMeasurementsPanel.add(Box.createVerticalStrut(10));
            }
        }

        shirtMeasurementsPanel.revalidate();
        shirtMeasurementsPanel.repaint();
        
        System.out.println("Updated shirt measurements: " + qty + " panels created");
    }

    private void updatePantMeasurements() {
        int qty = (Integer) qtyPantsSpinner.getValue();
        
        // Clear existing panels
        pantMeasurementsPanel.removeAll();
        pantPanels.clear();

        // Create new panels based on quantity
        for (int i = 0; i < qty; i++) {
            PantMeasurementPanel panel = new PantMeasurementPanel(i + 1);
            panel.setVisible(true);
            pantPanels.add(panel);
            pantMeasurementsPanel.add(panel);
            
            // Add some spacing between panels
            if (i < qty - 1) {
                pantMeasurementsPanel.add(Box.createVerticalStrut(10));
            }
        }

        pantMeasurementsPanel.revalidate();
        pantMeasurementsPanel.repaint();
        
        System.out.println("Updated pant measurements: " + qty + " panels created");
    }

    private void saveOrder() {
        try {
            // Validate required fields
            if (customerNameField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Customer name is required", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (customerMobileField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Customer mobile is required", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Order order = new Order();
            order.setCustomerName(customerNameField.getText().trim());
            order.setCustomerMobile(customerMobileField.getText().trim());
            order.setQtyShirts((Integer) qtyShirtsSpinner.getValue());
            order.setQtyPants((Integer) qtyPantsSpinner.getValue());
            order.setNote(noteArea.getText().trim());
            order.setPaymentStatus((String) paymentStatusCombo.getSelectedItem());
            order.setAdvancePayment(new BigDecimal(advancePayment.getText().toString()));

            // Validate and set total payment
            if (!totalPaymentField.getText().trim().isEmpty()) {
                try {
                    order.setTotalPayment(new BigDecimal(totalPaymentField.getText().trim()));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid payment amount format", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
//            if(!advancePayment.getText().trim().isEmpty() && ((String) paymentStatusCombo.getSelectedItem()).equals("Partial") ) {
//            	JOptionPane.showMessageDialog(this,"Partial Payment Status needs Advance Payment","Validation Error",JOptionPane.ERROR_MESSAGE);
//            }

            // Validate and set pickup date
            if (!pickupDateField.getText().trim().isEmpty()) {
                try {
                    String dateStr = pickupDateField.getText().trim();
                    // Support multiple date formats
                    if (dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        order.setPickupDate(LocalDateTime.parse(dateStr + "T10:00:00"));
                    } else if (dateStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
                        // Convert MM/dd/yyyy to yyyy-MM-dd
                        String[] parts = dateStr.split("/");
                        String converted = parts[2] + "-" + String.format("%02d", Integer.parseInt(parts[0])) + "-" + String.format("%02d", Integer.parseInt(parts[1]));
                        order.setPickupDate(LocalDateTime.parse(converted + "T10:00:00"));
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD or MM/DD/YYYY", "Validation Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD or MM/DD/YYYY", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Validate measurements are filled
            for (ShirtMeasurementPanel panel : shirtPanels) {
                if (!panel.isValid()) {
                    JOptionPane.showMessageDialog(this, "Please fill all required shirt measurements (Height, Chest, Price)", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            for (PantMeasurementPanel panel : pantPanels) {
                if (!panel.isValid()) {
                    JOptionPane.showMessageDialog(this, "Please fill all required pant measurements (Height, Waist, Price)", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Create OrderView with measurements
            OrderView orderView = new OrderView(order);

            // Add shirt measurements to OrderView
            for (ShirtMeasurementPanel panel : shirtPanels) {
                Shirt shirt = panel.getShirtData();
                orderView.addShirt(shirt);
            }

            // Add pant measurements to OrderView
            for (PantMeasurementPanel panel : pantPanels) {
                Pant pant = panel.getPantData();
                orderView.addPant(pant);
            }

            // Save order with measurements to database
            int orderId = services.OrderService.saveOrder(orderView);

            if (orderId > 0) {
                order.setOrderID(orderId);

                // Show success message with order number
                String successMessage = String.format(
                    "✅ ORDER CREATED SUCCESSFULLY!\n\n" +
                    "Order Number: #%d\n" +
                    "Customer: %s\n" +
                    "Mobile: %s\n" +
                    "Shirts: %d | Pants: %d\n" +
                    "Total Payment: ₹%s\n\n" +
                    "Advance Payment: ₹%s\\n\\n"+
                    "All measurements have been saved!",
                    orderId,
                    order.getCustomerName(),
                    order.getCustomerMobile(),
                    order.getQtyShirts(),
                    order.getQtyPants(),
                    order.getTotalPayment() != null ? order.getTotalPayment().toString() : "0.00",
                    order.getAdvancePayment() != null ? order.getAdvancePayment().toString() : "0.00"
                );

                JOptionPane.showMessageDialog(this, successMessage, "Order Created - #" + orderId, JOptionPane.INFORMATION_MESSAGE);
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Failed to save order to database.\nPlease check database connection.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving order: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    private void clearForm() {
        customerNameField.setText("");
        customerMobileField.setText("");
        qtyShirtsSpinner.setValue(0);
        qtyPantsSpinner.setValue(0);
        noteArea.setText("");
        totalPaymentField.setText("");
        pickupDateField.setText("");
        paymentStatusCombo.setSelectedIndex(0);
        advancePayment.setText("");

        shirtPanels.clear();
        pantPanels.clear();
        shirtMeasurementsPanel.removeAll();
        pantMeasurementsPanel.removeAll();
        setupMeasurementPanels();
    }

    // Inner class for individual shirt measurement
    private class ShirtMeasurementPanel extends JPanel {
        private int shirtNumber;
        private JComboBox<String> shirtTypeCombo;
        private JTextField priceField;
        private JTextField heightField, chestField, front1Field, front2Field;
        private JTextField shoulderField, bellyField, sleeveField, collarField, cupField;
        private JTextField otherField;

        public ShirtMeasurementPanel(int shirtNumber) {
            this.shirtNumber = shirtNumber;
            setBorder(BorderFactory.createTitledBorder("Shirt " + shirtNumber));
            initializeComponents();
            setupLayout();
        }

        private void initializeComponents() {
        	String[] shirtType = services.OrderService.getShirtTypes();
            shirtTypeCombo = new JComboBox<>(shirtType);
//            shirtTypeCombo = new JComboBox<>(new String[]{"Regular Shirt", "Formal Shirt", "Casual Shirt", "T-Shirt"});
            String price=services.OrderService.getShirtPrice((String) shirtTypeCombo.getSelectedItem()).toString();
            shirtTypeCombo.addActionListener(e -> {
            	String eprice=services.OrderService.getShirtPrice((String) shirtTypeCombo.getSelectedItem()).toString();
                priceField.setText(eprice);

                String payment=getTotalPayment().toString();
                totalPaymentField.setText(payment);
            });
            priceField = new JTextField(12);
            priceField.setText(price);
            heightField = new JTextField(12);
            chestField = new JTextField(12);
            front1Field = new JTextField(12);
            front2Field = new JTextField(12);
            shoulderField = new JTextField(12);
            bellyField = new JTextField(12);
            sleeveField = new JTextField(12);
            collarField = new JTextField(12);
            cupField = new JTextField(12);
            otherField = new JTextField(20);
        }

        private void setupLayout() {
            setLayout(new GridBagLayout());
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            // Type and Price
            gbc.gridx = 0; gbc.gridy = 0;
            add(new JLabel("Type:"), gbc);
            gbc.gridx = 1;
            add(shirtTypeCombo, gbc);
            gbc.gridx = 2;
            add(new JLabel("Price:"), gbc);
            gbc.gridx = 3;
            add(priceField, gbc);

            // Measurements
            gbc.gridx = 0; gbc.gridy = 1;
            add(new JLabel("Height:"), gbc);
            gbc.gridx = 1;
            add(heightField, gbc);
            gbc.gridx = 2;
            add(new JLabel("Chest:"), gbc);
            gbc.gridx = 3;
            add(chestField, gbc);

            gbc.gridx = 0; gbc.gridy = 2;
            add(new JLabel("Front1:"), gbc);
            gbc.gridx = 1;
            add(front1Field, gbc);
            gbc.gridx = 2;
            add(new JLabel("Front2:"), gbc);
            gbc.gridx = 3;
            add(front2Field, gbc);

            gbc.gridx = 0; gbc.gridy = 3;
            add(new JLabel("Shoulder:"), gbc);
            gbc.gridx = 1;
            add(shoulderField, gbc);
            gbc.gridx = 2;
            add(new JLabel("Belly:"), gbc);
            gbc.gridx = 3;
            add(bellyField, gbc);

            gbc.gridx = 0; gbc.gridy = 4;
            add(new JLabel("Sleeve:"), gbc);
            gbc.gridx = 1;
            add(sleeveField, gbc);
            gbc.gridx = 2;
            add(new JLabel("Collar:"), gbc);
            gbc.gridx = 3;
            add(collarField, gbc);

            gbc.gridx = 0; gbc.gridy = 5;
            add(new JLabel("Cup:"), gbc);
            gbc.gridx = 1;
            add(cupField, gbc);
            gbc.gridx = 2;
            add(new JLabel("Other:"), gbc);
            gbc.gridx = 3;
            add(otherField, gbc);
        }

        public boolean isValid() {
        	try {
	            return !heightField.getText().trim().isEmpty() && 
	                   !chestField.getText().trim().isEmpty() &&
	                   !priceField.getText().trim().isEmpty();
        	}catch(NullPointerException e)
            {
                System.out.print("NullPointerException Caught");
                return false;
            }
            
        }

        public Shirt getShirtData() {
            Shirt shirt = new Shirt();
            shirt.setShirtType((String) shirtTypeCombo.getSelectedItem());

            try {
                if (!priceField.getText().trim().isEmpty()) {
                    shirt.setPrice(new BigDecimal(priceField.getText().trim()));
                }
                if (!heightField.getText().trim().isEmpty()) {
                    shirt.setHeight(new BigDecimal(heightField.getText().trim()));
                }
                if (!chestField.getText().trim().isEmpty()) {
                    shirt.setChest(new BigDecimal(chestField.getText().trim()));
                }
                if (!front1Field.getText().trim().isEmpty()) {
                    shirt.setFront1(new BigDecimal(front1Field.getText().trim()));
                }
                if (!front2Field.getText().trim().isEmpty()) {
                    shirt.setFront2(new BigDecimal(front2Field.getText().trim()));
                }
                if (!shoulderField.getText().trim().isEmpty()) {
                    shirt.setShoulder(new BigDecimal(shoulderField.getText().trim()));
                }
                if (!bellyField.getText().trim().isEmpty()) {
                    shirt.setBelly(new BigDecimal(bellyField.getText().trim()));
                }
                if (!sleeveField.getText().trim().isEmpty()) {
                    shirt.setSleeve(new BigDecimal(sleeveField.getText().trim()));
                }
                if (!collarField.getText().trim().isEmpty()) {
                    shirt.setCollar(new BigDecimal(collarField.getText().trim()));
                }
                if (!cupField.getText().trim().isEmpty()) {
                    shirt.setCup(new BigDecimal(cupField.getText().trim()));
                }
                shirt.setOther(otherField.getText().trim());
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid number format in shirt measurements");
            }

            return shirt;
        }
    }

    // Inner class for individual pant measurement
    private class PantMeasurementPanel extends JPanel {
        private int pantNumber;
        private JComboBox<String> pantTypeCombo;
        private JTextField priceField;
        private JTextField heightField, waistField, seatField, thighField;
        private JTextField kneeField, bottomField, hangField;
        private JTextField otherField;

        public PantMeasurementPanel(int pantNumber) {
            this.pantNumber = pantNumber;
            setBorder(BorderFactory.createTitledBorder("Pant " + pantNumber));
            initializeComponents();
            setupLayout();
        }

        private void initializeComponents() {
        	String[] pantType =services.OrderService.getPantTypes();
            pantTypeCombo = new JComboBox<>(pantType);
//            pantTypeCombo = new JComboBox<>(new String[]{"Regular Pant", "Formal Pant", "Casual Pant", "Jeans"});
            String price=services.OrderService.getPantPrice((String) pantTypeCombo.getSelectedItem()).toString();
            pantTypeCombo.addActionListener(e -> {
            	String eprice=services.OrderService.getPantPrice((String) pantTypeCombo.getSelectedItem()).toString();
                priceField.setText(eprice);

                String payment=getTotalPayment().toString();
                totalPaymentField.setText(payment);
            });
            priceField = new JTextField(12);
            priceField.setText(price);
            heightField = new JTextField(12);
            waistField = new JTextField(12);
            seatField = new JTextField(12);
            thighField = new JTextField(12);
            kneeField = new JTextField(12);
            bottomField = new JTextField(12);
            hangField = new JTextField(12);
            otherField = new JTextField(20);
        }

        private void setupLayout() {
            setLayout(new GridBagLayout());
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            // Type and Price
            gbc.gridx = 0; gbc.gridy = 0;
            add(new JLabel("Type:"), gbc);
            gbc.gridx = 1;
            add(pantTypeCombo, gbc);
            gbc.gridx = 2;
            add(new JLabel("Price:"), gbc);
            gbc.gridx = 3;
            add(priceField, gbc);

            // Measurements
            gbc.gridx = 0; gbc.gridy = 1;
            add(new JLabel("Height:"), gbc);
            gbc.gridx = 1;
            add(heightField, gbc);
            gbc.gridx = 2;
            add(new JLabel("Waist:"), gbc);
            gbc.gridx = 3;
            add(waistField, gbc);

            gbc.gridx = 0; gbc.gridy = 2;
            add(new JLabel("Seat:"), gbc);
            gbc.gridx = 1;
            add(seatField, gbc);
            gbc.gridx = 2;
            add(new JLabel("Thigh:"), gbc);
            gbc.gridx = 3;
            add(thighField, gbc);

            gbc.gridx = 0; gbc.gridy = 3;
            add(new JLabel("Knee:"), gbc);
            gbc.gridx = 1;
            add(kneeField, gbc);
            gbc.gridx = 2;
            add(new JLabel("Bottom:"), gbc);
            gbc.gridx = 3;
            add(bottomField, gbc);

            gbc.gridx = 0; gbc.gridy = 4;
            add(new JLabel("Hang:"), gbc);
            gbc.gridx = 1;
            add(hangField, gbc);
            gbc.gridx = 2;
            add(new JLabel("Other:"), gbc);
            gbc.gridx = 3;
            add(otherField, gbc);
        }

        public boolean isValid() {
        	try {
        		return !heightField.getText().trim().isEmpty() && 
                        !waistField.getText().trim().isEmpty() &&
                        !priceField.getText().trim().isEmpty();
        	}catch(NullPointerException e)
            {
                System.out.print("NullPointerException Caught");
                return false;
            }
            
        }

        public Pant getPantData() {
            Pant pant = new Pant();
            pant.setPantType((String) pantTypeCombo.getSelectedItem());

            try {
                if (!priceField.getText().trim().isEmpty()) {
                    pant.setPrice(new BigDecimal(priceField.getText().trim()));
                }
                if (!heightField.getText().trim().isEmpty()) {
                    pant.setHeight(new BigDecimal(heightField.getText().trim()));
                }
                if (!waistField.getText().trim().isEmpty()) {
                    pant.setWaist(new BigDecimal(waistField.getText().trim()));
                }
                if (!seatField.getText().trim().isEmpty()) {
                    pant.setSeat(new BigDecimal(seatField.getText().trim()));
                }
                if (!thighField.getText().trim().isEmpty()) {
                    pant.setThigh(new BigDecimal(thighField.getText().trim()));
                }
                if (!kneeField.getText().trim().isEmpty()) {
                    pant.setKnee(new BigDecimal(kneeField.getText().trim()));
                }
                if (!bottomField.getText().trim().isEmpty()) {
                    pant.setBottom(new BigDecimal(bottomField.getText().trim()));
                }
                if (!hangField.getText().trim().isEmpty()) {
                    pant.setHang(new BigDecimal(hangField.getText().trim()));
                }
                pant.setOther(otherField.getText().trim());
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid number format in pant measurements");
            }

            return pant;
        }
    }
}