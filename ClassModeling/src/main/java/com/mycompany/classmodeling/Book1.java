/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classmodeling;

/**
 *
 * @author n0252282
 */
public class Book1 {

    private String bookOutline;
    private String[] chapters;
    private boolean[] chapterCompleteIndicator;
    private boolean bookCompleteIndicator;
    private String[] editorNotes;
    private String foreword;

    public Book1() {

    }

    public Book1(String[] chapters, boolean[] chapterCompleteIndicator) {
        this.chapters = chapters;
        this.chapterCompleteIndicator = chapterCompleteIndicator;
    }

    public String getBookOutline() {
        return bookOutline;
    }

    public void setBookOutline(String bookOutline) {
        this.bookOutline = bookOutline;
    }

    public String[] getChapters() {
        return chapters;
    }

    public void setChapters(String[] chapters) {
        this.chapters = chapters;
    }

    public boolean[] getChapterCompleteIndicator() {
        return chapterCompleteIndicator;
    }

    public void setChapterCompleteIndicator(boolean[] chapterCompleteIndicator) {
        this.chapterCompleteIndicator = chapterCompleteIndicator;
    }

    public boolean isBookCompleteIndicator() {
        return bookCompleteIndicator;
    }

    public void setBookCompleteIndicator(boolean bookCompleteIndicator) {
        this.bookCompleteIndicator = bookCompleteIndicator;
    }

    public String[] getEditorNotes() {
        return editorNotes;
    }

    public void setEditorNotes(String[] editorNotes) {
        this.editorNotes = editorNotes;
    }

    public String getForeword() {
        return foreword;
    }

    public void setForeword(String foreword) {
        this.foreword = foreword;
    }

}
