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
        //create a new order, passing in order date and order details from user
        LocalDate orderDate = view.getOrderDate();
        try {
            Order newOrder = view.getNewOrderDetails();
            if (view.getPersistDataChoice().compareToIgnoreCase("s") == 0) {
                //display products
                view.displayAllProducts(service.getAllProducts());
                //display tax states
                view.displayAllTaxes(service.retrieveTaxList());
                service.createOrder(orderDate, newOrder);
                service.saveCurrentWork();
                view.displayChangesSavedBanner();
            } else {
                view.displayOrderAbortBanner();

            }
        } catch (FlooringMasteryPersistenceException | ItemNotAvailableException | TaxException | DataValidationException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void editAnOrder() throws FlooringMasteryPersistenceException {
        Integer editChoice = 0;

        Order orderToEdit = new Order();
        Order editedOrder = new Order();

//        //display products
//        view.displayAllProducts(service.getAllProducts());
//        //display tax states
//        view.displayAllTaxes(service.retrieveTaxList());
        boolean success = false;
        while (!success) {
            success = true;
            try {
                //get order date to edit from user
                LocalDate orderToEditDate = view.getOrderDate();
                view.displayAllOrders(service.getOrdersByDate(orderToEditDate));
                editChoice = view.getEditChoice();
                if (editChoice > 0) {
                    //display products
                    view.displayAllProducts(service.getAllProducts());
                    //display tax states
                    view.displayAllTaxes(service.retrieveTaxList());
                    orderToEdit = service.retrieveOrder(orderToEditDate, editChoice);
                    editedOrder = view.getEditedOrderDetails(orderToEdit);
                    service.editOrder(orderToEdit, editedOrder);
                }
            } catch (NoOrderFoundException | ItemNotAvailableException | TaxException | DataValidationException | FlooringMasteryPersistenceException e) {
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
