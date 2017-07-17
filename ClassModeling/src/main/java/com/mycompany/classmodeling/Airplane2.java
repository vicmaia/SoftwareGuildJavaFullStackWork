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
public class Airplane2 {

    private int modelIdentifier;
    private Controls controlSetup;
    private Exeterior exteriorAppearance;
    private Interior interiorAppearance;
    private double maxSpeed;
    private int numEngines;

    public Airplane2() {

    }

    public Airplane2(double maxSpeed, int numEngines) {
        this.maxSpeed = maxSpeed;
        this.numEngines = numEngines;
    }

    public int getModelIdentifier() {
        return modelIdentifier;
    }

    public void setModelIdentifier(int modelIdentifier) {
        this.modelIdentifier = modelIdentifier;
    }

    public Controls getControlSetup() {
        return controlSetup;
    }

    public void setControlSetup(Controls controlSetup) {
        this.controlSetup = controlSetup;
    }

    public Exeterior getExteriorAppearance() {
        return exteriorAppearance;
    }

    public void setExteriorAppearance(Exeterior exteriorAppearance) {
        this.exteriorAppearance = exteriorAppearance;
    }

    public Interior getInteriorAppearance() {
        return interiorAppearance;
    }

    public void setInteriorAppearance(Interior interiorAppearance) {
        this.interiorAppearance = interiorAppearance;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getNumEngines() {
        return numEngines;
    }

    public void setNumEngines(int numEngines) {
        this.numEngines = numEngines;
    }

}
