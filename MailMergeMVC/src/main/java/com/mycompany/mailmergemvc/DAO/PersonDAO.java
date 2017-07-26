/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mailmergemvc.DAO;

import com.mycompany.mailmergemvc.DTO.Person;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface PersonDAO {
    // DAOs are for CRUD operations
    // for this application we only need to 
    // read a list of people for now
    
    //R - read
    List<Person> retrieveAllPeople();
}
