package com.example.hreddit.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Forum implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String naslov;
    private String tekst;
    private String kategorija;
    private String user;
    private String datum;
    private int upvote;
    private int downvote;
    private int brojKomentara;

    public Forum(String naslov, String tekst, String kategorija, String user, String datum, int upvote, int downvote, int brojKomentara) {
        this.naslov = naslov;
        this.tekst = tekst;
        this.kategorija = kategorija;
        this.user = user;
        this.datum = datum;
        this.upvote = upvote;
        this.downvote = downvote;
        this.brojKomentara = brojKomentara;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
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

    public int getBrojKomentara() {
        return brojKomentara;
    }

    public void setBrojKomentara(int brojKomentara) {
        this.brojKomentara = brojKomentara;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", naslov='" + naslov + '\'' +
                ", tekst='" + tekst + '\'' +
                ", user='" + user + '\'' +
                ", kategorija='" + kategorija + '\'' +
                ", datum='" + datum + '\'' +
                ", upvote='" + upvote + '\'' +
                ", downvote='" + downvote + '\'' +
                ", brojKomentara='" + brojKomentara + '\'' +
                '}';
    }
}