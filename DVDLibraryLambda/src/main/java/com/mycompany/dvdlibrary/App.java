package com.mycompany.dvdlibrary;

import com.mycompany.dvdlibrary.controller.DVDLibraryController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        //instantiate context
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DVDLibraryController controller;
        // ask context to instantiate controller
                // please get me the bean with the alias called controller and 
                // please cast it back as the type classrostercontroller
        if (args[0].equalsIgnoreCase("test")) {
            controller = ctx.getBean("controller", DVDLibraryController.class);
        } else {
            controller = ctx.getBean("controller", DVDLibraryController.class);
        }
        controller.run();

//        //Select an IO Implementation
//        UserIO myIo = new UserIOConsoleImpl();
//        //Stick IO in your view
//        DVDLibraryView myView = new DVDLibraryView(myIo);
//
//        //Select a DAO implementation
//        DVDLibraryLambdaDao myDao = new DVDLibraryLambdaDaoFileImpl();
//        //put controller in a variable for readability
//        DVDLibraryController controller;
//
//        //Stick view and dao in controller
//        controller = new DVDLibraryController(myView, myDao);
//
//        //kick off controller
//        controller.run();
    }
}
