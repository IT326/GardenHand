package com.example.gardenhand;

public class Comment {
    String authorID;
    String message;

    public Comment() {
        //pull data from database
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return authorID;
    }
}
