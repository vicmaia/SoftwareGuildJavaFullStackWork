/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dto;

/**
 *
 * @author n0252282
 */
public class ProgramConfig {
    private boolean prodMode;

    public boolean isProdMode() {
        return prodMode;
    }

    public void setProdMode(boolean prodMode) {
        this.prodMode = prodMode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.prodMode ? 1 : 0);
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
        final ProgramConfig other = (ProgramConfig) obj;
        if (this.prodMode != other.prodMode) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProgramConfig{" + "prodMode=" + prodMode + '}';
    }
    
}
