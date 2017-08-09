package com.mycompany.flooringmastery.controller;

import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.flooringmastery.ui.FlooringMasteryView;
import com.mycompany.flooringmastery.service.FlooringMasteryServiceLayer;
import java.time.LocalDate;

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
                        getOrdersByDate();
                        break;
                    case 2:
                        addAnOrder();
                        break;
                    case 3:
                        editAnOrder();
                        break;
                    case 4:
                        //;
                        break;
                    case 5:
                        //;
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        try {
            return view.printMenuAndGetSelection();
        } catch (NumberFormatException e) {
            view.displayErrorMessage(e.getMessage());
        }
        return 0;
    }

    private void getOrdersByDate() throws FlooringMasteryPersistenceException {
        try {
            view.displayAllOrders(service.getOrdersByDate(view.getOrderDate()));
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void addAnOrder() throws FlooringMasteryPersistenceException {
        try {
            service.createOrder(view.getOrderDate(), view.getNewOrderDetails());
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void editAnOrder() throws FlooringMasteryPersistenceException {
        try {
            LocalDate orderToEditDate = view.getOrderDate();
            view.displayAllOrders(service.getOrdersByDate(orderToEditDate));
            
            Integer editChoice = view.getEditChoice();
            service.retrieveOrder(orderToEditDate, editChoice);
            
            view.getEditedOrderDetails(Order orderToEdit)
        } catch (FlooringMasteryPersistenceException e) {
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
