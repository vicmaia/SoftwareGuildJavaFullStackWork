/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.funwithjdbc.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author n0252282
 */
public class Beer {
    private String beerName;
    private float ABV;
    private String style;
    private BigDecimal price;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.beerName);
        hash = 23 * hash + Float.floatToIntBits(this.ABV);
        hash = 23 * hash + Objects.hashCode(this.style);
        hash = 23 * hash + Objects.hashCode(this.price);
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
        final Beer other = (Beer) obj;
        if (Float.floatToIntBits(this.ABV) != Float.floatToIntBits(other.ABV)) {
            return false;
        }
        if (!Objects.equals(this.beerName, other.beerName)) {
            return false;
        }
        if (!Objects.equals(this.style, other.style)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public float getABV() {
        return ABV;
    }

    public void setABV(float ABV) {
        this.ABV = ABV;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
