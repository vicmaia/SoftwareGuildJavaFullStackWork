package com.mycompany.vendingmachine;

import com.mycompany.vendingmachine.controller.VendingMachineController;
import com.mycompany.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.mycompany.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.mycompany.vendingmachine.ui.VendingMachineView;
import com.mycompany.vendingmachine.ui.UserIO;
import com.mycompany.vendingmachine.ui.UserIOConsoleImpl;
import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.service.VendingMachineServiceLayer;

/**
 *
 * @author n0252282
 */
public class App {

    public static void main(String[] args) {
        // Instantiate the UserIO implementation
        UserIO myIo = new UserIOConsoleImpl();

        // Instantiate the View and wire the UserIO implementation into it
        VendingMachineView myView = new VendingMachineView(myIo);

        // Instantiate the DAO
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();

        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao);

        // Instantiate the Controller and wire the Service Layer into it
        VendingMachineController controller;
        controller = new VendingMachineController(myView, myService); //change myDao to myService since that is what the controller communicates with now

        // Kick off the Controller
        controller.run();
    }
}
