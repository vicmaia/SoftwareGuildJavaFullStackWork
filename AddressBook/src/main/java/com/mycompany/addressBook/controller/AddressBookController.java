/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressBook.controller;

import com.mycompany.addressBook.dao.AddressBookDaoFileImpl;
import com.mycompany.addressBook.dao.AddressBookException;
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
                        addAddress();
                        break;
                    case 2:
                        removeAddress();
                        break;
                    case 3:
                        viewAddress(); //find address
                        break;
                    case 4:
                        System.out.println(listAddressCount());
                        break;
                    case 5:
                        listAddresses();//list all addresses
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

    private int listAddressCount() throws AddressBookException {
        return dao.getAllAddresses().size();
    }

    private void addAddress() throws AddressBookException {
        view.displayAddAddressBanner();
        dao.getAllAddresses();
        Address newAddress = view.getNewAddressInfo();
        dao.addAddress(newAddress.getLastName(), newAddress);
        view.displayAddSuccessBanner();
    }

    private void removeAddress() throws AddressBookException {
        view.displayRemoveAddressBanner();
        String Addresstitle = view.getAddressChoice();
        dao.removeAddress(Addresstitle);
        view.displayRemoveSuccessBanner();
    }

//    private void editAddress() throws AddressBookException {
//        view.displayEditAddressBanner();
//        //Get Record
//        String Addresstitle = view.getAddressTitleChoice();
//        //Return Address object
//        Address address = dao.getAddress(Addresstitle);
//        Address addressEdit = new Address(address.getLastName(), address.getReleaseDate(), address.getRating(), address.getDirector(), address.getStudio(), address.getComment());
//        //edit logic
//        boolean keepGoing = true;
//        int editFieldChoice = 0;
//        try {
//            while (keepGoing) {
//                //Display edit menu;
//                //store menu selection
//                editFieldChoice = view.displayEditMenuAddress(addressEdit);
//
//                switch (editFieldChoice) {
//                    case 1:
//                        addressEdit.setTitle(view.editAddress(editFieldChoice));
//                        break;
//                    case 2:
//                        addressEdit.setReleaseDate(view.editAddress(editFieldChoice));
//
//                        break;
//                    case 3:
//                        addressEdit.setRating(view.editAddress(editFieldChoice));
//                        break;
//                    case 4:
//                        addressEdit.setDirector(view.editAddress(editFieldChoice));
//                        break;
//                    case 5:
//                        addressEdit.setStudio(view.editAddress(editFieldChoice));
//                        break;
//                    case 6:
//                        addressEdit.setComment(view.editAddress(editFieldChoice));
//                        break;
//                    case 7:
//                        replaceAddress(address, addressEdit);
//                        view.displayChangesSaved();
//                        break;
//                    case 8:
//                        keepGoing = false;
//                        break;
//                    default:
//                        unknownCommand();
//                }
//            }
//            view.displayReturningToMainMenu();
//        } catch (AddressBookException e) {
//            view.displayErrorMessage(e.getMessage());
//        }
//
//    }
    private void listAddresses() throws AddressBookException {
        view.displayDisplayAllBanner();
        List<Address> addressList = dao.getAllAddresses();
        view.displayAddressList(addressList);
    }

    private void viewAddress() throws AddressBookException {
        view.displayAddressBanner();
        String Addresstitle = view.getAddressChoice();
        Address address = dao.getAddress(Addresstitle);
        view.displayAddress(address);
    }

//    private void replaceAddress(Address oldAddress, Address newAddress) throws AddressBookException {
//        //remove old record
//        dao.removeAddress(oldAddress.getLastName());
//        //add new record
//        dao.addAddress(newAddress.getLastName(), newAddress);
//    }
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
