package com.mycompany.vendingmachine.controller;

import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.service.Change;
import com.mycompany.vendingmachine.service.InsufficientFundsException;
import com.mycompany.vendingmachine.service.NoItemInventoryException;
import com.mycompany.vendingmachine.ui.VendingMachineView;
import com.mycompany.vendingmachine.service.VendingMachineServiceLayer;

/**
 *
 * @author n0252282
 */
public class VendingMachineController {

    VendingMachineView view;

    private VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
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
        } catch (VendingMachinePersistenceException | InsufficientFundsException | NoItemInventoryException | NumberFormatException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {

        try {
            view.displayAllItems(service.getAllItemsFiltered());
            //passing in current money so that it can be displayed with the menu
            return view.printMenuAndGetSelection(service.getCurrentMoney());
        } catch (NumberFormatException | VendingMachinePersistenceException e) {
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

    private void purchase() throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException {
        try {
            Change change = service.purchaseItem(view.getItemChoice());
            view.displayChange(change);
            view.displayPurchaseSuccess();
        } catch (VendingMachinePersistenceException | InsufficientFundsException | NoItemInventoryException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void giveChange() throws VendingMachinePersistenceException {
        try {
            view.displayChange(service.cancelGiveChange());
        } catch (VendingMachinePersistenceException e) {
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
