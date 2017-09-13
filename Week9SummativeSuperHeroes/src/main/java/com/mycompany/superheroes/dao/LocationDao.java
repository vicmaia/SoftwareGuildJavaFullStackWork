/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superheroes.dao;

import com.mycompany.superheroes.models.Location;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface LocationDao {

    //Location
    public void addLocation(Location location);

    public void deleteLocation(int locationID);

    public void updateLocation(Location location);

    public Location getLocationById(int locationID);

    public List<Location> getAllLocations();

}
