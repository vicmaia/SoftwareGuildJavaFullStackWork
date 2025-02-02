/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.dao;

import com.mycompany.classroster.dto.ClassRosterPersistenceException;
import com.mycompany.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface ClassRosterDao {

    /**
     * Adds the given Student to the roster and associates it with the given
     * student id. If there is already a student associated with the given
     * student id it will return that student object, otherwise it will return
     * null.
     *
     * @param studentId id with which student is to be associated
     * @param student student to be added to the roster
     * @return the Student object previously associated with the given student
     * id if it exists, null otherwise
     * @throws com.mycompany.classroster.dto.ClassRosterPersistenceException
     */
    Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException;

    /**
     * Returns a String array containing the student ids of all students in the
     * roster.
     *
     * @return String array containing the ids of all the students in the roster
     * @throws com.mycompany.classroster.dto.ClassRosterPersistenceException
     */
    List<Student> getAllStudents() throws ClassRosterPersistenceException;

    /**
     * Returns the student object associated with the given student id. Returns
     * null if no such student exists
     *
     * @param studentId ID of the student to retrieve
     * @return the Student object associated with the given student id, null if
     * no such student exists
     * @throws com.mycompany.classroster.dto.ClassRosterPersistenceException
     */
    Student getStudent(String studentId) throws ClassRosterPersistenceException;

    /**
     * Removes from the roster the student associated with the given id. Returns
     * the student object that is being removed or null if there is no student
     * associated with the given id
     *
     * @param studentId id of student to be removed
     * @return Student object that was removed or null if no student was
     * associated with the given student id
     * @throws com.mycompany.classroster.dto.ClassRosterPersistenceException
     */
    Student removeStudent(String studentId) throws ClassRosterPersistenceException;
}
