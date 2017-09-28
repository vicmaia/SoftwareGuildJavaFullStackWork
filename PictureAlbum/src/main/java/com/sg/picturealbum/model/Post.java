/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.picturealbum.model;

import java.util.Objects;

/**
 *
 * @author n0252282
 */
public class Post {

    private int postID;
    private String title;
    private String content;
    private String hashtags;
    private Category category;
    private Author author;
    private Item item;
    private int pendingDelete;
    private int pendingEdit;

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

    public String getHashtags() {
        return hashtags;
    }

    public void setHashtags(String hashtags) {
        this.hashtags = hashtags;
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

    public int getPendingDelete() {
        return pendingDelete;
    }

    public void setPendingDelete(int pendingDelete) {
        this.pendingDelete = pendingDelete;
    }

    public int getPendingEdit() {
        return pendingEdit;
    }

    public void setPendingEdit(int pendingEdit) {
        this.pendingEdit = pendingEdit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.postID;
        hash = 59 * hash + Objects.hashCode(this.title);
        hash = 59 * hash + Objects.hashCode(this.content);
        hash = 59 * hash + Objects.hashCode(this.hashtags);
        hash = 59 * hash + Objects.hashCode(this.category);
        hash = 59 * hash + Objects.hashCode(this.author);
        hash = 59 * hash + Objects.hashCode(this.item);
        hash = 59 * hash + this.pendingDelete;
        hash = 59 * hash + this.pendingEdit;
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
        if (this.pendingDelete != other.pendingDelete) {
            return false;
        }
        if (this.pendingEdit != other.pendingEdit) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.hashtags, other.hashtags)) {
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
