/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dto;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author n0252282
 */
public class Order {

    private Integer orderNumber;
    private LocalDate orderDate;
    private String customerName;
    private Tax taxRate;
    private Product product;
    private BigDecimal area;

    //calculated fields
//    private BigDecimal materialCost;
//    private BigDecimal laborCost;
//    private BigDecimal taxTotal;
//    private BigDecimal totalCost;
    public Order() {
    }

    public Order(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Order(Integer orderNumber, LocalDate orderDate, String customerName, Tax taxRate, Product product, BigDecimal area) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.taxRate = taxRate;
        this.product = product;
        this.area = area;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Tax getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Tax taxRate) {
        this.taxRate = taxRate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getMaterialCost() {
        return product.getCostPerSquareFoot().multiply(this.area);
    }

    public BigDecimal getLaborCost() {
        return product.getLaborCostPerSquareFoot().multiply(this.area);
    }

    public BigDecimal getTaxTotal() {
        BigDecimal onehundred = new BigDecimal("100");
        return taxRate.getTaxRate().divide(onehundred, MathContext.UNLIMITED).multiply((this.getMaterialCost()).add(this.getLaborCost())).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotalCost() {
        return this.getLaborCost().add(this.getTaxTotal()).add(this.getMaterialCost()).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.orderNumber);
        hash = 59 * hash + Objects.hashCode(this.orderDate);
        hash = 59 * hash + Objects.hashCode(this.customerName);
        hash = 59 * hash + Objects.hashCode(this.taxRate);
        hash = 59 * hash + Objects.hashCode(this.product);
        hash = 59 * hash + Objects.hashCode(this.area);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.orderNumber, other.orderNumber)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{"
                + "orderNumber=" + orderNumber
                + ", orderDate=" + orderDate
                + ", customerName=" + customerName
                + ", taxRate=" + taxRate
                + ", product=" + product
                + ", area=" + area
                + '}';
    }

}
