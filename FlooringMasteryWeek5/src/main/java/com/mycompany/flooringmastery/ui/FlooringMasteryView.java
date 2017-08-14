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
import java.time.format.DateTimeParseException;
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

    public void displayAllProducts(List<Product> productList) {
        io.print("Products available:");
        for (Product currentProduct : productList) {
            io.print("Product: " + currentProduct.getProductType()
                    + " Cost per square foot: " + currentProduct.getCostPerSquareFoot()
                    + " Labor cost per square foot: " + currentProduct.getLaborCostPerSquareFoot());
        }
    }

    public void displayAllTaxes(List<Tax> taxList) {
        io.print("States available for order addresses:");
        for (Tax currentTax : taxList) {
            io.print("Tax state: " + currentTax.getState()
                    + " Tax rate: " + currentTax.getTaxRate());
        }
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

        LocalDate orderToEditDate = orderToEdit.getOrderDate();
        String orderToEditDateString = orderToEditDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        //Loop date prompt until properly formatted date entered
        boolean success = false;
        while (!success) {
            success = true;
            String editedDate = io.readString("Current order date is " + orderToEditDateString + ". Please update, or hit enter to keep: ");
            if (editedDate.compareTo("") == 0) {
                editedOrder.setOrderDate(orderToEdit.getOrderDate());
            } else {
                try {
                    editedOrder.setOrderDate(LocalDate.parse(editedDate, DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                } catch (DateTimeParseException e) {
                    success = false;
                    System.out.println("Please enter a valid date in the format MM/dd/yyyy.");
                }
            }
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

        //Loop date prompt until properly formatted BigD entered
        success = false;
        while (!success) {
            success = true;
            String editedArea = io.readString("Current area in square feet is " + orderToEdit.getArea() + ". Please update, or hit enter to keep: ");
            if (editedArea.compareTo("") == 0) {
                editedOrder.setArea(orderToEdit.getArea());
            } else {
                try {
                    editedOrder.setArea(new BigDecimal(editedArea));
                } catch (NumberFormatException e) {
                    success = false;
                    System.out.println("Please enter a decimal number.");
                }
            }
        }
        return editedOrder;
    }

    public String getObjectChoice() {
        return io.readString("Please enter the Item ID.");
    }

    public void displayChangesSavedBanner() {
        io.print("--Changes Saved!--");
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

    public Integer getRemoveChoice() {
        return io.readInt("Please enter the record you would like to remove: ");
    }

    public String getPersistDataChoice() {
        return io.readString("Would you like to save this record or discard? (S, D)");
    }

}
