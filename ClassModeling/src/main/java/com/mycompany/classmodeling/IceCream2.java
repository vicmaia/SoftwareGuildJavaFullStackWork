/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classmodeling;

/**
 *
 * @author n0252282
 */
public class IceCream2 {

    private int cartonsInStorage;
    private int cartonsInStore;
    private String productName;
    private boolean reorderIndictor;
    private boolean promotionalPricing;
    private double productPrice;

    public IceCream2() {

    }

    public IceCream2(int cartonsInStorage, int cartonsInStore) {
        this.cartonsInStorage = cartonsInStorage;
        this.cartonsInStore = cartonsInStore;
    }

    public int getCartonsInStorage() {
        return cartonsInStorage;
    }

    public void setCartonsInStorage(int cartonsInStorage) {
        this.cartonsInStorage = cartonsInStorage;
    }

    public int getCartonsInStore() {
        return cartonsInStore;
    }

    public void setCartonsInStore(int cartonsInStore) {
        this.cartonsInStore = cartonsInStore;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public boolean isReorderIndictor() {
        return reorderIndictor;
    }

    public void setReorderIndictor(boolean reorderIndictor) {
        this.reorderIndictor = reorderIndictor;
    }

    public boolean isPromotionalPricing() {
        return promotionalPricing;
    }

    public void setPromotionalPricing(boolean promotionalPricing) {
        this.promotionalPricing = promotionalPricing;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

}
