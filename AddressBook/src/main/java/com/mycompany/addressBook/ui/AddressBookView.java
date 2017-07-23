/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressBook.ui;

import com.mycompany.addressBook.dto.Address;
import java.util.List;
import java.util.Set;

/**
 *
 * @author n0252282
 */
public class AddressBookView {

    UserIO io;

    public AddressBookView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add Address");
        io.print("2. Delete Address");
        io.print("3. Find Address");
        io.print("4. List Addres Count");
        io.print("5. List All Addresses");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices: ", 1, 6);
    }

    public Address getNewAddressInfo() {
        String lastName = io.readString("Please enter Last name");
        String firstName = io.readString("Please enter First name");
        String streetAddress = io.readString("Please enter street address");
        String city = io.readString("Please enter city");
        String state = io.readString("Please enter state");
        String zip = io.readString("Please enter zip");

        Address currentAddress = new Address(lastName);
        currentAddress.setFirstName(firstName);
        currentAddress.setStreetAddress(streetAddress);
        currentAddress.setCity(city);
        currentAddress.setState(state);
        currentAddress.setZip(zip);

        return currentAddress;
    }

    public void displayAddAddressBanner() {
        io.print("=== Add Address ===");
    }

    public void displayAddSuccessBanner() {
        io.readString(
                "Address successfully created.  Please hit enter to continue");
    }

//    public void displayEditAddressBanner() {
//        io.print("=== Edit Address ===");
//    }
    public void displayAddressList(List<Address> addressList) {
        int counter = 0;
        for (Address currentAddress : addressList) {
            counter++;
            io.print("Address #" + counter);
            displayFormattedDetails(currentAddress);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Addresses ===");
    }

    public void displayAddressBanner() {
        io.print("=== Display Address ===");
    }

    public String getAddressChoice() {
        return io.readString("Please enter the Last Name.");
    }
//

    public void displayAddress(Address currentAddress) {
        if (currentAddress != null) {
            displayFormattedDetails(currentAddress);
        } else {
            io.print("No such Address.");
        }
        io.readString("Please hit enter to continue.");
    }

//    public int displayEditMenuAddress(Address currentAddress) {
//        if (currentAddress != null) {
//            displayFormattedDetails(currentAddress);
//            io.print("7.Save changes\n"
//                    + "8.Exit editor and return to main menu (unsaved changes will be lost).\n");
//        } else {
//            io.print("No such Address.");
//        }
//        return getEditFieldChoice();
//    }
//    public int getEditFieldChoice() {
//        return io.readInt("Please enter the number of the field you would like to edit: ", 1, 8);
//    }
//    public String editAddress(int menuChoice) {
//        switch (menuChoice) {
//            case 1:
//                return io.readString("Please enter a new title: ");
//            case 2:
//                return io.readString("Please enter a new release date: ");
//            case 3:
//                return io.readString("Please enter a new rating: ");
//            case 4:
//                return io.readString("Please enter a new director: ");
//            case 5:
//                return io.readString("Please enter a new studio: ");
//            case 6:
//                return io.readString("Please enter new notes: ");
//            default:
//                throw new AssertionError();
//        }
//    }
    public void displayReturningToMainMenu() {
        io.print("Returning to main menu.");
    }

    public void displayRemoveAddressBanner() {
        io.print("=== Remove Address ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("Address successfully removed. Please hit enter to continue.");
    }
//

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

    public void displayChangesSaved() {
        io.print("=== Changes saved ===");
    }

    public void displayFormattedDetails(Address currentAddress) {
        io.print("1.Last Name: " + currentAddress.getLastName() + "\n"
                + "2.First Name: " + currentAddress.getFirstName() + "\n"
                + "3.Street Address: " + currentAddress.getStreetAddress() + "\n"
                + "4.City: " + currentAddress.getCity() + "\n"
                + "5.State: " + currentAddress.getState() + "\n"
                + "6.Zip: " + currentAddress.getZip());
    }
}
