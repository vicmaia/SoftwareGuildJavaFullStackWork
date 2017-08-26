/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachinespringmvc.controller;

import com.mycompany.vendingmachinespringmvc.dao.ErrorMessage;
import com.mycompany.vendingmachinespringmvc.service.InsufficientFundsException;
import com.mycompany.vendingmachinespringmvc.service.NoItemInventoryException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author n0252282
 */
public class ControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorMessage processMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Your submission has the following errors: ");

        for (FieldError currentError : fieldErrors) {
            messageBuilder.append("[");
            messageBuilder.append(currentError.getField());
            messageBuilder.append(":");
            messageBuilder.append(currentError.getDefaultMessage());
            messageBuilder.append("] ");
        }

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(messageBuilder.toString());
        return errorMessage;
    }
    
    @ExceptionHandler(InsufficientFundsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorMessage processUpdateIntegrityException(InsufficientFundsException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return errorMessage;
    }
    
    @ExceptionHandler(NoItemInventoryException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorMessage processUpdateIntegrityException(NoItemInventoryException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return errorMessage;
    }  
}
