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
public class House1 {
    private double latitude;
    private double longitude;
    private double elevation;
    private String streetName;
    private String streetNumber;
    
    public House1(){
        
    }
    
    public House1(double latitudeIn, double longitudeIn){
            this.latitude = latitudeIn;
            this.longitude = longitudeIn;
    }
    //oould also use method/s to set elevation and streetName/streetNumber
    
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    
}
