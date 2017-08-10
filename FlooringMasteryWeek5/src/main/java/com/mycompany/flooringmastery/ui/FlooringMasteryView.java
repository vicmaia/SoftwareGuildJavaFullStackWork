/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.ui;

import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        return io.readLocalDate("Please enter the order date (MM/dd/yyyy): ");
    }

    public void displayAllOrders(List<Order> orderList) {
        io.print("Current Orders: \n");
        for (Order currentOrder : orderList) {
            io.print("Order # " + currentOrder.getOrderNumber() + " Date: "
                    + currentOrder.getOrderDate() + " Name: "
                    + currentOrder.getCustomerName() + " "
                    + currentOrder.getTaxRate() + " "
                    + currentOrder.getProduct() + " Area: "
                    + currentOrder.getArea() + " Material Cost: "
                    + currentOrder.getMaterialCost() + " Labor Cost: "
                    + currentOrder.getLaborCost() + " Total Tax: "
                    + currentOrder.getTaxTotal() + " Total Cost: "
                    + currentOrder.getTotalCost());
        }
        io.print("");
    }

    public Order getNewOrderDetails() {
        Order newOrder = new Order();
        Tax taxRate = new Tax();
        Product newProduct = new Product();

        newOrder.setCustomerName(io.readString("Please enter customer name: "));

        taxRate.setState(io.readString("Please enter customer state: "));
        newOrder.setTaxRate(taxRate);

        newProduct.setProductType(io.readString("Please enter desired product: "));
        newOrder.setProduct(newProduct);

        newOrder.setArea(io.readBigDecimal("Please enter area in number of square feet: "));

        return newOrder;
    }

    public Order getEditedOrderDetails(Order orderToEdit) {
        Order editedOrder = new Order(orderToEdit.getOrderNumber(), orderToEdit.getOrderDate(), orderToEdit.getCustomerName(), orderToEdit.getTaxRate(), orderToEdit.getProduct(), orderToEdit.getArea());
        Tax taxRate = new Tax();
        Product newProduct = new Product();

        String editedDate = io.readString("Current order date is " + orderToEdit.getOrderDate() + ". Please update, or hit enter to keep: ");
        if (editedDate.compareTo("") == 0) {
            editedOrder.setOrderDate(orderToEdit.getOrderDate());
        } else {
            //to do catch bad parse
            editedOrder.setOrderDate(LocalDate.parse(editedDate, DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        }

        editedOrder.setCustomerName(io.readString("Current customer name is " + orderToEdit.getCustomerName() + ". Please update, or hit enter to keep: "));
        if (editedOrder.getCustomerName().isEmpty()) {
            editedOrder.setCustomerName(orderToEdit.getCustomerName());
        }

        taxRate.setState((io.readString("Current customer tax state is " + orderToEdit.getTaxRate().getState() + ". Please update, or hit enter to keep: ")));
        if (taxRate.getState().isEmpty()) {
            editedOrder.setTaxRate(orderToEdit.getTaxRate());
        } else {
            editedOrder.setTaxRate(taxRate);
        }

        newProduct.setProductType(io.readString("Current product is " + orderToEdit.getProduct().getProductType() + ". Please update, or hit enter to keep: "));
        if (newProduct.getProductType().isEmpty()) {
            editedOrder.setProduct(orderToEdit.getProduct());
        } else {
            editedOrder.setProduct(newProduct);
        }

        String editedArea = io.readString("Current area in square feet is " + orderToEdit.getArea() + ". Please update, or hit enter to keep: ");
        if (editedArea.compareTo("") == 0) {
            editedOrder.setArea(orderToEdit.getArea());
        } else {
            editedOrder.setArea(new BigDecimal(editedArea));
        }

        return editedOrder;
    }

    public String getObjectChoice() {
        return io.readString("Please enter the Item ID.");
    }

    public void displayOrderCreatedBanner() {
        io.print("--Order created and saved--");
    }

    public void displayOrderAbortBanner() {
        io.print("--Order not created--");
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

    public Integer getEditChoice() {
        return io.readInt("Please enter the record you would like to edit: ");
    }

    public String getPersistDataChoice() {
        return io.readString("Would you like to save this record or discard? (S, D)");
    }

}
