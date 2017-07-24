package com.mycompany.classroster;

import com.mycompany.classroster.controller.ClassRosterController;
import com.mycompany.classroster.dao.ClassRosterAuditDao;
import com.mycompany.classroster.dao.ClassRosterAuditDaoFileImpl;
import com.mycompany.classroster.dao.ClassRosterDao;
import com.mycompany.classroster.dao.ClassRosterDaoFileImpl;
import com.mycompany.classroster.service.ClassRosterServiceLayer;
import com.mycompany.classroster.service.ClassRosterServiceLayerImpl;
import com.mycompany.classroster.ui.ClassRosterView;
import com.mycompany.classroster.ui.UserIO;
import com.mycompany.classroster.ui.UserIOConsoleImpl;

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
        // Instantiate the UserIO implementation
        UserIO myIo = new UserIOConsoleImpl();

        // Instantiate the View and wire the UserIO implementation into it
        ClassRosterView myView = new ClassRosterView(myIo);

        // Instantiate the DAO
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();

        // Instantiate the Audit DAO
        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();

        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);

        // Instantiate the Controller and wire the Service Layer into it
        ClassRosterController controller;
        controller = new ClassRosterController(myView, myService); //change myDao to myService since that is what the controller communicates with now

        // Kick off the Controller
        controller.run();
    }
}
