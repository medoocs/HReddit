package com.example.hreddit.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class ForumStats implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String username;
    private int idForuma;
    private int upvote;
    private int downvote;

    public ForumStats(String username, int idForuma, int upvote, int downvote) {
        this.username = username;
        this.idForuma = idForuma;
        this.upvote = upvote;
        this.downvote = downvote;

    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdForuma() {
        return idForuma;
    }

    public void setIdForuma(int idForuma) {
        this.idForuma = idForuma;
    }

    public int getUpvote() {
        return upvote;
    }

    public void setUpvote(int upvote) {
        this.upvote = upvote;
    }

    public int getDownvote() {
        return downvote;
    }

    public void setDownvote(int downvote) {
        this.downvote = downvote;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", idForuma='" + idForuma + '\'' +
                ", upvote='" + upvote + '\'' +
                ", downvote='" + downvote + '\'' +
                '}';
    }
}