package com.sg.picturealbum.dao;

import com.sg.picturealbum.model.Author;
import com.sg.picturealbum.model.Blog;
import com.sg.picturealbum.model.Category;
import com.sg.picturealbum.model.Item;
import com.sg.picturealbum.model.Post;
import com.sg.picturealbum.model.StaticPage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
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
            = "select * from blogs";

    private static final String SQL_INSERT_POST
            = "insert into posts "
            + "(title, content, hashtags, category_id, author_id, item_id, pending_delete, pending_edit) "
            + "values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_POST
            = "delete from posts where post_id = ?";
    private static final String SQL_SELECT_POST
            = "select * from posts p join categories c on c.category_id = p.category_id "
            + "join authors a on p.author_id = a.author_id "
            + "join items i on p.item_id = i.item_id "
            + "where post_id = ?";
    private static final String SQL_UPDATE_POST
            = "update posts set "
            + "title = ?, content = ?, hashtags = ?, category_id = ?, author_id = ?, item_id = ?, "
            + "pending_delete = ?, pending_edit = ? "
            + "where post_id = ?";
    private static final String SQL_SELECT_ALL_FROM_POSTS
            = "select * from posts";

    private static final String SQL_SELECT_ALL_POSTS
            = "select * from posts p join categories c on c.category_id = p.category_id "
            + "join authors a on p.author_id = a.author_id "
            + "join items i on p.item_id = i.item_id";
    private static final String SQL_SELECT_ALL_CATEGORIES
            = "select * from categories";
    private static final String SQL_SELECT_ALL_AUTHORS
            = "select * from authors";
    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from items";
    private static final String SQL_SELECT_ALL_STATIC
            = "select * from static s join authors a on s.author_id = a.author_id";

    private static final String SQL_SEARCH_BY_HASHTAG
            //= "select * from posts where hashtags like ?" ;
            = "select * from posts p "
            + "join categories c on c.category_id = p.category_id "
            + "join authors a on p.author_id = a.author_id "
            + "join items i on p.item_id = i.item_id "
            + "where hashtags like ?";

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
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Post addPost(Post post) {
        jdbcTemplate.update(SQL_INSERT_POST,
                post.getTitle(),
                post.getContent(),
                post.getHashtags(),
                post.getCategory(),
                post.getAuthor(),
                post.getItem(),
                post.getPendingDelete(),
                post.getPendingEdit());

        // query the database for the id that was just assigned to the new
        // row in the database
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        // set the new id value on the post object and return it
        post.setPostID(newId);
        return post;
    }

    @Override
    public void removePost(int postId) {
        jdbcTemplate.update(SQL_DELETE_POST, postId);
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
    public List<Post> getAllPosts() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POSTS, new PostMapper());
    }

    @Override
    public List<Post> getAllPostsMatchingHashTag(String hashTag) {
        String hashTagToSearch = "%" + hashTag + "%";

        List<Post> postList = jdbcTemplate.query(SQL_SEARCH_BY_HASHTAG,
                new PostMapper(), hashTagToSearch);

        return postList;
    }

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CATEGORIES, new BlogListDaoDbImpl.CategoryMapper());
    }

    @Override
    public List<Author> getAllAuthors() {
        return jdbcTemplate.query(SQL_SELECT_ALL_AUTHORS, new BlogListDaoDbImpl.AuthorMapper());
    }

    @Override
    public List<Item> getAllItems() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS, new BlogListDaoDbImpl.ItemMapper());
    }

    @Override
    public List<StaticPage> getAllStatic() {
        return jdbcTemplate.query(SQL_SELECT_ALL_STATIC, new BlogListDaoDbImpl.StaticMapper());
    }

    @Override
    public Post getPostById(int postId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_POST,
                    new PostMapper(), postId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given post id - we just want to
            // return null in this case
            return null;
        }
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

    @Override
    public List<Post> searchPosts(Map<SearchTerm, String> criteria) {
        if (criteria.isEmpty()) {
            return getAllPosts();
        } else {
            StringBuilder sQuery
                    = new StringBuilder("select * from posts where ");
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
                    new PostMapper(),
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

    private static final class CategoryMapper implements RowMapper<Category> {

        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category category = new Category();
            category.setCategoryID(rs.getInt("category_id"));
            category.setName(rs.getString("category_name"));
            category.setDescription(rs.getString("category_description"));
            return category;
        }
    }

    private static final class AuthorMapper implements RowMapper<Author> {

        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = new Author();
            author.setAuthorID(rs.getInt("author_id"));
            author.setFirstName(rs.getString("first_name"));
            author.setLastName(rs.getString("last_name"));
            author.setTitle(rs.getString("title"));
            return author;
        }
    }

    private static final class ItemMapper implements RowMapper<Item> {

        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            Item item = new Item();
            item.setItemID(rs.getInt("item_id"));
            item.setItemName(rs.getString("item_name"));
            item.setItemDescription(rs.getString("item_description"));
            item.setItemPrice(rs.getBigDecimal("item_price"));
            item.setItemQuantity(rs.getInt("item_quantity"));
            return item;
        }
    }

    private static final class StaticMapper implements RowMapper<StaticPage> {

        public StaticPage mapRow(ResultSet rs, int rowNum) throws SQLException {
            StaticPage staticpage = new StaticPage();
            staticpage.setStaticID(rs.getInt("item_id"));
            staticpage.setContent(rs.getString("item_name"));
            staticpage.setTitle(rs.getString("item_description"));

            Author author = new Author();
            author.setAuthorID(rs.getInt("author_id"));
            author.setFirstName(rs.getString("first_name"));
            author.setLastName(rs.getString("last_name"));
            author.setTitle(rs.getString("title"));

            staticpage.setAuthor(author);
            return staticpage;
        }
    }
}
