package com.example.hreddit.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hreddit.Model.Forum;

import java.util.List;


@Dao
public interface ForumDao {
    /*@Query("SELECT * FROM User where email= :mail and password= :password")
    User getUser(String mail, String password);

    @Query("SELECT * FROM User where username= :username")
    User getUsername(String username);

    @Query("SELECT * FROM User where email= :mail")
    User getEmail(String mail);*/

    //Get all items

    @Query("SELECT * FROM Forum WHERE naslov LIKE :search ORDER BY CASE WHEN :sort = 'asc' THEN upvote END asc, CASE WHEN :sort = 'desc' THEN upvote END desc")
    LiveData<List<Forum>> getForums(String sort, String search);

    @Query("SELECT * FROM Forum where naslov LIKE :search AND kategorija IN (:kategorija) ORDER BY CASE WHEN :sort = 'asc' THEN upvote END asc, CASE WHEN :sort = 'desc' THEN upvote END desc")
    LiveData<List<Forum>> getCatForums(String[] kategorija, String sort, String search);

    @Query("SELECT * FROM Forum where user = :username ORDER BY datum desc")
    LiveData<List<Forum>> getForumsProfile(String username);

    @Query("SELECT * FROM Forum where id IN (:id) ORDER BY datum desc")
    LiveData<List<Forum>> getForumsById(int[] id);

    @Query("SELECT * FROM Forum WHERE id = :id")
    Forum getForumById(int id);

    @Query("SELECT * FROM Forum WHERE naslov = :naslov AND tekst = :tekst AND user = :username AND datum = :datum")
    Forum getForumByAll(String naslov, String tekst, String username, String datum);

    @Insert
    void insert(Forum forum);

    @Update
    void update(Forum forum);

    @Delete
    void delete(Forum forum);

    //Delete one item by id
    @Query("DELETE FROM Forum WHERE id = :id")
    void deleteByForumId(int id);

    //Delete All
    @Query("DELETE FROM Forum")
    void deleteAll();

}
