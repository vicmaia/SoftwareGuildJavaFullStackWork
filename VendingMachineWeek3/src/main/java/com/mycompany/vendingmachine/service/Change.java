/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

/**
 *
 * @author n0252282
 */
public class Change {

    private int numPennies = 0;
    private int numNickles = 0;
    private int numDimes = 0;
    private int numQuarters = 0;

    public Change (int numPennies) {

        if (numPennies >= 25) {
            this.numQuarters = numPennies / 25;
            numPennies -= this.numQuarters * 25;
        }
        if (numPennies >= 10) {
            this.numDimes = numPennies / 10;
            numPennies -= this.numDimes * 10;
        }
        if (numPennies >= 5) {
            this.numNickles = numPennies / 5;
            numPennies -= this.numNickles * 5;
        }
        if (numPennies < 5) {
            this.numPennies = numPennies;
        }
    }

    public int getNumPennies() {
        return numPennies;
    }

    public int getNumNickles() {
        return numNickles;
    }

    public int getNumDimes() {
        return numDimes;
    }

    public int getNumQuarters() {
        return numQuarters;
    }
    
}
