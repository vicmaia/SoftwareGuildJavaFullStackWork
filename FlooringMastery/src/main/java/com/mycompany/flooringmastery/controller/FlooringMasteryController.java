package com.mycompany.flooringmastery.controller;

import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.flooringmastery.service.NoItemInventoryException;
import com.mycompany.flooringmastery.ui.FlooringMasteryView;
import com.mycompany.flooringmastery.service.FlooringMasteryServiceLayer;

/**
 *
 * @author n0252282
 */
public class FlooringMasteryController {

    FlooringMasteryView view;

    private FlooringMasteryServiceLayer service;

    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceLayer service) {
        this.view = view;
        this.service = service;

    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addMoney();
                        break;
                    case 2:
                        purchase();
                        break;
                    case 3:
                        giveChange();
                        break;
                    case 4:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (FlooringMasteryPersistenceException | InsufficientFundsException | NoItemInventoryException | NumberFormatException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {

        try {
            view.displayAllItems(service.getAllItemsFiltered());
            //passing in current money so that it can be displayed with the menu
            return view.printMenuAndGetSelection(service.getCurrentMoney());
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        return 0;
    }

    private void addMoney() throws NumberFormatException {
        try {
            service.setCurrentMoney(view.getMoneyEntry());
            view.displayCurrentMoney(service.getCurrentMoney());
        } catch (NumberFormatException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void purchase() throws FlooringMasteryPersistenceException, InsufficientFundsException, NoItemInventoryException {
        try {
            Change change = service.purchaseItem(view.getItemChoice());
            view.displayPurchaseSuccess();
            view.displayChange(change);
        } catch (FlooringMasteryPersistenceException | InsufficientFundsException | NoItemInventoryException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void giveChange() throws FlooringMasteryPersistenceException, InsufficientFundsException {
        try {
            view.displayChange(service.cancelGiveChange());
        } catch (FlooringMasteryPersistenceException | InsufficientFundsException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
