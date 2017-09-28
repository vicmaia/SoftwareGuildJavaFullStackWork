/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.dao;

import com.sg.blog.model.Author;
import com.sg.blog.model.StaticPage;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface StaticPageDao {

    public void addStaticPage(StaticPage staticPage);

    public void deleteStaticPage(int staticPageID);

    public void updateStaticPage(StaticPage staticPage);

    public StaticPage getStaticPageById(int staticPageID);

    public List<StaticPage> getAllStaticPages();
}
