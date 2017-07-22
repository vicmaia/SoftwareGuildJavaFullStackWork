/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dao;

import com.mycompany.dvdlibrary.dto.DVDLibraryException;
import com.mycompany.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface DVDLibraryDao {

    DVD addDVD(String DVD, DVD dvd) throws DVDLibraryException;

    List<DVD> getAllDVDs() throws DVDLibraryException;

    DVD getDVD(String dvdTitle) throws DVDLibraryException;

    DVD removeDVD(String dvdTitle) throws DVDLibraryException;
}
