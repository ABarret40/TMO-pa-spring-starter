package com.galvanize.tmo.paspringstarter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

    @JsonProperty("id")
    private int id;
    @JsonProperty("author")
    private String author;
    @JsonProperty("title")
    private String title;
    @JsonProperty("yearPublished")
    private int yearPublished;

    public Book() {
    }

    public Book(int id, String author, String title, int yearPublished) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.yearPublished = yearPublished;
    }

    public Book( String author, String title, int yearPublished) {
        this.author = author;
        this.title = title;
        this.yearPublished = yearPublished;
    }

    public boolean sameBookAs(Book book) {
        if(this.author == book.getAuthor() && this.title == book.getTitle() && this.yearPublished == book.getYearPublished()) {
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", yearPublished=" + yearPublished +
                '}';
    }
}
