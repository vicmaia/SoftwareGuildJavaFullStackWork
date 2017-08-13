/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author n0252282
 */
public class FlooringMasteryProductDaoStubFileImpl implements FlooringMasteryProductDao {

    private Map<String, Product> products = new HashMap<>();

    Product product1 = new Product();
    Product product2 = new Product();
    Product product3 = new Product();
    Product product4 = new Product();

    public FlooringMasteryProductDaoStubFileImpl() {
    }

    @Override
    public List<Product> getAllProducts() throws FlooringMasteryPersistenceException {
        product1.setProductType("Carpet");
        product1.setCostPerSquareFoot(new BigDecimal("2.25"));
        product1.setCostPerSquareFoot(new BigDecimal("2.10"));
        products.put(product1.getProductType(), product1);

        product2.setProductType("Laminate");
        product2.setCostPerSquareFoot(new BigDecimal("1.75"));
        product2.setCostPerSquareFoot(new BigDecimal("2.10"));
        products.put(product2.getProductType(), product2);

        product3.setProductType("Tile");
        product3.setCostPerSquareFoot(new BigDecimal("3.50"));
        product3.setCostPerSquareFoot(new BigDecimal("4.15"));
        products.put(product3.getProductType(), product3);

        product4.setProductType("Wood");
        product4.setCostPerSquareFoot(new BigDecimal("5.15"));
        product4.setCostPerSquareFoot(new BigDecimal("4.75"));
        products.put(product4.getProductType(), product4);

        return new ArrayList<>(products.values());
    }

    @Override
    public Product getProduct(String productID) throws FlooringMasteryPersistenceException {
        getAllProducts();
        return products.get(productID);
    }

}
