package com.example.hreddit.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String userName;
    private String password;
    private String email;
    private String aboutMe;
    private int forumScore;
    private int commentScore;
    private int followers;
    private byte[] image;
    private String following;
    private String notificationForums;

    public User(String userName, String password, String email, String aboutMe, int forumScore, int commentScore, int followers, byte[] image, String following, String notificationForums) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.aboutMe = aboutMe;
        this.forumScore = forumScore;
        this.commentScore = commentScore;
        this.followers = followers;
        this.image = image;
        this.following = following;
        this.notificationForums = notificationForums;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public int getForumScore() {
        return forumScore;
    }

    public void setForumScore(int forumScore) {
        this.forumScore = forumScore;
    }

    public int getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(int commentScore) {
        this.commentScore = commentScore;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getNotificationForums() {
        return notificationForums;
    }

    public void setNotificationForums(String notificationForums) {
        this.notificationForums = notificationForums;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}