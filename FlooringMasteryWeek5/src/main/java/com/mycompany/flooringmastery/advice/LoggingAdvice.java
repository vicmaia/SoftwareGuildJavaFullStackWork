/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.advice;

import com.mycompany.flooringmastery.dao.FlooringMasteryAuditDao;
import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.flooringmastery.service.ItemNotAvailableException;
import com.mycompany.flooringmastery.service.TaxException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author n0252282
 */
public class LoggingAdvice {

    FlooringMasteryAuditDao auditDao;

    public LoggingAdvice(FlooringMasteryAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    //Exception catches all exceptions
    public void createAuditEntryINAE(JoinPoint jp, ItemNotAvailableException ex) {

        Object[] args = jp.getArgs();
        //getsignature.getname = need of the calling method "getStudent" "removeStudent"
        String auditEntry = "Method name: " + jp.getSignature().getName() + " | ";

        auditEntry += "Item Requested: ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        auditEntry += " | ItemNotAvailableException: " + ex;
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringMasteryPersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void createAuditEntryTE(JoinPoint jp, TaxException ex) {
        Object[] args = jp.getArgs();
        //getsignature.getname = need of the calling method "getStudent" "removeStudent"
        String auditEntry = "Method name: " + jp.getSignature().getName() + " | ";

        auditEntry += "Item Requested: ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        auditEntry += " | Tax Exception: " + ex;
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringMasteryPersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void createAuditEntry(JoinPoint jp) {
        //JoinPoint - is the object that carries the information about the joinpoint or pointcut (the place where 
        //this will be used.  Let's us create this method in a generic way.

        //paramters can be of any type, so we need an array of objects
        Object[] args = jp.getArgs();
        //getsignature.getname = need of the calling method "getStudent" "removeStudent"
        String auditEntry = "Method name: " + jp.getSignature().getName() + " | ";
        auditEntry += "Order successfully added: ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringMasteryPersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

}
