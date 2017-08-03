/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.advice;

import com.mycompany.vendingmachine.dao.VendingMachineAuditDao;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.service.InsufficientFundsException;
import com.mycompany.vendingmachine.service.NoItemInventoryException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author n0252282
 */
public class LoggingAdvice {

    VendingMachineAuditDao auditDao;

    public LoggingAdvice(VendingMachineAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    //Exception catches all exceptions
    public void createAuditEntryIFE(JoinPoint jp, InsufficientFundsException ex) {

        Object[] args = jp.getArgs();
        //getsignature.getname = need of the calling method "getStudent" "removeStudent"
        String auditEntry = "Method name: " + jp.getSignature().getName() + " | ";

        auditEntry += "Item Requested: ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        auditEntry += " | InsufficientFundsException: " + ex;
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void createAuditEntryNII(JoinPoint jp, NoItemInventoryException ex) {
        Object[] args = jp.getArgs();
        //getsignature.getname = need of the calling method "getStudent" "removeStudent"
        String auditEntry = "Method name: " + jp.getSignature().getName() + " | ";

        auditEntry += "Item Requested: ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        auditEntry += " | NoItemInventoryException: " + ex;
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
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
        auditEntry += "Item successfully purchased: ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

}
