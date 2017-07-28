/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.ui;

import com.mycompany.vendingmachine.dto.Item;
import com.mycompany.vendingmachine.service.Change;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author n0252282
 */
public class VendingMachineView {

    UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Welcome to Vend-a-lot 2000:");
        io.print("1. Add money");//+currentBalance
        io.print("2. Purchase an item");
        io.print("3. Get Change");
        io.print("4. Exit");

        return io.readInt("Please select from the above choices: ", 1, 4);
    }

    public BigDecimal getMoneyEntry(){
        return io.readBigDecimal("Please insert your money: ");
    }
    
    public void displayPurchaseSuccess() {
        io.readString(
                "Purchase successful!  Please hit enter to continue");//add item name
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

    public int getItemChoice() {
        return io.readInt("Please enter the Item ID.");
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
    }

    public void displayChange(Change change) {
        io.print("Here is your change:");
        io.print(change.getNumQuarters() + " Quarters");
        io.print(change.getNumDimes() + " Dimes");
        io.print(change.getNumNickles() + " Nickels");
        io.print(change.getNumPennies() + " Pennies");
    }

    public void displayCurrentMoney(BigDecimal currentMoney) {
        io.print("You have $" + currentMoney);
    }
}
