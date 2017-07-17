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
public class Airplane1 {

    private String planeIdentifier;
    private String planeDestination;
    private String planeOrigin;
    private String carrier;
    private double flightPathAngle;
    private double flightPathDestination;

    public Airplane1() {

    }

    public Airplane1(String planeID, String planeDestination, String planeOrigin) {
        this.planeIdentifier = planeID;
        this.planeDestination = planeDestination;
        this.planeOrigin = planeOrigin;
    }

    public String getPlaneIdentifier() {
        return planeIdentifier;
    }

    public void setPlaneIdentifier(String planeIdentifier) {
        this.planeIdentifier = planeIdentifier;
    }

    public String getPlaneDestination() {
        return planeDestination;
    }

    public void setPlaneDestination(String planeDestination) {
        this.planeDestination = planeDestination;
    }

    public String getPlaneOrigin() {
        return planeOrigin;
    }

    public void setPlaneOrigin(String planeOrigin) {
        this.planeOrigin = planeOrigin;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public double getFlightPathAngle() {
        return flightPathAngle;
    }

    public void setFlightPathAngle(double flightPathAngle) {
        this.flightPathAngle = flightPathAngle;
    }

    public double getFlightPathDestination() {
        return flightPathDestination;
    }

    public void setFlightPathDestination(double flightPathDestination) {
        this.flightPathDestination = flightPathDestination;
    }

}
