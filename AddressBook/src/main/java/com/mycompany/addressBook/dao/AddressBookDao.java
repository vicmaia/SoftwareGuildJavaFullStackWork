/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressBook.dao;

import com.mycompany.addressBook.dto.AddressBookException;
import com.mycompany.addressBook.dto.Address;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface AddressBookDao {

    Address addDVD(String DVD, Address dvd) throws AddressBookException;

    List<Address> getAllDVDs() throws AddressBookException;

    Address getDVD(String dvdTitle) throws AddressBookException;

    Address removeDVD(String dvdTitle) throws AddressBookException;
}
