/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.controller;

import com.mycompany.classroster.dao.ClassRosterDao;
import com.mycompany.classroster.dao.ClassRosterDaoFileImpl;
import com.mycompany.classroster.dto.ClassRosterPersistenceException;
import com.mycompany.classroster.dto.Student;
import com.mycompany.classroster.service.ClassRosterDataValidationException;
import com.mycompany.classroster.service.ClassRosterDuplicateIdException;
import com.mycompany.classroster.service.ClassRosterServiceLayer;
import com.mycompany.classroster.ui.ClassRosterView;
import com.mycompany.classroster.ui.UserIO;
import com.mycompany.classroster.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author n0252282
 */
public class ClassRosterController {

    ClassRosterView view;
    //ClassRosterDao dao;
//    private UserIO io = new UserIOConsoleImpl();

    // Replace the field declaration for the ClassRosterDao:
    // private ClassRosterDao dao;
    // with a declaration for the ClassRosterServiceLayer:
    private ClassRosterServiceLayer service;

    public ClassRosterController(ClassRosterView view, ClassRosterServiceLayer service) {
        this.view = view;
        this.service = service;
        //Replaced this with the service
        //this.dao = dao;
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
