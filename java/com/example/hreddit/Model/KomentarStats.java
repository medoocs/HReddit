package com.example.hreddit.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class KomentarStats implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String username;
    private int idKomentara;
    private int upvote;
    private int downvote;

    public KomentarStats(String username, int idKomentara, int upvote, int downvote) {
        this.username = username;
        this.idKomentara = idKomentara;
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

    public int getIdKomentara() {
        return idKomentara;
    }

    public void setIdKomentara(int idKomentara) {
        this.idKomentara = idKomentara;
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
                ", idKomentara='" + idKomentara + '\'' +
                ", upvote='" + upvote + '\'' +
                ", downvote='" + downvote + '\'' +
                '}';
    }
}