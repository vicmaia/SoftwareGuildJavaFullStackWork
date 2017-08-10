package com.mycompany.flooringmastery.controller;

import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.flooringmastery.dto.Order;
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
                        removeAnOrder();
                        break;
                    case 5:
                        saveProgess();
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
            //display all orders for a date passing in order date from user
            view.displayAllOrders(service.getOrdersByDate(view.getOrderDate()));
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void addAnOrder() throws FlooringMasteryPersistenceException {
        try {
            //create a new order, passing in order date and order details from user
            service.createOrder(view.getOrderDate(), view.getNewOrderDetails());
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void editAnOrder() throws FlooringMasteryPersistenceException {
        try {
            //get order date to edit from user
            LocalDate orderToEditDate = view.getOrderDate();
            
            //display all orders based on date from user
            view.displayAllOrders(service.getOrdersByDate(orderToEditDate));

            //ask user to choose an order to edit, store it
            Integer editChoice = view.getEditChoice();
            
            //pass order edit date and orderid chosen by user to retrieve that order info
            Order orderToEdit = service.retrieveOrder(orderToEditDate, editChoice);
            
            //make a copy of the original order in the object editedorder, get edit information from user
            Order editedOrder = view.getEditedOrderDetails(orderToEdit);

            //pass out the original order object and the edited order to the editOrder method
            service.editOrder(orderToEdit, editedOrder);

        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void removeAnOrder() throws FlooringMasteryPersistenceException {
        try {
            LocalDate orderToEditDate = view.getOrderDate();
            view.displayAllOrders(service.getOrdersByDate(orderToEditDate));

            Integer editChoice = view.getEditChoice();
            
            service.removeOrder(orderToEditDate, editChoice);
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

    private void saveProgess() throws FlooringMasteryPersistenceException{
        service.saveCurrentWork();
    }

}
