/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.picturealbum.dao;


import com.sg.picturealbum.model.Author;
import com.sg.picturealbum.model.Category;
import com.sg.picturealbum.model.Item;
import com.sg.picturealbum.model.Post;
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
 * @post n0252282
 */
public class PostDaoImpl implements PostDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_SELECT_ALL_POSTS
            = "select * from posts p join categories c on c.category_id = p.category_id "
            + "join authors a on p.author_id = a.author_id "
            + "join items i on p.item_id = i.item_id";

    private static final String SQL_SELECT_POST
            = "select * from posts p join categories c on c.category_id = p.category_id "
            + "join authors a on p.author_id = a.author_id "
            + "join items i on p.item_id = i.item_id where post_id = ?";

    private static final String SQL_ADD_POST
            = "insert into posts (title, content, hashtags, category_id "
            + ", author_id, item_id, pending_delete, pending_edit) values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_POST
            = "delete from posts where post_id = ?";

    private static final String SQL_UPDATE_POST
            = "update posts set title = ?, content = ?, hashtags = ? "
            + ", category_id = ?, author_id = ?, item_id = ? "
            + ", pending_delete = ?, pending_edit = ? where post_id = ?";

    @Override
    public List<Post> getAllPosts() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POSTS, new PostDaoImpl.PostMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPost(Post post) {

        jdbcTemplate.update(SQL_ADD_POST,
                post.getTitle(),
                post.getContent(),
                post.getHashtags(),
                post.getCategory().getCategoryID(),
                post.getAuthor().getAuthorID(),
                post.getItem().getItemID(),
                post.getPendingDelete(),
                post.getPendingEdit());

        int postID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class
        );

        post.setPostID(postID);
    }

    @Override
    public void deletePost(int postID) {
        jdbcTemplate.update(SQL_DELETE_POST, postID);
    }

    @Override
    public void updatePost(Post post) {
        jdbcTemplate.update(SQL_UPDATE_POST,
                post.getTitle(),
                post.getContent(),
                post.getHashtags(),
                post.getCategory().getCategoryID(),
                post.getAuthor().getAuthorID(),
                post.getItem().getItemID(),
                post.getPendingDelete(),
                post.getPendingEdit(),
                post.getPostID());
    }

    @Override
    public Post getPostById(int postID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_POST,
                    new PostDaoImpl.PostMapper(), postID);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given post id - we just want to
            // return null in this case
            return null;
        }
    }

    private static final class PostMapper implements RowMapper<Post> {

        @Override
        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            Post post = new Post();
            post.setPostID(rs.getInt("post_id"));
            post.setTitle(rs.getString("title"));
            post.setContent(rs.getString("content"));
            post.setHashtags(rs.getString("hashtags"));

            Category category = new Category();
            category.setCategoryID(rs.getInt("category_id"));
            category.setName(rs.getString("category_name"));
            category.setDescription(rs.getString("category_description"));

            post.setCategory(category);

            Author author = new Author();
            author.setAuthorID(rs.getInt("author_id"));
            author.setFirstName(rs.getString("first_name"));
            author.setLastName(rs.getString("last_name"));
            author.setTitle(rs.getString("author_title"));

            post.setAuthor(author);

            Item item = new Item();
            item.setItemID(rs.getInt("item_id"));
            item.setItemName(rs.getString("item_name"));
            item.setItemDescription(rs.getString("item_description"));
            item.setItemPrice(rs.getBigDecimal("item_price"));
            item.setItemQuantity(rs.getInt("item_quantity"));

            post.setItem(item);

            post.setPendingDelete(rs.getInt("pending_delete"));
            post.setPendingEdit(rs.getInt("pending_edit"));

            return post;
        }
    }

}
