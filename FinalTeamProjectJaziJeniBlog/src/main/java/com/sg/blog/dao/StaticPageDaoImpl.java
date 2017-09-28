/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.dao;

import com.sg.blog.model.Author;
import com.sg.blog.model.StaticPage;
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
 * @static n0252282
 */
public class StaticPageDaoImpl implements StaticPageDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_SELECT_ALL_STATIC
            = "select * from static s join authors a on s.author_id = a.author_id";

    private static final String SQL_SELECT_STATIC
            = "select * from static s join authors a on s.author_id = a.author_id where static_id = ?";

    private static final String SQL_ADD_STATIC
            = "insert into static (static_title, static_content, author_id"
            + ") values (?, ?, ?)";

    private static final String SQL_DELETE_STATIC
            = "delete from static where static_id = ?";

    private static final String SQL_UPDATE_STATIC
            = "update static set static_title = ?, static_content = ? "
            + ", author_id = ? where static_id = ?";

    @Override
    public List<StaticPage> getAllStaticPages() {
        return jdbcTemplate.query(SQL_SELECT_ALL_STATIC,
                new StaticPageDaoImpl.StaticMapper());
    }

    @Override
    public void deleteStaticPage(int staticPageID) {
        jdbcTemplate.update(SQL_DELETE_STATIC, staticPageID);
    }

    @Override
    public void updateStaticPage(StaticPage staticPage) {
        jdbcTemplate.update(SQL_UPDATE_STATIC,
                staticPage.getTitle(),
                staticPage.getContent(),
                staticPage.getAuthor().getAuthorID(),
                staticPage.getStaticID());
    }

    @Override
    public StaticPage getStaticPageById(int staticPageID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_STATIC,
                    new StaticMapper(),
                    staticPageID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addStaticPage(StaticPage staticPage) {

        jdbcTemplate.update(SQL_ADD_STATIC,
                staticPage.getTitle(),
                staticPage.getContent(),
                staticPage.getAuthor().getAuthorID());

        int staticID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class
        );

        staticPage.setStaticID(staticID);
    }

    private static final class StaticMapper implements RowMapper<StaticPage> {

        @Override
        public StaticPage mapRow(ResultSet rs, int rowNum) throws SQLException {
            StaticPage staticpage = new StaticPage();
            staticpage.setTitle(rs.getString("static_title"));
            staticpage.setContent(rs.getString("static_content"));
            staticpage.setStaticID(rs.getInt("static_id"));

            Author author = new Author();
            author.setFirstName(rs.getString("first_name"));
            author.setLastName(rs.getString("last_name"));
            author.setTitle(rs.getString("author_title"));
            author.setAuthorID(rs.getInt("author_id"));
            staticpage.setAuthor(author);
            return staticpage;
        }
    }
}
