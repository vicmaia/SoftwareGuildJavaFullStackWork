/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Product;
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
public class FlooringMasteryProductDaoFileImpl implements FlooringMasteryProductDao {

    public String productFile = "Products.txt";
    public static final String DELIMITER = ",";

    private Map<String, Product> products = new HashMap<>();

    public FlooringMasteryProductDaoFileImpl(String productFile) {
        this.productFile = productFile;
    }

    public FlooringMasteryProductDaoFileImpl() {
    }

    @Override
    public List<Product> getAllProducts() throws FlooringMasteryPersistenceException {
        loadProducts();
        return new ArrayList<>(products.values());
    }

    @Override
    public Product getProduct(String productID) throws FlooringMasteryPersistenceException {
        loadProducts();
        return products.get(productID);
    }

    private void loadProducts() throws FlooringMasteryPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(productFile)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "-_- Could not load product data into memory.", e);
        }
        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            Product currentProduct = new Product(currentTokens[0]);

            currentProduct.setCostPerSquareFoot(new BigDecimal(currentTokens[1]));

            currentProduct.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[2]));

            //put product into map
            products.put(currentProduct.getProductType(), currentProduct);
        }
        // close scanner
        scanner.close();
    }

}
