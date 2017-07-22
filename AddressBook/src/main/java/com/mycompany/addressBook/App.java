package com.mycompany.addressBook;

import com.mycompany.addressBook.controller.AddressBookController;
import com.mycompany.addressBook.dao.AddressBookDaoFileImpl;
import com.mycompany.addressBook.ui.AddressBookView;
import com.mycompany.addressBook.ui.UserIO;
import com.mycompany.addressBook.ui.UserIOConsoleImpl;
import com.mycompany.addressBook.dao.AddressBookDao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author n0252282
 */
public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        AddressBookView myView = new AddressBookView(myIo);
        AddressBookDao myDao = new AddressBookDaoFileImpl();
        AddressBookController controller;
        controller = new AddressBookController(myView, myDao);
        controller.run();
    }
}
