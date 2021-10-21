package com.example.hreddit.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity
public class Komentar implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private int idForuma;
    private String tekst;
    private String user;
    private String datum;
    private int upvote;
    private int downvote;


    public Komentar(int idForuma, String tekst, String user, String datum, int upvote, int downvote) {
        this.idForuma = idForuma;
        this.tekst = tekst;
        this.user = user;
        this.datum = datum;
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

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
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

    public int getIdForuma() {
        return idForuma;
    }

    public void setIdForuma(int idForuma) {
        this.idForuma = idForuma;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", tekst='" + tekst + '\'' +
                ", user='" + user + '\'' +
                ", datum='" + datum + '\'' +
                ", upvote='" + upvote + '\'' +
                ", downvote='" + downvote + '\'' +
                '}';
    }
}