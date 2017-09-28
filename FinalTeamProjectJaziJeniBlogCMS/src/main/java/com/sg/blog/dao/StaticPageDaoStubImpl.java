/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.dao;

import com.sg.blog.model.Author;
import com.sg.blog.model.StaticPage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.hibernate.validator.internal.util.CollectionHelper.newHashMap;

/**
 *
 * @author n0001123
 */
public class StaticPageDaoStubImpl implements StaticPageDao {

    Map<Integer, StaticPage> spList = newHashMap();

    public StaticPageDaoStubImpl() {
        StaticPage staticPage = new StaticPage();
        staticPage.setTitle("title");
        staticPage.setContent("static_content");
        staticPage.setStaticID(1);
        Author author = new Author();
        author.setFirstName("first_name");
        author.setLastName("last_name");
        author.setTitle("author_title");
        staticPage.setAuthor(author);
        spList.put(staticPage.getStaticID(), staticPage);
    }

    @Override
    public void addStaticPage(StaticPage staticPage) {
        StaticPage sp = new StaticPage();
        sp.setTitle("title");
        sp.setContent("static_content");
        sp.setStaticID(2);
        Author author = new Author();
        author.setFirstName("first_name");
        author.setLastName("last_name");
        author.setTitle("author_title");
        author.setAuthorID(25);
        sp.setAuthor(author);
        spList.put(sp.getStaticID(), staticPage);
        spList.put(staticPage.getStaticID(), staticPage);
    }

    @Override
    public void deleteStaticPage(int staticPageID) {
        spList.remove(staticPageID);
    }

    @Override
    public void updateStaticPage(StaticPage staticPage) {
        deleteStaticPage(staticPage.getStaticID());
        addStaticPage(staticPage);
    }

    @Override
    public StaticPage getStaticPageById(int staticPageID) {
        StaticPage staticPage = spList.get(staticPageID);
        return staticPage;
    }

    @Override
    public List<StaticPage> getAllStaticPages() {
        return new ArrayList<>(spList.values());
    }
}
