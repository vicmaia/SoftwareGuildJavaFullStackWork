/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.ui;

import com.mycompany.flooringmastery.dto.Item;
import com.mycompany.flooringmastery.service.Change;
import java.math.BigDecimal;
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

    public int printMenuAndGetSelection(BigDecimal currentMoney) throws NumberFormatException {
        io.print("Welcome to Vend-a-lot 2000:");
        io.print("Money inserted: " + currentMoney);
        io.print("1. Add money");//+currentBalance
        io.print("2. Purchase an item");
        io.print("3. Get Change");
        io.print("4. Exit");

        return io.readInt("Please select from the above choices: ", 1, 4);
    }

    public BigDecimal getMoneyEntry() {
        return io.readBigDecimal("Please insert your money: ");
    }

    public void displayPurchaseSuccess() {
        io.print("Purchase successful!");
    }

    public void displayAllItems(List<Item> itemList) {
        io.print("Current Inventory:\n");
        for (Item currentItem : itemList) {
            io.print(currentItem.getItemID() + " "
                    + currentItem.getItemName() + "  "
                    + currentItem.getItemPrice());
        }
        io.print("");
    }

    public String getItemChoice() {
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

    public void displayChange(Change change) {
        if (change.getNumDimes() + change.getNumQuarters() + change.getNumNickles() + change.getNumPennies() != 0) {
            io.print("Here is your change:");
            io.print(change.getNumQuarters() + " Quarters");
            io.print(change.getNumDimes() + " Dimes");
            io.print(change.getNumNickles() + " Nickels");
            io.print(change.getNumPennies() + " Pennies");
        } else {
            io.print("No change to return!");
        }
        io.readString("Press any key to continue.");
    }

    public void displayCurrentMoney(BigDecimal currentMoney) {
        io.print("You have $" + currentMoney);
    }
}
