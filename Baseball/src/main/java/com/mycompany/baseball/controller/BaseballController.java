/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseball.controller;

import com.mycompany.baseball.service.BaseballService;
import com.mycompany.baseball.service.BaseballServiceImpl;
import com.mycompany.baseball.ui.BaseballView;
import com.mycompany.baseball.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author n0252282
 */
public class BaseballController {

    BaseballView view;
    private BaseballService service;

    public BaseballController(BaseballView view, BaseballService service) {
        this.view = view;
        this.service = service;
    }
    //for sandboxing
    public BaseballController() {
        this.view = new BaseballView(new UserIOConsoleImpl());
        this.service = new BaseballServiceImpl();
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listStudents();
                        break;
                    case 2:
                        createStudent();
                        break;
                    case 3:
                        viewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (ClassRosterPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createStudent() throws ClassRosterPersistenceException {
//        view.displayCreateStudentBanner();
//        Student newStudent = view.getNewStudentInfo();
//        dao.addStudent(newStudent.getStudentId(), newStudent);
//        view.displayCreateSuccessBanner();
        view.displayCreateStudentBanner();
        boolean hasErrors = false;
        do {
            Student currentStudent = view.getNewStudentInfo();
            try {
                service.createStudent(currentStudent);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void listStudents() throws ClassRosterPersistenceException {
        view.displayDisplayAllBanner();
        //List<Student> studentList = dao.getAllStudents();
        List<Student> studentList = service.getAllStudents();
        view.displayStudentList(studentList);
    }

    private void viewStudent() throws ClassRosterPersistenceException {
        view.displayDisplayStudentBanner();
//        String studentId = view.getStudentIdChoice();
//        Student student = dao.getStudent(studentId);
//        view.displayStudent(student);
        String studentId = view.getStudentIdChoice();
        Student student = service.getStudent(studentId);
        view.displayStudent(student);
    }

    private void removeStudent() throws ClassRosterPersistenceException {
//        view.displayRemoveStudentBanner();
//        String studentId = view.getStudentIdChoice();
//        dao.removeStudent(studentId);
//        view.displayRemoveSuccessBanner();

        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        service.removeStudent(studentId); //this is the change with the service layer
        view.displayRemoveSuccessBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
}
