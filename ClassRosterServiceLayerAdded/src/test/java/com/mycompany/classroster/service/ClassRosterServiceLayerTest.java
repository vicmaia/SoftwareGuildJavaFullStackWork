/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.service;

import com.mycompany.classroster.dto.ClassRosterPersistenceException;
import com.mycompany.classroster.dto.Student;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author n0252282
 */
public class ClassRosterServiceLayerTest {
    
    public ClassRosterServiceLayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createStudent method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testCreateStudent() throws Exception {
    }

    /**
     * Test of getAllStudents method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testGetAllStudents() throws Exception {
    }

    /**
     * Test of getStudent method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testGetStudent() throws Exception {
    }

    /**
     * Test of removeStudent method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testRemoveStudent() throws Exception {
    }

    public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {

        public void createStudent(Student student) throws ClassRosterDuplicateIdException, ClassRosterDataValidationException, ClassRosterPersistenceException {
        }

        public List<Student> getAllStudents() throws ClassRosterPersistenceException {
            return null;
        }

        public Student getStudent(String studentId) throws ClassRosterPersistenceException {
            return null;
        }

        public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
            return null;
        }
    }
    
}
