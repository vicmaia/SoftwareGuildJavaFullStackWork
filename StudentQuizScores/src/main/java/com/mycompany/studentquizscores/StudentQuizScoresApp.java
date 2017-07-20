/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.studentquizscores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author n0252282
 */
public class StudentQuizScoresApp {

    public static void main(String[] args) {

        //initialize variables
        UserIO appIO = new UserIOImpl();
        HashMap<String, ArrayList<Integer>> quizGrades = new HashMap<>();
        String studentName = "";
        int menuChoice = 0;
        ArrayList<Integer> gradesOut = new ArrayList<>();
        //big String menu
        String menu = "========================Menu======================= \n"
                + "1. View a list of students in the system\n"
                + "2. Add a student to the system\n"
                + "3. Remove a student from the system\n"
                + "4. View a list of quiz scores for a given student\n"
                + "5. View the average quiz score for a given student\n"
                + "6. Exit System\n"
                + "=================================================== \n"
                + "Please enter your selection: ";

        //add some test data
        quizGrades.put("Ken", new ArrayList<>(Arrays.asList(70, 80, 90)));

        //Logic, IO, etc
        do {
            menuChoice = appIO.readInt(menu, 1, 6);

            switch (menuChoice) {
                case 1:
                    appIO.print("Student List: ");

                    for (String student : quizGrades.keySet()) {
                        appIO.print(student);
                    }
                    break;
                case 2:
                    String newStudent = appIO.readString("Please enter the student's name to add: ");
                    quizGrades.put(newStudent, new ArrayList<>(Arrays.asList()));
                    break;
                case 3:
                    String removeStudent = appIO.readString("Please enter the student's name to remove: ");
                    quizGrades.remove(removeStudent);
                    break;
                case 4:
                    appIO.print("Student Grade List: ");

                    studentName = appIO.readString("Student name: ");
                    for (int i = 0; i < quizGrades.get(studentName).size(); i++) {
                        System.out.print(quizGrades.get(studentName).get(i) + ", ");
                    }
                    appIO.print("");
                    break;
                case 5:
                    appIO.print("Student Quiz Average: ");

                    studentName = appIO.readString("Student name: ");
                    double sum = 0.0;
                    for (int i = 0; i < quizGrades.get(studentName).size(); i++) {
                        sum += quizGrades.get(studentName).get(i);
                    }
                    System.out.println("Quiz Average: " + (sum / quizGrades.get(studentName).size()));
                    break;
                case 6:

                    break;
                default:
                    throw new AssertionError();
            }
        } while (menuChoice != 6);
    }
}
