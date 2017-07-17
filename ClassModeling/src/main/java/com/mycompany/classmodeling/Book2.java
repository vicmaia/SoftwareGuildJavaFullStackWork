/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classmodeling;

/**
 *
 * @author n0252282
 */
public class Book2 {

    private double deweyDecimal;
    private String title;
    private String subject;
    private int publishYear;
    private String author;
    private boolean borrowed;

    public Book2() {

    }

    public Book2(String title, String subject, String author) {
        this.title = title;
        this.subject = subject;
        this.author = author;
    }

    public double getDeweyDecimal() {
        return deweyDecimal;
    }

    public void setDeweyDecimal(double deweyDecimal) {
        this.deweyDecimal = deweyDecimal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

}
