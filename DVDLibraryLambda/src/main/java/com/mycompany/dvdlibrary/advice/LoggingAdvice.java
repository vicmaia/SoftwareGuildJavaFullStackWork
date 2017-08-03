/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.advice;

import com.mycompany.dvdlibrary.dao.DVDLibraryAuditDao;
import com.mycompany.dvdlibrary.dao.DVDLibraryPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author n0252282
 */
public class LoggingAdvice {

    DVDLibraryAuditDao auditDao;

    public LoggingAdvice(DVDLibraryAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void createAuditEntry(JoinPoint jp) {
        //JoinPoint - is the object that carries the information about the joinpoint or pointcut (the place where 
        //this will be used.  Let's us create this method in a generic way.

        //paramters can be of any type, so we need an array of objects
        Object[] args = jp.getArgs();
        //getsignature.getname = need of the calling method "getStudent" "removeStudent"
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditEntry = "Testtesttesttest";
            auditDao.writeAuditEntry(auditEntry);
        } catch (DVDLibraryPersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

}
