/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressBook.controller;

import com.mycompany.addressBook.dao.AddressBookDaoFileImpl;
import com.mycompany.addressBook.dto.AddressBookException;
import com.mycompany.addressBook.dto.Address;
import com.mycompany.addressBook.ui.AddressBookView;
import com.mycompany.addressBook.ui.UserIO;
import com.mycompany.addressBook.ui.UserIOConsoleImpl;
import java.util.List;
import com.mycompany.addressBook.dao.AddressBookDao;

/**
 *
 * @author n0252282
 */
public class AddressBookController {

    AddressBookView view;
    AddressBookDao dao;
//    private UserIO io = new UserIOConsoleImpl();

    public AddressBookController(AddressBookView view, AddressBookDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addDVD();
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        editDVD();
                        break;
                    case 4:
                        listDVDs();
                        break;
                    case 5:
                        viewDVD();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (AddressBookException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addDVD() throws AddressBookException {
        view.displayAddDVDBanner();
        Address newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayAddSuccessBanner();
    }

    private void removeDVD() throws AddressBookException {
        view.displayRemoveDVDBanner();
        String DVDtitle = view.getDVDTitleChoice();
        dao.removeDVD(DVDtitle);
        view.displayRemoveSuccessBanner();
    }

    private void editDVD() throws AddressBookException {
        view.displayEditDVDBanner();
        //Get Record
        String DVDtitle = view.getDVDTitleChoice();
        //Return Address object
        Address dvd = dao.getDVD(DVDtitle);
        Address dvdEdit = new Address(dvd.getTitle(), dvd.getReleaseDate(), dvd.getRating(), dvd.getDirector(), dvd.getStudio(), dvd.getComment());
        //edit logic
        boolean keepGoing = true;
        int editFieldChoice = 0;
        try {
            while (keepGoing) {
                //Display edit menu;
                //store menu selection
                editFieldChoice = view.displayEditMenuDVD(dvdEdit);

                switch (editFieldChoice) {
                    case 1:
                        dvdEdit.setTitle(view.editDVD(editFieldChoice));
                        break;
                    case 2:
                        dvdEdit.setReleaseDate(view.editDVD(editFieldChoice));

                        break;
                    case 3:
                        dvdEdit.setRating(view.editDVD(editFieldChoice));
                        break;
                    case 4:
                        dvdEdit.setDirector(view.editDVD(editFieldChoice));
                        break;
                    case 5:
                        dvdEdit.setStudio(view.editDVD(editFieldChoice));
                        break;
                    case 6:
                        dvdEdit.setComment(view.editDVD(editFieldChoice));
                        break;
                    case 7:
                        replaceDVD(dvd, dvdEdit);
                        view.displayChangesSaved();
                        break;
                    case 8:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            view.displayReturningToMainMenu();
        } catch (AddressBookException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    private void listDVDs() throws AddressBookException {
        view.displayDisplayAllBanner();
        List<Address> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }

    private void viewDVD() throws AddressBookException {
        view.displayDVDBanner();
        String DVDtitle = view.getDVDTitleChoice();
        Address dvd = dao.getDVD(DVDtitle);
        view.displayDVD(dvd);
    }

    private void replaceDVD(Address oldDVD, Address newDVD) throws AddressBookException {
        //remove old record
        dao.removeDVD(oldDVD.getTitle());
        //add new record
        dao.addDVD(newDVD.getTitle(), newDVD);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
