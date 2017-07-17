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
public class Car2 {

    private double topSpeed;
    private int firstLevelUnlocked;
    private String manufacturer;
    private String model;
    private int weight;
    private String color;

    public Car2() {

    }

    public Car2(int weight, double topSpeed) {
        this.weight = weight;
        this.topSpeed = topSpeed;
    }

    public double getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(double topSpeed) {
        this.topSpeed = topSpeed;
    }

    public int getFirstLevelUnlocked() {
        return firstLevelUnlocked;
    }

    public void setFirstLevelUnlocked(int firstLevelUnlocked) {
        this.firstLevelUnlocked = firstLevelUnlocked;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    
}
