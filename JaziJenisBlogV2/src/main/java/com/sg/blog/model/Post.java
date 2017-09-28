/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.model;

import java.util.Objects;

/**
 *
 * @author n0252282
 */
public class Post {

    private int postID;
    private String title;
    private String content;
    private Category category;
    private Author author;
    private Item item;
    private int approved;
    private int editApproved;
    private int staticPage;

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public int getEditApproved() {
        return editApproved;
    }

    public void setEditApproved(int editApproved) {
        this.editApproved = editApproved;
    }

    public int getStaticPage() {
        return staticPage;
    }

    public void setStaticPage(int staticPage) {
        this.staticPage = staticPage;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.postID;
        hash = 71 * hash + Objects.hashCode(this.title);
        hash = 71 * hash + Objects.hashCode(this.content);
        hash = 71 * hash + Objects.hashCode(this.category);
        hash = 71 * hash + Objects.hashCode(this.author);
        hash = 71 * hash + Objects.hashCode(this.item);
        hash = 71 * hash + this.approved;
        hash = 71 * hash + this.editApproved;
        hash = 71 * hash + this.staticPage;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Post other = (Post) obj;
        if (this.postID != other.postID) {
            return false;
        }
        if (this.approved != other.approved) {
            return false;
        }
        if (this.editApproved != other.editApproved) {
            return false;
        }
        if (this.staticPage != other.staticPage) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.item, other.item)) {
            return false;
        }
        return true;
    }

}
