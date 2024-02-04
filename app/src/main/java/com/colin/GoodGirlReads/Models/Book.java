package com.colin.GoodGirlReads.Models;

import com.google.firebase.database.Exclude;

import java.util.List;


public class Book {
    public String ISBN;
    public String key;
    public String title;

    public int pages;
    public String trope;

    public List<String> authors;
    public String publishedDate;
    public String categories;
    public String imageURL;

    public Book() {

    }

    public Book(String ISBN, String title, int pages, String category, String trope, List<String> authors) {
        this.ISBN = ISBN;
        this.title = title;
        this.pages = pages;
        this.categories = category;
        this.trope = trope;
        this.authors = authors;
    }

}
