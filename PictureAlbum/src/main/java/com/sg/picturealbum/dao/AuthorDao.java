/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.picturealbum.dao;

import com.sg.picturealbum.model.Author;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface AuthorDao {

    public void addAuthor (Author author);

    public void deleteAuthor(int authorID);

    public void updateAuthor(Author author);

    public Author getAuthorById(int authorID);

    public List<Author> getAllAuthors();
}
