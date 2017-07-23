/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Date Transfer Object
package com.mycompany.addressBook.dto;

/**
 *
 * @author n0252282
 */
public class Address {

    private String lastName;
    private String firstName;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;

    public Address() {
    }

    public Address(String lastName, String firstName, String streetAddress, String city, String state, String zip) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Address(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}
