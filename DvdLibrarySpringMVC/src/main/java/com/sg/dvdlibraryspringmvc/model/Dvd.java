/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.model;

import java.util.Objects;

/**
 *
 * @author n0211021
 */
public class Dvd {
    private Integer dvdID;
    private String dvdTitle;
    private Integer releaseYear;
    private String director;
    private String rating;
    private String notes;

    public String getDvdTitle() {
        return dvdTitle;
    }

    public void setDvdTitle(String dvdTitle) {
        this.dvdTitle = dvdTitle;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getDvdID() {
        return dvdID;
    }

    public void setDvdID(Integer dvdID) {
        this.dvdID = dvdID;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.dvdID);
        hash = 73 * hash + Objects.hashCode(this.dvdTitle);
        hash = 73 * hash + Objects.hashCode(this.releaseYear);
        hash = 73 * hash + Objects.hashCode(this.director);
        hash = 73 * hash + Objects.hashCode(this.rating);
        hash = 73 * hash + Objects.hashCode(this.notes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dvd other = (Dvd) obj;
        if (!Objects.equals(this.dvdTitle, other.dvdTitle)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        if (!Objects.equals(this.rating, other.rating)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        if (!Objects.equals(this.dvdID, other.dvdID)) {
            return false;
        }
        if (!Objects.equals(this.releaseYear, other.releaseYear)) {
            return false;
        }
        return true;
    }
    
    
}
