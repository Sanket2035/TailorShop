
package components;

import models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

public class MeasurementPanel extends JPanel {
    private JTabbedPane tabbedPane;
    private ShirtMeasurementPanel shirtPanel;
    private PantMeasurementPanel pantPanel;

    public MeasurementPanel() {
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        tabbedPane = new JTabbedPane();
        shirtPanel = new ShirtMeasurementPanel();
        pantPanel = new PantMeasurementPanel();
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        tabbedPane.addTab("Shirt Measurements", shirtPanel);
        tabbedPane.addTab("Pant Measurements", pantPanel);
        add(tabbedPane, BorderLayout.CENTER);
    }

    // Inner class for Shirt Measurements
    private class ShirtMeasurementPanel extends JPanel {
        private JComboBox<String> shirtTypeCombo;
        private JTextField priceField;
        private JTextField heightField, chestField, front1Field, front2Field;
        private JTextField shoulderField, bellyField, sleeveField, collarField, cupField;
        private JTextArea otherArea;
        private JButton saveShirtButton;

        public ShirtMeasurementPanel() {
            initializeShirtComponents();
            setupShirtLayout();
            setupShirtEventListeners();
        }

        private void initializeShirtComponents() {
        	String[] shirtType = services.OrderService.getShirtTypes();
            shirtTypeCombo = new JComboBox<>(shirtType);
            priceField = new JTextField(10);
            heightField = new JTextField(10);
            chestField = new JTextField(10);
            front1Field = new JTextField(10);
            front2Field = new JTextField(10);
            shoulderField = new JTextField(10);
            bellyField = new JTextField(10);
            sleeveField = new JTextField(10);
            collarField = new JTextField(10);
            cupField = new JTextField(10);
            otherArea = new JTextArea(3, 20);
            saveShirtButton = new JButton("Save Shirt Measurement");
            saveShirtButton.setForeground(Color.black);
        }

        private void setupShirtLayout() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            // Shirt Type and Price
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(new JLabel("Shirt Type:"), gbc);
            gbc.gridx = 1;
            add(shirtTypeCombo, gbc);
            gbc.gridx = 2;
            add(new JLabel("Price:"), gbc);
            gbc.gridx = 3;
            add(priceField, gbc);

            // Measurements - Row 1
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(new JLabel("Height:"), gbc);
            gbc.gridx = 1;
            add(heightField, gbc);
            gbc.gridx = 2;
            add(new JLabel("Chest:"), gbc);
            gbc.gridx = 3;
            add(chestField, gbc);

            // Measurements - Row 2
            gbc.gridx = 0;
            gbc.gridy = 2;
            add(new JLabel("Front1:"), gbc);
            gbc.gridx = 1;
            add(front1Field, gbc);
            gbc.gridx = 2;
            add(new JLabel("Front2:"), gbc);
            gbc.gridx = 3;
            add(front2Field, gbc);

            // Measurements - Row 3
            gbc.gridx = 0;
            gbc.gridy = 3;
            add(new JLabel("Shoulder:"), gbc);
            gbc.gridx = 1;
            add(shoulderField, gbc);
            gbc.gridx = 2;
            add(new JLabel("Belly:"), gbc);
            gbc.gridx = 3;
            add(bellyField, gbc);

            // Measurements - Row 4
            gbc.gridx = 0;
            gbc.gridy = 4;
            add(new JLabel("Sleeve:"), gbc);
            gbc.gridx = 1;
            add(sleeveField, gbc);
            gbc.gridx = 2;
            add(new JLabel("Collar:"), gbc);
            gbc.gridx = 3;
            add(collarField, gbc);

            // Cup
            gbc.gridx = 0;
            gbc.gridy = 5;
            add(new JLabel("Cup:"), gbc);
            gbc.gridx = 1;
            add(cupField, gbc);

            // Other notes
            gbc.gridx = 0;
            gbc.gridy = 6;
            add(new JLabel("Other:"), gbc);
            gbc.gridx = 1;
            gbc.gridwidth = 3;
            add(new JScrollPane(otherArea), gbc);

            // Save button
            gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.gridwidth = 4;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(saveShirtButton, gbc);
        }

        private void setupShirtEventListeners() {
            saveShirtButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveShirtMeasurement();
                }
            });
        }

        private void saveShirtMeasurement() {
            try {
                Shirt shirt = new Shirt();
                shirt.setShirtType((String) shirtTypeCombo.getSelectedItem());

                if (!priceField.getText().isEmpty()) {
                    shirt.setPrice(new BigDecimal(priceField.getText()));
                }
                if (!heightField.getText().isEmpty()) {
                    shirt.setHeight(new BigDecimal(heightField.getText()));
                }
                if (!chestField.getText().isEmpty()) {
                    shirt.setChest(new BigDecimal(chestField.getText()));
                }
                if (!front1Field.getText().isEmpty()) {
                    shirt.setFront1(new BigDecimal(front1Field.getText()));
                }
                if (!front2Field.getText().isEmpty()) {
                    shirt.setFront2(new BigDecimal(front2Field.getText()));
                }
                if (!shoulderField.getText().isEmpty()) {
                    shirt.setShoulder(new BigDecimal(shoulderField.getText()));
                }
                if (!bellyField.getText().isEmpty()) {
                    shirt.setBelly(new BigDecimal(bellyField.getText()));
                }
                if (!sleeveField.getText().isEmpty()) {
                    shirt.setSleeve(new BigDecimal(sleeveField.getText()));
                }
                if (!collarField.getText().isEmpty()) {
                    shirt.setCollar(new BigDecimal(collarField.getText()));
                }
                if (!cupField.getText().isEmpty()) {
                    shirt.setCup(new BigDecimal(cupField.getText()));
                }
                shirt.setOther(otherArea.getText());

                JOptionPane.showMessageDialog(this, "Shirt measurement saved!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saving measurement: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Inner class for Pant Measurements
    private class PantMeasurementPanel extends JPanel {
        private JComboBox<String> pantTypeCombo;
        private JTextField priceField;
        private JTextField heightField, waistField, seatField, thighField;
        private JTextField kneeField, bottomField, hangField;
        private JTextArea otherArea;
        private JButton savePantButton;

        public PantMeasurementPanel() {
            initializePantComponents();
            setupPantLayout();
            setupPantEventListeners();
        }

        private void initializePantComponents() {
        	String[] pantType = services.OrderService.getPantTypes();
            pantTypeCombo = new JComboBox<>(pantType);
            priceField = new JTextField(10);
            heightField = new JTextField(10);
            waistField = new JTextField(10);
            seatField = new JTextField(10);
            thighField = new JTextField(10);
            kneeField = new JTextField(10);
            bottomField = new JTextField(10);
            hangField = new JTextField(10);
            otherArea = new JTextArea(3, 20);
            savePantButton = new JButton("Save Pant Measurement");
            savePantButton.setForeground(Color.black);
        }

        private void setupPantLayout() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            // Pant Type and Price
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(new JLabel("Pant Type:"), gbc);
            gbc.gridx = 1;
            add(pantTypeCombo, gbc);
            gbc.gridx = 2;
            add(new JLabel("Price:"), gbc);
            gbc.gridx = 3;
            add(priceField, gbc);

            // Measurements - Row 1
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(new JLabel("Height:"), gbc);
            gbc.gridx = 1;
            add(heightField, gbc);
            gbc.gridx = 2;
            add(new JLabel("Waist:"), gbc);
            gbc.gridx = 3;
            add(waistField, gbc);

            // Measurements - Row 2
            gbc.gridx = 0;
            gbc.gridy = 2;
            add(new JLabel("Seat:"), gbc);
            gbc.gridx = 1;
            add(seatField, gbc);
            gbc.gridx = 2;
            add(new JLabel("Thigh:"), gbc);
            gbc.gridx = 3;
            add(thighField, gbc);

            // Measurements - Row 3
            gbc.gridx = 0;
            gbc.gridy = 3;
            add(new JLabel("Knee:"), gbc);
            gbc.gridx = 1;
            add(kneeField, gbc);
            gbc.gridx = 2;
            add(new JLabel("Bottom:"), gbc);
            gbc.gridx = 3;
            add(bottomField, gbc);

            // Hang
            gbc.gridx = 0;
            gbc.gridy = 4;
            add(new JLabel("Hang:"), gbc);
            gbc.gridx = 1;
            add(hangField, gbc);

            // Other notes
            gbc.gridx = 0;
            gbc.gridy = 5;
            add(new JLabel("Other:"), gbc);
            gbc.gridx = 1;
            gbc.gridwidth = 3;
            add(new JScrollPane(otherArea), gbc);

            // Save button
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.gridwidth = 4;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(savePantButton, gbc);
        }

        private void setupPantEventListeners() {
            savePantButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    savePantMeasurement();
                }
            });
        }

        private void savePantMeasurement() {
            try {
                Pant pant = new Pant();
                pant.setPantType((String) pantTypeCombo.getSelectedItem());

                if (!priceField.getText().isEmpty()) {
                    pant.setPrice(new BigDecimal(priceField.getText()));
                }
                if (!heightField.getText().isEmpty()) {
                    pant.setHeight(new BigDecimal(heightField.getText()));
                }
                if (!waistField.getText().isEmpty()) {
                    pant.setWaist(new BigDecimal(waistField.getText()));
                }
                if (!seatField.getText().isEmpty()) {
                    pant.setSeat(new BigDecimal(seatField.getText()));
                }
                if (!thighField.getText().isEmpty()) {
                    pant.setThigh(new BigDecimal(thighField.getText()));
                }
                if (!kneeField.getText().isEmpty()) {
                    pant.setKnee(new BigDecimal(kneeField.getText()));
                }
                if (!bottomField.getText().isEmpty()) {
                    pant.setBottom(new BigDecimal(bottomField.getText()));
                }
                if (!hangField.getText().isEmpty()) {
                    pant.setHang(new BigDecimal(hangField.getText()));
                }
                pant.setOther(otherArea.getText());

                JOptionPane.showMessageDialog(this, "Pant measurement saved!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saving measurement: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
