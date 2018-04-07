package com.example.john.particiaption10sql2018;

/**
 * Created by John on 4/2/2018.
 */

//The Comment class//
public class Comment {
    private long id;
    private String comment;

    //method to get Id value//
    public long getId() {
        return id;
    }

    //method to set Id value//
    public void setId(long id) {
        this.id = id;
    }

    //method to get comment value//
    public String getComment() {
        return comment;
    }

    //method to set the comment value//
    public void setComment(String comment) {
        this.comment = comment;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return comment;
    }
}
