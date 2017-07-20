package com.mycompany.statecapitals1.StateCapitals1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author n0252282
 */
public class Capital {

    String name;
    int population;
    double size;

    public Capital(String name, int population, double size) {
        this.name = name;
        this.population = population;
        this.size = size;
    }

//    @Override
//    public String toString() {
//        return "Capital{" + "name=" + name + ", population=" + population + ", size=" + size + '}';
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
    
    
}
