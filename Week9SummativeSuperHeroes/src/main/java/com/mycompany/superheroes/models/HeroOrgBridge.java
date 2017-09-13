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
public class HeroOrgBridge {

    private Integer heroID;
    private Integer orgID;

    public Integer getHeroID() {
        return heroID;
    }

    public void setHeroID(Integer heroID) {
        this.heroID = heroID;
    }

    public Integer getOrgID() {
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.heroID);
        hash = 89 * hash + Objects.hashCode(this.orgID);
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
        final HeroOrgBridge other = (HeroOrgBridge) obj;
        if (!Objects.equals(this.heroID, other.heroID)) {
            return false;
        }
        if (!Objects.equals(this.orgID, other.orgID)) {
            return false;
        }
        return true;
    }

}
