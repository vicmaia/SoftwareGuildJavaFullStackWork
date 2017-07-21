/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.controller;

import com.mycompany.dvdlibrary.dao.DVDLibraryDao;
import com.mycompany.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.mycompany.dvdlibrary.dto.DVDLibraryException;
import com.mycompany.dvdlibrary.dto.DVD;
import com.mycompany.dvdlibrary.ui.DVDLibraryView;
import com.mycompany.dvdlibrary.ui.UserIO;
import com.mycompany.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author n0252282
 */
public class DVDLibraryController {

    DVDLibraryView view;
    DVDLibraryDao dao;
//    private UserIO io = new UserIOConsoleImpl();

    public DVDLibraryController(DVDLibraryView view, DVDLibraryDao dao) {
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
        } catch (DVDLibraryException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addDVD() throws DVDLibraryException {
        view.displayAddDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayAddSuccessBanner();
    }

    private void removeDVD() throws DVDLibraryException {
        view.displayRemoveDVDBanner();
        String DVDtitle = view.getDVDTitleChoice();
        dao.removeDVD(DVDtitle);
        view.displayRemoveSuccessBanner();
    }

    private void editDVD() throws DVDLibraryException {
        view.displayEditDVDBanner();
        //Get Record
        String DVDtitle = view.getDVDTitleChoice();
        //Return DVD object
        DVD dvd = dao.getDVD(DVDtitle);

        //edit logic
        boolean keepGoing = true;
        int editFieldChoice = 0;
        try {
            while (keepGoing) {
                //Display edit menu;
                //store menu selection
                editFieldChoice = view.displayEditMenuDVD(dvd);

                switch (editFieldChoice) {
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
        } catch (DVDLibraryException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    private void listDVDs() throws DVDLibraryException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }

    private void viewDVD() throws DVDLibraryException {
        view.displayDVDBanner();
        String DVDtitle = view.getDVDTitleChoice();
        DVD dvd = dao.getDVD(DVDtitle);
        view.displayDVD(dvd);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
