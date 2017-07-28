/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.service.VendingMachineDataValidationException;
import com.mycompany.vendingmachine.service.VendingMachineDuplicateIdException;
import com.mycompany.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.mycompany.vendingmachine.dao.VendingMachineAuditDaoStubImpl;
import com.mycompany.vendingmachine.dao.VendingMachineDaoStubImpl;
import com.mycompany.vendingmachine.dto.Item;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.mycompany.vendingmachine.dao.VendingMachineAuditDao;
import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.service.VendingMachineDataValidationException;
import com.mycompany.vendingmachine.service.VendingMachineDuplicateIdException;
import com.mycompany.vendingmachine.service.VendingMachineServiceLayer;
import com.mycompany.vendingmachine.service.VendingMachineServiceLayer;
import com.mycompany.vendingmachine.service.VendingMachineServiceLayerImpl;

/**
 *
 * @author n0252282
 */
public class ClassRosterServiceLayerTest {

    private VendingMachineServiceLayer service;

    public ClassRosterServiceLayerTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();

        service = new VendingMachineServiceLayerImpl(dao, auditDao);
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
     * Test of createStudent method, of class VendingMachineServiceLayer.
     */
    //if it returns no exceptions etc., test passes, no reason to add assert
    @Test
    public void testCreateStudent() throws Exception {
        Item student = new Item("0002");
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort("Java-Jan-2015");
        service.createStudent(student);
    }

    //if we catch the exception, it passes
    @Test
    public void testCreateStudentDuplicateId() throws Exception {
        Item student = new Item("0001");
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort("Java-Jan-2015");

        try {
            service.createStudent(student);
            //fail is a static method from jUnit.  If this test passes and tries to
            //create student, no bueno
            fail("Expected ClassRosterDuplicateIdException was not thrown.");
        } catch (VendingMachineDuplicateIdException e) {
            return;
        }

    }

    @Test
    public void testCreateStudentInvalidData() throws Exception {
        Item student = new Item("0002");
        student.setFirstName("");
        student.setLastName("Smith");
        student.setCohort("Java-Jan-2015");

        try {
            service.createStudent(student);
            //fail is a static method from jUnit.  If this test passes and tries to
            //create student, no bueno
            fail("Expected ClassRosterDataValidationException was not thrown.");
        } catch (VendingMachineDataValidationException e) {
            return;
        }

    }

    @Test
    public void testGetAllStudents() throws Exception {
        assertEquals(1, service.getAllStudents().size());
    }

    @Test
    public void testGetStudent() throws Exception {
        Item student = service.getStudent("0001");
        assertNotNull(student);
        student = service.getStudent("9999");
        assertNull(student);
    }

    @Test
    public void testRemoveStudent() throws Exception {
        Item student = service.removeStudent("0001");
        assertNotNull(student);
        student = service.removeStudent("9999");
        assertNull(student);
    }
}
