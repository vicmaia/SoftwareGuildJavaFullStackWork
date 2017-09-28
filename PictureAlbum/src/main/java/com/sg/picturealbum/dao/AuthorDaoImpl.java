/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.picturealbum.dao;

import com.sg.picturealbum.model.Author;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author n0252282
 */
public class AuthorDaoImpl implements AuthorDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_SELECT_ALL_AUTHORS
            = "select * from authors";

    private static final String SQL_SELECT_AUTHOR
            = "select * from authors where author_id = ?";

    private static final String SQL_ADD_AUTHOR
            = "insert into authors (first_name, last_name, author_title"
            + ") values (?, ?, ?)";

    private static final String SQL_DELETE_AUTHOR
            = "delete from authors where author_id = ?";

    private static final String SQL_UPDATE_AUTHOR
            = "update authors set first_name = ?, last_name = ?, author_title = ? "
            + "where author_id = ?";

    @Override
    public List<Author> getAllAuthors() {
        return jdbcTemplate.query(SQL_SELECT_ALL_AUTHORS, new AuthorDaoImpl.AuthorMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addAuthor(Author author) {

        jdbcTemplate.update(SQL_ADD_AUTHOR,
                author.getFirstName(),
                author.getLastName(),
                author.getTitle());

        int authorID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class
        );

        author.setAuthorID(authorID);
    }

    @Override
    public void deleteAuthor(int authorID) {
        jdbcTemplate.update(SQL_DELETE_AUTHOR, authorID);
    }

    @Override
    public void updateAuthor(Author author) {
        jdbcTemplate.update(SQL_UPDATE_AUTHOR,
                author.getFirstName(),
                author.getLastName(),
                author.getTitle(),
                author.getAuthorID());
    }

    @Override
    public Author getAuthorById(int authorID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_AUTHOR,
                    new AuthorDaoImpl.AuthorMapper(), authorID);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given post id - we just want to
            // return null in this case
            return null;
        }
    }

    private static final class AuthorMapper implements RowMapper<Author> {

        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = new Author();
            author.setFirstName(rs.getString("first_name"));
            author.setLastName(rs.getString("last_name"));
            author.setTitle(rs.getString("author_title"));
            author.setAuthorID(rs.getInt("author_id"));
            return author;
        }
    }

}
