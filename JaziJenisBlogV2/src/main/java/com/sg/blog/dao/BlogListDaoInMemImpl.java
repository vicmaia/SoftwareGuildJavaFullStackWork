package com.sg.blog.dao;

import com.sg.blog.model.Blog;
import com.sg.blog.model.Category;
import com.sg.blog.model.Post;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author ward
 */
public class BlogListDaoInMemImpl implements BlogListDao {
// holds all of our Blog objects - simulates the database

    private Map<Long, Blog> blogMap = new HashMap<>();
    // used to assign ids to Blogs - simulates the auto increment
    // primary key for Blogs in a database
    private static long blogIdCounter = 0;

    @Override
    public Blog addBlog(Blog blog) {
        // assign the current counter values as the blogid
        blog.setBlogId(blogIdCounter);
        // increment the counter so it is ready for use for the 
        // next blog
        blogIdCounter++;
        blogMap.put(blog.getBlogId(), blog);
        return blog;
    }

    @Override
    public void removeBlog(long blogId) {
        blogMap.remove(blogId);
    }

    @Override
    public void updateBlog(Blog blog) {
        blogMap.put(blog.getBlogId(), blog);
    }

    @Override
    public List<Blog> getAllBlogs() {
        Collection<Blog> c = blogMap.values();
        return new ArrayList(c);
    }

    @Override
    public Blog getBlogById(long blogId) {
        return blogMap.get(blogId);
    }

    @Override
    public List<Blog> searchBlogs(Map<SearchTerm, String> criteria) {
        // Get all the search term values from the map
        String firstNameSearchCriteria = criteria.get(SearchTerm.FIRST_NAME);
        String lastNameSearchCriteria = criteria.get(SearchTerm.LAST_NAME);
        String companySearchCriteria = criteria.get(SearchTerm.COMPANY);
        String phoneSearchCriteria = criteria.get(SearchTerm.PHONE);
        String emailSearchCriteria = criteria.get(SearchTerm.EMAIL);

        // Declare all the predicate conditions - remember that
        // Predicate is a functional interface with one method
        // called test(T t) that returns a boolean.  Since
        // Predicate is generic, we get to specify the type of
        // object we want T to be - in our case, we want T to be
        // of type Blog.  That means the Predicates declared 
        // here will have one method: boolean test(Blog c)
        Predicate<Blog> firstNameMatchPredicate;
        Predicate<Blog> lastNameMatchPredicate;
        Predicate<Blog> companyMatchPredicate;
        Predicate<Blog> phoneMatchPredicate;
        Predicate<Blog> emailMatchPredicate;

        // Placeholder predicate - always returns true. Used for 
        // search terms that are empty - if the user didn't specify 
        // a value for one of the search terms, we must return true
        // because we are ANDing all the search terms together and 
        // our spec says that we return everything in the DAO when
        // the user leaves all the search terms blank.
        Predicate<Blog> truePredicate = (c) -> {
            return true;
        };

        // Assign values to predicates. If a given search term is empty, 
        // just assign the default truePredicate, otherwise assign the 
        // predicate that only returns true when it finds a match for the 
        // given term.
        if (firstNameSearchCriteria == null || firstNameSearchCriteria.isEmpty()) {
            firstNameMatchPredicate = truePredicate;
        } else {
            firstNameMatchPredicate = (c) -> c.getFirstName().equals(firstNameSearchCriteria);
        }

        if (lastNameSearchCriteria == null || lastNameSearchCriteria.isEmpty()) {
            lastNameMatchPredicate = truePredicate;
        } else {
            lastNameMatchPredicate = (c) -> c.getLastName().equals(lastNameSearchCriteria);
        }

        if (companySearchCriteria == null || companySearchCriteria.isEmpty()) {
            companyMatchPredicate = truePredicate;
        } else {
            companyMatchPredicate = (c) -> c.getCompany().equals(companySearchCriteria);
        }

        if (phoneSearchCriteria == null || phoneSearchCriteria.isEmpty()) {
            phoneMatchPredicate = truePredicate;
        } else {
            phoneMatchPredicate = (c) -> c.getPhone().equals(phoneSearchCriteria);
        }

        if (emailSearchCriteria == null || emailSearchCriteria.isEmpty()) {
            emailMatchPredicate = truePredicate;
        } else {
            emailMatchPredicate = (c) -> c.getEmail().equals(emailSearchCriteria);
        }

        // Return the list of Blogs that match the given criteria. 
        // To do this we just AND all the predicates together in a 
        // filter operation.
        return blogMap.values().stream()
                .filter(firstNameMatchPredicate
                        .and(lastNameMatchPredicate)
                        .and(companyMatchPredicate)
                        .and(phoneMatchPredicate)
                        .and(emailMatchPredicate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getAllPosts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> getAllCategories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
