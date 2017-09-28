package com.sg.blog.dao;

import com.sg.blog.model.Blog;
import com.sg.blog.model.Post;
import com.sg.blog.model.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import static org.springframework.http.RequestEntity.post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class BlogListDaoDbImpl implements BlogListDao {

    private static final String SQL_INSERT_BLOG
            = "insert into blogs "
            + "(first_name, last_name, company, phone, email) "
            + "values (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_BLOG
            = "delete from blogs where blog_id = ?";
    private static final String SQL_SELECT_BLOG
            = "select * from blogs where blog_id = ?";
    private static final String SQL_UPDATE_BLOG
            = "update blogs set "
            + "first_name = ?, last_name = ?, company = ?, "
            + "phone = ?, email = ? "
            + "where blog_id = ?";
    private static final String SQL_SELECT_ALL_BLOGS
            = "select * from contacts";
    private static final String SQL_SELECT_ALL_POSTS
            = "select * from posts p join categories c on c.category_id = p.category_id";
    private static final String SQL_SELECT_ALL_CATEGORIES
            = "select * from categories";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Blog addBlog(Blog blog) {
        jdbcTemplate.update(SQL_INSERT_BLOG,
                blog.getFirstName(),
                blog.getLastName(),
                blog.getCompany(),
                blog.getPhone(),
                blog.getEmail());

        // query the database for the id that was just assigned to the new
        // row in the database
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        // set the new id value on the blog object and return it
        blog.setBlogId(newId);
        return blog;
    }

    @Override
    public void removeBlog(long blogId) {
        jdbcTemplate.update(SQL_DELETE_BLOG, blogId);
    }

    @Override
    public void updateBlog(Blog blog) {
        jdbcTemplate.update(SQL_UPDATE_BLOG,
                blog.getFirstName(),
                blog.getLastName(),
                blog.getCompany(),
                blog.getPhone(),
                blog.getEmail(),
                blog.getBlogId());
    }

    @Override
    public List<Blog> getAllBlogs() {
        return jdbcTemplate.query(SQL_SELECT_ALL_BLOGS, new BlogMapper());
    }

    @Override
    public List<Post> getAllPosts() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POSTS, new PostMapper());
    }

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CATEGORIES, new CategoryMapper());
    }

    @Override
    public Blog getBlogById(long blogId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BLOG,
                    new BlogMapper(), blogId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given blog id - we just want to
            // return null in this case
            return null;
        }
    }

    @Override
    public List<Blog> searchBlogs(Map<SearchTerm, String> criteria) {
        if (criteria.isEmpty()) {
            return getAllBlogs();
        } else {
            StringBuilder sQuery
                    = new StringBuilder("select * from blogs where ");
            // build the where clause
            int numParams = criteria.size();
            int paramPosition = 0;
            // we'll put the positional parameters into an array, the order of the
            // parameters will match the order in which we get the search criteria
            // from the map
            String[] paramVals = new String[numParams];
            Set<SearchTerm> keySet = criteria.keySet();
            Iterator<SearchTerm> iter = keySet.iterator();
            // build up the where clause based on the key/value pairs in the map
            // build where clause and positional parameter array
            while (iter.hasNext()) {
                SearchTerm currentKey = iter.next();
                // if we are not the first one in, we must add an AND to the 
                // where clause
                if (paramPosition > 0) {
                    sQuery.append(" and ");
                }
                // now append our criteria name
                sQuery.append(currentKey);
                sQuery.append(" = ? ");
                // grab the value for this search criteria and put it into the 
                // paramVals array
                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }

            return jdbcTemplate.query(sQuery.toString(),
                    new BlogMapper(),
                    paramVals);
        }
    }

    private static final class BlogMapper implements RowMapper<Blog> {

        public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
            Blog blog = new Blog();
            blog.setBlogId(rs.getLong("contact_id"));
            blog.setFirstName(rs.getString("first_name"));
            blog.setLastName(rs.getString("last_name"));
            blog.setCompany(rs.getString("company"));
            blog.setPhone(rs.getString("phone"));
            blog.setEmail(rs.getString("email"));
            return blog;
        }
    }

    private static final class PostMapper implements RowMapper<Post> {

        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            Post post = new Post();
            post.setPostID(rs.getInt("post_id"));
            post.setTitle(rs.getString("title"));
            post.setContent(rs.getString("content"));

            Category category = new Category();
            category.setCategoryID(rs.getInt("category_id"));
            category.setName(rs.getString("category_name"));
            category.setDescription(rs.getString("category_description"));

            post.setCategory(category);

            return post;
        }
    }

    private static final class CategoryMapper implements RowMapper<Category> {

        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category category = new Category();
            category.setCategoryID(rs.getInt("category_id"));
            category.setName(rs.getString("category_name"));
            category.setDescription(rs.getString("category_description"));
            return category;
        }
    }

}
