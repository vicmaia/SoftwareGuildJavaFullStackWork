package com.mycompany.dvdlibrary;

import com.mycompany.dvdlibrary.controller.DVDLibraryController;
import com.mycompany.dvdlibrary.dao.DVDLibraryLambdaDao;
import com.mycompany.dvdlibrary.dao.DVDLibraryLambdaDaoFileImpl;
import com.mycompany.dvdlibrary.ui.DVDLibraryView;
import com.mycompany.dvdlibrary.ui.UserIO;
import com.mycompany.dvdlibrary.ui.UserIOConsoleImpl;

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
        //Select an IO Implementation
        UserIO myIo = new UserIOConsoleImpl();
        //Stick IO in your view
        DVDLibraryView myView = new DVDLibraryView(myIo);

        //Select a DAO implementation
        DVDLibraryLambdaDao myDao = new DVDLibraryLambdaDaoFileImpl();
        //put controller in a variable for readability
        DVDLibraryController controller;

        //Stick view and dao in controller
        controller = new DVDLibraryController(myView, myDao);

        //kick off controller
        controller.run();
    }
}
