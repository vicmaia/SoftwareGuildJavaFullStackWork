/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.controller;

import com.mycompany.vendingmachine.dto.VendingMachinePersistenceException;
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
                        //purchase();
                        break;
                    case 3:
                        //getChange();
                        break;
                    case 4:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (Exception e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {

        try {
            view.displayAllItems(service.getAllItems());
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        return view.printMenuAndGetSelection();
    }
    
    private void addMoney() {
        service.setCurrentMoney(view.getMoneyEntry());
        view.displayCurrentMoney(service.getCurrentMoney());
    }

//    private void purchase() throws VendingMachinePersistenceException {
////        view.displayCreateStudentBanner();
////        Item newStudent = view.getNewStudentInfo();
////        dao.addStudent(newStudent.getStudentId(), newStudent);
////        view.displayCreateSuccessBanner();
//        view.displayCreateStudentBanner();
//        boolean hasErrors = false;
//        do {
//            Item currentStudent = view.getNewStudentInfo();
//            try {
//                service.createStudent(currentStudent);
//                view.displayCreateSuccessBanner();
//                hasErrors = false;
//            } catch (VendingMachineDuplicateIdException | VendingMachineDataValidationExceptione) {
//                hasErrors = true;
//                view.displayErrorMessage(e.getMessage());
//            }
//        } while (hasErrors);
//    }
//
//    private void addMoney() throws VendingMachinePersistenceException {
//        view.displayDisplayAllBanner();
//        //List<Student> studentList = dao.getAllStudents();
//        List<Item> studentList = service.getAllStudents();
//        view.displayStudentList(studentList);
//    }
//
    private void getChange() throws VendingMachinePersistenceException {

    }
//
//    private void removeStudent() throws VendingMachinePersistenceException {
////        view.displayRemoveStudentBanner();
////        String studentId = view.getStudentIdChoice();
////        dao.removeStudent(studentId);
////        view.displayRemoveSuccessBanner();
//
//        view.displayRemoveStudentBanner();
//        String studentId = view.getStudentIdChoice();
//        service.removeStudent(studentId); //this is the change with the service layer
//        view.displayRemoveSuccessBanner();
//    }
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
