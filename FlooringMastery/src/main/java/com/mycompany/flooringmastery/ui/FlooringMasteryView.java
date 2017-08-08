/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.ui;

import com.mycompany.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author n0252282
 */
public class FlooringMasteryView {

    UserIO io;

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() throws NumberFormatException {
        io.print("Welcome to Flooring World!");
        io.print("1. Display all orders by date");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Save Current Work");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices: ", 1, 6);
    }

    public LocalDate getOrderDate() throws NumberFormatException {
        return io.readLocalDate("Please enter the order date: ");
    }

    public void displayAllOrders(List<Order> orderList) {
        io.print("Current Orders: \n");
        for (Order currentOrder : orderList) {
            io.print(currentOrder.getOrderNumber() + " "
                    + currentOrder.getCustomerName() + "  "
                    + currentOrder.getTaxRate() + "  "
                    + currentOrder.getProduct() + "  "
                    + currentOrder.getArea() + "  "
                    + currentOrder.getMaterialCost() + "  "
                    + currentOrder.getLaborCost() + "  "
                    + currentOrder.getTaxTotal() + "  "
                    + currentOrder.getTotalCost() + "  ");
        }
        io.print("");
    }

    public String getObjectChoice() {
        return io.readString("Please enter the Item ID.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
        io.readString("Press any key to continue.");
    }

}
