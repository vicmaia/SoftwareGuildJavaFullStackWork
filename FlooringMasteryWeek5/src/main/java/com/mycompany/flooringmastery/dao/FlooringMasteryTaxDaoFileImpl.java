/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

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
public class FlooringMasteryTaxDaoFileImpl implements FlooringMasteryTaxDao {

    public String taxFile = "Taxes.txt";
    public static final String DELIMITER = ",";

    private Map<String, Tax> taxes = new HashMap<>();

    public FlooringMasteryTaxDaoFileImpl(String productFile) {
        this.taxFile = productFile;
    }

    public FlooringMasteryTaxDaoFileImpl() {
    }

    @Override
    public List<Tax> getAllTaxes() throws FlooringMasteryPersistenceException {
        loadProducts();
        return new ArrayList<>(taxes.values());
    }

    @Override
    public Tax getTax(String state) throws FlooringMasteryPersistenceException {
        loadProducts();
        return taxes.get(state);
    }

    private void loadProducts() throws FlooringMasteryPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(taxFile)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "-_- Could not load tax data into memory.", e);
        }
        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            Tax currentTax = new Tax(currentTokens[0]);

            currentTax.setTaxRate(new BigDecimal(currentTokens[1]));

            //put product into map
            taxes.put(currentTax.getState(), currentTax);
        }
        // close scanner
        scanner.close();
    }

}
