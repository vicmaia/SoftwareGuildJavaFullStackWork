/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.service;

/**
 *
 * @author n0001123
 */
public class BlogServiceLayerDataValidationException extends Exception {

    public BlogServiceLayerDataValidationException(String message) {
        super(message);
    }

    public BlogServiceLayerDataValidationException(String message,
            Throwable cause) {
        super(message, cause);
    } 
}
