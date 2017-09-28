/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.dao;

import com.sg.blog.model.Author;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author n0001123
 */
public class AuthorDaoStubImpl implements AuthorDao {

    Map<Integer, Author> authorList = new HashMap();

    public AuthorDaoStubImpl() {
        Author author = new Author();
        author.setFirstName("first_name");
        author.setLastName("last_name");
        author.setTitle("author_title");
        author.setAuthorID(author.getAuthorID());
        authorList.put(author.getAuthorID(), author);
    }

    @Override
    public void addAuthor(Author author) {
        author.setAuthorID(16);
        author.setFirstName("first_name");
        author.setLastName("last_name");
        author.setTitle("author_title");
        authorList.put(author.getAuthorID(), author);
    }

    @Override
    public void deleteAuthor(int authorID) {
               
             }

    @Override
    public void updateAuthor(Author author) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Author getAuthorById(int authorID) {
        return authorList.get(authorID);
    }

    @Override
    public List<Author> getAllAuthors() {
        return new ArrayList<>(authorList.values());
    }

}
