/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superheroes.models;

import java.util.Objects;

/**
 *
 * @author n0252282
 */
public class Org {

    private Integer orgID;
    private String orgName;
    private String orgDescription;
    private Integer locationID;

    public Integer getOrgID() {
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
    }

    public Integer getLocationID() {
        return locationID;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.orgID);
        hash = 97 * hash + Objects.hashCode(this.orgName);
        hash = 97 * hash + Objects.hashCode(this.orgDescription);
        hash = 97 * hash + Objects.hashCode(this.locationID);
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
        final Org other = (Org) obj;
        if (!Objects.equals(this.orgName, other.orgName)) {
            return false;
        }
        if (!Objects.equals(this.orgDescription, other.orgDescription)) {
            return false;
        }
        if (!Objects.equals(this.orgID, other.orgID)) {
            return false;
        }
        if (!Objects.equals(this.locationID, other.locationID)) {
            return false;
        }
        return true;
    }



}
