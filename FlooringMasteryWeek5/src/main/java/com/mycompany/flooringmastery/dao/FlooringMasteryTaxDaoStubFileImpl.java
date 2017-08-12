/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import static com.mycompany.flooringmastery.dao.FlooringMasteryOrderDaoFileImpl.DELIMITER;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class FlooringMasteryTaxDaoStubFileImpl implements FlooringMasteryTaxDao {
    private Map<String, Tax> taxes = new HashMap<>();
    
    Tax tax1 = new Tax();
    Tax tax2 = new Tax();
    Tax tax3 = new Tax();
    Tax tax4 = new Tax();


    public FlooringMasteryTaxDaoStubFileImpl() {
    }

    @Override
    public List<Tax> getAllTaxes() throws FlooringMasteryPersistenceException {
        tax1.setState("OH");
        tax1.setTaxRate(new BigDecimal("6.25"));

        taxes.put(tax1.getState(), tax1);

        tax2.setState("PA");
        tax2.setTaxRate(new BigDecimal("6.75"));

        taxes.put(tax2.getState(), tax2);

        tax3.setState("MI");
        tax3.setTaxRate(new BigDecimal("5.75"));

        taxes.put(tax3.getState(), tax3);

        tax4.setState("IN");
        tax4.setTaxRate(new BigDecimal("6.00"));

        taxes.put(tax4.getState(), tax4);
        
        return new ArrayList<>(taxes.values());
    }

    @Override
    public Tax getTax(String state) throws FlooringMasteryPersistenceException {
        return taxes.get(state);
    }
}
