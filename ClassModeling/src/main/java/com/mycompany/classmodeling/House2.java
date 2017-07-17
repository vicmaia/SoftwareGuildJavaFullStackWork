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
public class House2 {
    private double height;
    private double width;
    private double depth;
    private String outsideColor;
    private String numFloors;
    
    public House2(){
        
    }
    
    public House2(double height, double width, double depth){
            this.height = height;
            this.width = width;
            this.depth = depth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public String getOutsideColor() {
        return outsideColor;
    }

    public void setOutsideColor(String outsideColor) {
        this.outsideColor = outsideColor;
    }

    public String getNumFloors() {
        return numFloors;
    }

    public void setNumFloors(String numFloors) {
        this.numFloors = numFloors;
    }
    
    
}
