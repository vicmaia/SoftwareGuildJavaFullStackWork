/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.dao;

/**
 *
 * @author n0001123
 */
    public class BlogPersistenceException extends Exception {

        public BlogPersistenceException(String message) {
            super(message);
        }

        public BlogPersistenceException(String message, Throwable cause) {
            super(message, cause);
        }

    }
