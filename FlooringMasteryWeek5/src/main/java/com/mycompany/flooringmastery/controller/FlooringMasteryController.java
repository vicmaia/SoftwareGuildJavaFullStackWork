package com.mycompany.flooringmastery.controller;

import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.service.DataValidationException;
import com.mycompany.flooringmastery.ui.FlooringMasteryView;
import com.mycompany.flooringmastery.service.FlooringMasteryServiceLayer;
import com.mycompany.flooringmastery.service.ItemNotAvailableException;
import com.mycompany.flooringmastery.service.NoOrderFoundException;
import com.mycompany.flooringmastery.service.TaxException;
import java.time.LocalDate;

/**
 *
 * @author n0252282
 */
public class FlooringMasteryController {

    private FlooringMasteryView view;

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
        //get order date from user
        LocalDate orderDate = view.getOrderDate();
        try {
            //display products
            view.displayAllProducts(service.getAllProducts());
            //display tax states
            view.displayAllTaxes(service.retrieveTaxList());
            //get new order details from user
            Order newOrder = view.getNewOrderDetails();
            //ask the user if they want to keep the new order
            if (view.getPersistDataChoice().compareToIgnoreCase("s") == 0) {
                service.createOrder(orderDate, newOrder);
                service.saveCurrentWork();
                view.displayChangesSavedBanner();
            } else {
                view.displayOrderAbortBanner();
            }
        } catch (ItemNotAvailableException | TaxException | DataValidationException | FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void editAnOrder() throws FlooringMasteryPersistenceException {
        Integer editChoice = 0;
        Order orderToEdit = new Order();
        Order editedOrder = new Order();
        LocalDate orderToEditDate = null;

        boolean success = false;
        while (!success) {
            success = true;
            try {
                //added this try/catch in order to catch bad dates and send user back to main menu
                try {
                    //get order date to edit from user
                    orderToEditDate = view.getOrderDate();
                    //display all order for the given date.
                    view.displayAllOrders(service.getOrdersByDate(orderToEditDate));
                } catch (FlooringMasteryPersistenceException e) {
                    view.displayErrorMessage(e.getMessage());
                    return;
                }
                //If we make it through date validation, and have presented orders, display existing orders
                editChoice = view.getEditChoice();
                if (editChoice > 0) {
                    //display products
                    view.displayAllProducts(service.getAllProducts());
                    //display tax states
                    view.displayAllTaxes(service.retrieveTaxList());
                    //Bring back the original order to edit
                    orderToEdit = service.retrieveOrder(orderToEditDate, editChoice);
                    //Get the details from the user for the edited order
                    editedOrder = view.getEditedOrderDetails(orderToEdit);
                    //Call the editOrder method to delte the old order from the map and add the new edited order
                    service.editOrder(orderToEdit, editedOrder);
                }
            } catch (NoOrderFoundException | ItemNotAvailableException | TaxException | DataValidationException e) {
                success = false;
                view.displayErrorMessage(e.getMessage());
            }
        }
    }

    private void removeAnOrder() throws FlooringMasteryPersistenceException {
        LocalDate orderToEditDate = view.getOrderDate();
        boolean success = false;
        while (!success) {
            success = true;
            try {
                view.displayAllOrders(service.getOrdersByDate(orderToEditDate));
                Integer editChoice = view.getRemoveChoice();
                service.removeOrder(orderToEditDate, editChoice);
            } catch (FlooringMasteryPersistenceException | NoOrderFoundException e) {
                success = false;
                view.displayErrorMessage(e.getMessage());
                return;
            }
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void saveProgess() {
        try {
            service.saveCurrentWork();
            view.displayChangesSavedBanner();
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
}
