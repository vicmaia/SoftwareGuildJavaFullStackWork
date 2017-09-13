/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superheroes.models;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author n0252282
 */
public class Sighting {

    private Integer sightingID;
    private Integer heroID;
    private Integer locationID;
    private LocalDate date;

    public Integer getSightingID() {
        return sightingID;
    }

    public void setSightingID(Integer sightingID) {
        this.sightingID = sightingID;
    }

    public Integer getHeroID() {
        return heroID;
    }

    public void setHeroID(Integer heroID) {
        this.heroID = heroID;
    }

    public Integer getLocationID() {
        return locationID;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.sightingID);
        hash = 59 * hash + Objects.hashCode(this.heroID);
        hash = 59 * hash + Objects.hashCode(this.locationID);
        hash = 59 * hash + Objects.hashCode(this.date);
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
        final Sighting other = (Sighting) obj;
        if (!Objects.equals(this.sightingID, other.sightingID)) {
            return false;
        }
        if (!Objects.equals(this.heroID, other.heroID)) {
            return false;
        }
        if (!Objects.equals(this.locationID, other.locationID)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }


}
