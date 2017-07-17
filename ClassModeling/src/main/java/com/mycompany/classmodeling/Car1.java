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
public class Car1 {

    private double priceSticker;
    private double priceInvoice;
    private String manufacturer;
    private String model;
    private int daysOnLot;
    private boolean lemonIndicator;

    public Car1() {

    }

    public Car1(double priceSticker, double priceInvoice) {
        this.priceSticker = priceSticker;
        this.priceInvoice = priceInvoice;
    }

    public double getPriceSticker() {
        return priceSticker;
    }

    public void setPriceSticker(double priceSticker) {
        this.priceSticker = priceSticker;
    }

    public double getPriceInvoice() {
        return priceInvoice;
    }

    public void setPriceInvoice(double priceInvoice) {
        this.priceInvoice = priceInvoice;
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

    public int getDaysOnLot() {
        return daysOnLot;
    }

    public void setDaysOnLot(int daysOnLot) {
        this.daysOnLot = daysOnLot;
    }

    public boolean isLemonIndicator() {
        return lemonIndicator;
    }

    public void setLemonIndicator(boolean lemonIndicator) {
        this.lemonIndicator = lemonIndicator;
    }

}
