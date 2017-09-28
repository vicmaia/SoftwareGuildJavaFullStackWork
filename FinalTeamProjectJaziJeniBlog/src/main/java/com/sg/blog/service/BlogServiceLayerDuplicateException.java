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
public class BlogServiceLayerDuplicateException extends Exception {

    public BlogServiceLayerDuplicateException(String message) {
        super(message);
    }

    public BlogServiceLayerDuplicateException(String message, Throwable cause) {
        super(message, cause);
    } 
}
