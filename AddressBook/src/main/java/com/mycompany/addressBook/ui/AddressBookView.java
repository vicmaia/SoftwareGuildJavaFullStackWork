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
        io.print("1. Add DVD");
        io.print("2. Remove DVD");
        io.print("3. Edit DVD");
        io.print("4. List DVDs");
        io.print("5. Display DVD by Title");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices: ", 1, 6);
    }

    public Address getNewDVDInfo() {
        String dvdTitle = io.readString("Please enter the DVD title");
        String releaseDate = io.readString("Please enter the release date");
        String rating = io.readString("Please enter the rating");
        String director = io.readString("Please enter the director");
        String studio = io.readString("Please enter the studio");
        String comment = io.readString("Please enter your rating or notes");

        Address currentDVD = new Address(dvdTitle);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setRating(rating);
        currentDVD.setDirector(director);
        currentDVD.setStudio(studio);
        currentDVD.setComment(comment);

        return currentDVD;
    }

    public void displayAddDVDBanner() {
        io.print("=== Add DVD ===");
    }

    public void displayAddSuccessBanner() {
        io.readString(
                "DVD successfully created.  Please hit enter to continue");
    }

    public void displayEditDVDBanner() {
        io.print("=== Edit DVD ===");
    }

    public void displayDVDList(List<Address> dvdList) {
        int counter = 0;
        for (Address currentDVD : dvdList) {
            counter++;
            io.print("DVD #" + counter);
            displayFormattedDetails(currentDVD);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayDVDBanner() {
        io.print("=== Display DVD ===");
    }

    public String getDVDTitleChoice() {
        return io.readString("Please enter the DVD Title.");
    }
//

    public void displayDVD(Address currentDVD) {
        if (currentDVD != null) {
            displayFormattedDetails(currentDVD);
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public int displayEditMenuDVD(Address currentDVD) {
        if (currentDVD != null) {
            displayFormattedDetails(currentDVD);
            io.print("7.Save changes\n"
                    + "8.Exit editor and return to main menu (unsaved changes will be lost).\n");
        } else {
            io.print("No such DVD.");
        }
        return getEditFieldChoice();
    }

    public int getEditFieldChoice() {
        return io.readInt("Please enter the number of the field you would like to edit: ", 1, 8);
    }

    public String editDVD(int menuChoice) {
        switch (menuChoice) {
            case 1:
                return io.readString("Please enter a new title: ");
            case 2:
                return io.readString("Please enter a new release date: ");
            case 3:
                return io.readString("Please enter a new rating: ");
            case 4:
                return io.readString("Please enter a new director: ");
            case 5:
                return io.readString("Please enter a new studio: ");
            case 6:
                return io.readString("Please enter new notes: ");
            default:
                throw new AssertionError();
        }
    }

    public void displayReturningToMainMenu() {
        io.print("Returning to main menu.");
    }

    public void displayRemoveDVDBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("DVD successfully removed. Please hit enter to continue.");
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

    public void displayFormattedDetails(Address currentDVD) {
        io.print("1.Title: " + currentDVD.getTitle() + "\n"
                + "2.Release Date: " + currentDVD.getReleaseDate() + "\n"
                + "3.MPAA Rating: " + currentDVD.getRating() + "\n"
                + "4.Director: " + currentDVD.getDirector() + "\n"
                + "5.Studio: " + currentDVD.getStudio() + "\n"
                + "6.Notes: " + currentDVD.getComment());
    }
}
