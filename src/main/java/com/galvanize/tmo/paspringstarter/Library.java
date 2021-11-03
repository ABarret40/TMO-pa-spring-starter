package com.galvanize.tmo.paspringstarter;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Library {

    @JsonProperty("books")
    private ArrayList<Book> books;

    public Library(ArrayList<Book> books) {
        this.books = books;
    }
}
