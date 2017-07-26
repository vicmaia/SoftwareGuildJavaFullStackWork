/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mailmergemvc.DAO;

import com.mycompany.mailmergemvc.DTO.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author n0252282
 */
public class PersonDAOInMemoryImpl implements PersonDAO {

    Map<String, Person> personList = new HashMap<>();

    @Override
    public List<Person> retrieveAllPeople() {
        return new ArrayList<Person>(personList.values());
    }

    private void loadPersonList() {
        Person morgan = new Person("morgan@yahoo", "Morgan");
        Person eric = new Person("eric@yahoo", "Eric");
        Person gabe = new Person("gabe@yahoo", "Gabe");

        personList.put(morgan.getEmail(), morgan);
        personList.put(eric.getEmail(), eric);
        personList.put(eric.getEmail(), eric);
    }
}
