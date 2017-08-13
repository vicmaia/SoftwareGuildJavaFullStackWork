/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author n0252282
 */
public class FlooringMasteryTaxDaoStubFileImpl implements FlooringMasteryTaxDao {

    private Map<String, Tax> taxes = new HashMap<>();

    public FlooringMasteryTaxDaoStubFileImpl() {
    }

    @Override
    public List<Tax> getAllTaxes() throws FlooringMasteryPersistenceException {
        Tax tax1 = new Tax("OH");
        tax1.setTaxRate(new BigDecimal("6.25"));

        taxes.put(tax1.getState(), tax1);

        Tax tax2 = new Tax("PA");
        tax2.setTaxRate(new BigDecimal("6.75"));

        taxes.put(tax2.getState(), tax2);

        Tax tax3 = new Tax("MI");
        tax3.setTaxRate(new BigDecimal("5.75"));

        taxes.put(tax3.getState(), tax3);

        Tax tax4 = new Tax("IN");
        tax4.setTaxRate(new BigDecimal("6.00"));

        taxes.put(tax4.getState(), tax4);

        return new ArrayList<>(taxes.values());
    }

    @Override
    public Tax getTax(String state) throws FlooringMasteryPersistenceException {
        getAllTaxes();
        return taxes.get(state);
    }
}
