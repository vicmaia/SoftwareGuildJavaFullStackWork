/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superheroes.dao;

import com.mycompany.superheroes.models.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface SightingDao {

    //Sightings
    public void addSighting(Sighting sighting);

    public void deleteSighting(int sightingID);

    public void updateSighting(Sighting sighting);

    public Sighting getSightingById(int sightingID);

    public List<Sighting> getAllSightings();

    //Reports
    public List<Sighting> getSightingsByLocation(int LocationID);

    public List<Sighting> getSightingsByHero(int HeroID);

    public List<Sighting> getSightingsByDate(LocalDate sightingDate);
}
