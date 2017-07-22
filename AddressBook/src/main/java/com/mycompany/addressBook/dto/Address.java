/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Date Transfer Object
package com.mycompany.addressBook.dto;

/**
 *
 * @author n0252282
 */
public class Address {

    private String title;
    private String releaseDate;
    private String rating;
    private String director;
    private String studio;
    private String comment;

    public Address() {
    }

    public Address(String title, String releaseDate, String rating, String director, String studio, String comment) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.director = director;
        this.studio = studio;
        this.comment = comment;
    }
    
    public Address(String title) {
        this.title = title;
    }

    //added for the edit feature so that we can edit titles
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
