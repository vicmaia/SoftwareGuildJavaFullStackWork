/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dao;

import com.mycompany.dvdlibrary.dto.DVDLibraryException;
import com.mycompany.dvdlibrary.dto.DVD;
import java.util.List;
import java.util.Map;

/**
 *
 * @author n0252282
 */
public interface DVDLibraryLambdaDao {

    DVD addDVD(String DVD, DVD dvd) throws DVDLibraryException;

    List<DVD> getAllDVDs() throws DVDLibraryException;

    DVD getDVD(String dvdTitle) throws DVDLibraryException;

    DVD removeDVD(String dvdTitle) throws DVDLibraryException;

    // You will add the following features to your program:
//• Find all the movies with a given MPAA rating DONE!!!
//• Find all the movies by a given director DONE!!!
//• When searching by director, the movies should be sorted into separate data structures by MPAA rating. DONE!!!
//• Find all the movies released by a particular studio
    public List<DVD> getMoviesFilterMPAA(String MPAA) throws DVDLibraryException;

    public List<DVD> getMoviesFilterDirecetor(String director) throws DVDLibraryException;

    public Map<String, List<DVD>> getMoviesFilterDirectorSortMPAA(String director) throws DVDLibraryException;

    public List<DVD> getMoviesFilterStudio(String studio) throws DVDLibraryException;

}
