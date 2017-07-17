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
public class IceCream1 {

    private double freezerTemp;
    private double creamTankTemp;
    private String productName;
    private double productVolume;
    private double conveyerSpeed;
    private int cowMood;

    public IceCream1() {

    }

    public IceCream1(double freezerTemp, double creamTankTemp) {
        this.freezerTemp = freezerTemp;
        this.creamTankTemp = creamTankTemp;
    }

    public double getFreezerTemp() {
        return freezerTemp;
    }

    public void setFreezerTemp(double freezerTemp) {
        this.freezerTemp = freezerTemp;
    }

    public double getCreamTankTemp() {
        return creamTankTemp;
    }

    public void setCreamTankTemp(double creamTankTemp) {
        this.creamTankTemp = creamTankTemp;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductVolume() {
        return productVolume;
    }

    public void setProductVolume(double productVolume) {
        this.productVolume = productVolume;
    }

    public double getConveyerSpeed() {
        return conveyerSpeed;
    }

    public void setConveyerSpeed(double conveyerSpeed) {
        this.conveyerSpeed = conveyerSpeed;
    }

    public int getCowMood() {
        return cowMood;
    }

    public void setCowMood(int cowMood) {
        this.cowMood = cowMood;
    }

}
