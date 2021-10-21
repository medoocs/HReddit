package com.example.hreddit.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hreddit.Model.Forum;
import com.example.hreddit.Model.ForumStats;
import com.example.hreddit.Model.KomentarStats;


@Dao
public interface KomentarStatsDao {

    @Query("SELECT * FROM KomentarStats WHERE idKomentara = :idKomentara AND username = :username")
    KomentarStats getUserStatsForKomentar(String username, int idKomentara);

    @Insert
    void insert(KomentarStats forum);

    @Update
    void update(KomentarStats forum);

    @Delete
    void delete(KomentarStats forum);

    //Delete one item by id
    @Query("DELETE FROM KomentarStats WHERE id = :id")
    void deleteByForumId(int id);

    //Delete All
    @Query("DELETE FROM KomentarStats")
    void deleteAll();

}
