/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.service;

/**
 *
 * @author n0190934
 */
public class BlogServiceLayerForeignKeyConstraintException extends Exception{

    public BlogServiceLayerForeignKeyConstraintException(String message) {
        super(message);
    }

    public BlogServiceLayerForeignKeyConstraintException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
