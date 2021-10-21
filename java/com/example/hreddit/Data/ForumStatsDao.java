package com.example.hreddit.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hreddit.Model.Forum;
import com.example.hreddit.Model.ForumStats;



@Dao
public interface ForumStatsDao {

    @Query("SELECT * FROM ForumStats WHERE idForuma = :idForuma AND username = :username")
    ForumStats getUserStatsForForum(String username, int idForuma);

    @Insert
    void insert(ForumStats forum);

    @Update
    void update(ForumStats forum);

    @Delete
    void delete(ForumStats forum);

    //Delete one item by id
    @Query("DELETE FROM ForumStats WHERE id = :id")
    void deleteByForumId(int id);

    //Delete All
    @Query("DELETE FROM ForumStats")
    void deleteAll();

}
