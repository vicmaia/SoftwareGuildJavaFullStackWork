/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.listexamples;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author n0252282
 */
public class MapExamples {

    public static void main(String[] args) {
        Map<String, Integer> populations = new HashMap<>();

        populations.put("USA", 200000000);

        populations.put("Canada", 34000000);

        populations.put("United Kingdom", 63000000);

        populations.put("Japan", 127000000);

        System.out.println("Map size is: " + populations.size());
// PART 2        
//        Integer usaPopulation = populations.get("USA");
//        
//        System.out.println(usaPopulation);
//        
//        populations.put("USA", 313000000);
//        
//        usaPopulation = populations.get("USA");
//        
//        System.out.println(usaPopulation);

// PART 3
//        Set<String> keys = populations.keySet();
//        
//        for (String currentKey : keys) {
//            Integer currentPopulation = populations.get(currentKey);
//            System.out.println("The population of: " + currentKey + " is " + currentPopulation);
//             }
        Collection<Integer> populationValues = populations.values();
        
        for (Integer currentPopulation : populationValues) {
            System.out.println(currentPopulation);
        }

    }
}
