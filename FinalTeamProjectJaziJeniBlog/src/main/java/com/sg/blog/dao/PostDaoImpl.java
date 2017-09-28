/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.dao;

import com.sg.blog.model.Author;
import com.sg.blog.model.Category;
import com.sg.blog.model.Item;
import com.sg.blog.model.Post;
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

    private static final String SQL_SELECT_ALL_APPROVED_POSTS
            = "select * from posts p "
            + "join categories c on c.category_id = p.category_id "
            + "join authors a on p.author_id = a.author_id "
            + "join items i on p.item_id = i.item_id "
            + "where pending_edit = 0 and pending_delete = 0";

    private static final String SQL_SELECT_POST
            = "select * from posts p join categories c on c.category_id = p.category_id "
            + "join authors a on p.author_id = a.author_id "
            + "join items i on p.item_id = i.item_id where post_id = ?";

    private static final String SQL_ADD_POST
            = "insert into posts (title, content, hashtags, category_id "
            + ", author_id, item_id, pending_delete, pending_edit, oldpost_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_POST
            = "delete from posts where post_id = ?";

    private static final String SQL_UPDATE_POST
            = "update posts set title = ?, content = ?, hashtags = ? "
            + ", category_id = ?, author_id = ?, item_id = ? "
            + ", pending_delete = ?, pending_edit = ?, oldpost_id = ? where post_id = ?";

    private static final String SQL_UPDATE_PENDING_EDIT
            = "update posts set pending_edit = ? where post_id = ?";

    private static final String SQL_SEARCH_BY_HASHTAG
            = "select * from posts p "
            + "join categories c on c.category_id = p.category_id "
            + "join authors a on p.author_id = a.author_id "
            + "join items i on p.item_id = i.item_id "
            + "where hashtags like ?";

    private static final String SQL_SEARCH_BY_CATEGORY
            = "select * from posts p "
            + "join categories c on c.category_id = p.category_id "
            + "join authors a on p.author_id = a.author_id "
            + "join items i on p.item_id = i.item_id "
            + "where p.category_id = ?";

    private static final String SQL_SELECT_POST_BY_ITEMID
            = "select * from posts p "
            + "join categories c on c.category_id = p.category_id "
            + "join authors a on p.author_id = a.author_id "
            + "join items i on p.item_id = i.item_id "
            + "where i.item_id = ?";

    @Override
    public List<Post> getAllPosts() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POSTS, new PostDaoImpl.PostMapper());
    }

    @Override
    public List<Post> getAllApprovedPosts() {
        return jdbcTemplate.query(SQL_SELECT_ALL_APPROVED_POSTS, new PostDaoImpl.PostMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Post addPost(Post post) {

        jdbcTemplate.update(SQL_ADD_POST,
                post.getTitle(),
                post.getContent(),
                post.getHashtags(),
                post.getCategory().getCategoryID(),
                post.getAuthor().getAuthorID(),
                post.getItem().getItemID(),
                post.getPendingDelete(),
                post.getPendingEdit(),
                post.getOldPostID());

        int postID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class
        );

        post.setPostID(postID);
        return post;
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
                post.getOldPostID(),
                post.getPostID());
    }

    @Override
    public void updatePendingEdit(Post post) {
        jdbcTemplate.update(SQL_UPDATE_PENDING_EDIT,
                post.getPendingEdit(),
                post.getPostID());
    }

    @Override
    public Post getPostById(int postID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_POST,
                    new PostDaoImpl.PostMapper(), postID);
        } catch (EmptyResultDataAccessException ex) {

            return null;
        }
    }

    @Override
    public List<Post> getPostByItemId(int itemID) {
        try {
            List<Post> postList = jdbcTemplate.query(SQL_SELECT_POST_BY_ITEMID,
                    new PostDaoImpl.PostMapper(), itemID);
            return postList;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Post> getPostByCategoryId(int categoryID) {
        try {
            List<Post> postList = jdbcTemplate.query(SQL_SEARCH_BY_CATEGORY,
                    new PostDaoImpl.PostMapper(), categoryID);
            return postList;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Post> getAllPostsMatchingHashTag(String hashTag) {
        String hashTagToSearch = "%" + hashTag + "%";

        List<Post> postList = jdbcTemplate.query(SQL_SEARCH_BY_HASHTAG,
                new PostDaoImpl.PostMapper(), hashTagToSearch);

        return postList;
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
            post.setOldPostID(rs.getInt("oldpost_id"));

            return post;
        }
    }

}
