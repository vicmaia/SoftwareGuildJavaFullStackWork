/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Date Transfer Object

package com.mycompany.classroster.dto;

/**
 *
 * @author n0252282
 */
public class Student {

    private String firstName;
    private String lastName;
    private String studentId;
    private String cohort; // Java or .Net + cohort month/year    

    public Student(String studentID) {
        this.studentId = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

}
