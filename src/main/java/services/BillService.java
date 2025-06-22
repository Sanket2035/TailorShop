
package services;

import models.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BillService {
    
//	public static String generateBill(OrderView orderView) {
//        if (orderView == null || orderView.getOrder() == null) {
//            return "Order not found.";
//        }
//
//        Order order = orderView.getOrder();
//        StringBuilder bill = new StringBuilder();
//
//        // Header
//        bill.append("------------------------------------------------------------\n");
//        bill.append("                        न्यू पॉश टेलर्स                       \n");
//        bill.append("            ढवळपुरी , तालुका : पारनेर , जिल्हा : अ. नगर           \n");
//        bill.append("                    संपर्क : 7020305364                     \n");
//        bill.append("------------------------------------------------------------\n\n");
//
//        // Order basic details - top box format
//        bill.append(String.format("ग्राहकाचे नाव    : %-25s\n",
//                order.getCustomerName()));
//        bill.append(String.format("ग्राहकाचा संपर्क   : %-25s  ऑर्डर क्रमांक : %s\n",
//                order.getCustomerMobile(), order.getOrderID()));
//        bill.append(String.format("ऑर्डर दिल्याची तारीख : %s\n", formatDate(order.getOrderDate())));
//        bill.append(String.format("डेलिव्हरीची तारीख : %s\n", (order.getPickupDate() != null ? formatDate(order.getPickupDate()) : "N/A")));
//        bill.append("\n------------------------------------------------------------\n");
//        bill.append(String.format("%-30s %-10s %-10s\n", "तपशील", "नग", "किंमत"));
//        bill.append("------------------------------------------------------------\n");
//
//        BigDecimal subtotal = BigDecimal.ZERO;
//
//        // Shirts
//        for (Shirt shirt : orderView.getShirts()) {
//            bill.append(String.format("%-30s %-10d ₹%-10.2f\n",
//                    shirt.getShirtType(), 1, shirt.getPrice()));
//            subtotal = subtotal.add(shirt.getPrice());
//        }
//
//        // Pants
//        for (Pant pant : orderView.getPants()) {
//            bill.append(String.format("%-30s %-10d ₹%-10.2f\n",
//                    pant.getPantType(), 1, pant.getPrice()));
//            subtotal = subtotal.add(pant.getPrice());
//        }
//
//        bill.append("------------------------------------------------------------\n");
//        bill.append(String.format("Total Payable : ₹%.2f\n", order.getTotalPayment()));
//        bill.append("------------------------------------------------------------\n");
//
//        bill.append("\n============================================================\n");
//        bill.append("                       न्यू पॉश टेलर्स ला निवडल्याबद्दल धन्यवाद !           \n");
//        bill.append("                            प्रो. प्रा. अनिल शेळके             \n");
//        bill.append("                           संपर्क: 7020305364                     \n");
//        bill.append("============================================================\n");
//
//        return bill.toString();
//    }
//	
    public static String generateNewBill(OrderView orderView) {
        if (orderView == null || orderView.getOrder() == null) {
            return "Order not found.";
        }

        Order order = orderView.getOrder();
        StringBuilder bill = new StringBuilder();

        // Header
        bill.append("------------------------------------------------------------\n");
        bill.append("                     New Posh Tailors                      \n");
        bill.append("          Dhavalpuri, Tal: Parner, Dist: A. Nagar          \n");
        bill.append("                    Mobile: 7020305364                     \n");
        bill.append("------------------------------------------------------------\n\n");

        // Order basic details - top box format
        bill.append(String.format("Customer Name   : %-25s\n",
                order.getCustomerName()));
        bill.append(String.format("Customer Mobile : %-25s  Order No. : %s\n",
                order.getCustomerMobile(), order.getOrderID()));
        bill.append(String.format("Order Placed On : %s\n", formatDate(order.getOrderDate())));
        bill.append(String.format("Delivery Date : %s\n", (order.getPickupDate() != null ? formatDate(order.getPickupDate()) : "N/A")));
        bill.append("\n------------------------------------------------------------\n");
        bill.append(String.format("%-30s %-10s %-10s\n", "Details", "Quantity", "Price"));
        bill.append("------------------------------------------------------------\n");

        BigDecimal subtotal = BigDecimal.ZERO;

        // Shirts
        for (Shirt shirt : orderView.getShirts()) {
            bill.append(String.format("%-30s %-10d ₹%-10.2f\n",
                    shirt.getShirtType(), 1, shirt.getPrice()));
            subtotal = subtotal.add(shirt.getPrice());
        }

        // Pants
        for (Pant pant : orderView.getPants()) {
            bill.append(String.format("%-30s %-10d ₹%-10.2f\n",
                    pant.getPantType(), 1, pant.getPrice()));
            subtotal = subtotal.add(pant.getPrice());
        }

        bill.append("------------------------------------------------------------\n");
        bill.append(String.format("SubTotal : ₹%.2f\n", order.getTotalPayment()));
        bill.append(String.format("Advance Payment : ₹%.2f\n",order.getAdvancePayment()));
        bill.append(String.format("Total Payable : ₹%.2f\n", order.getRemainingPayment(order.getTotalPayment(),order.getAdvancePayment())));
        bill.append("------------------------------------------------------------\n");

        bill.append("\n============================================================\n");
        bill.append("                Thank you for choosing Posh Tailors!           \n");
        bill.append("                  For queries, contact: Anil Shelke            \n");
        bill.append("                         Mobile: 7020305364                     \n");
        bill.append("============================================================\n");

        return bill.toString();
    }
    
    private static String formatDate(java.time.LocalDateTime dateTime) {
        if (dateTime == null) return "N/A";
        return dateTime.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
