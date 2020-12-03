package com.example.gardenhand;

public class Comment {
    String authorID;
    String message;

    public Comment(String author, String msg) {
        authorID = author;
        message = msg;

        //Store comment in database
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return authorID;
    }
}
