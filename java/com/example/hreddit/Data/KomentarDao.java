package com.example.hreddit.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hreddit.Model.Komentar;

import java.util.List;


@Dao
public interface KomentarDao {
    /*@Query("SELECT * FROM User where email= :mail and password= :password")
    User getUser(String mail, String password);

    @Query("SELECT * FROM User where username= :username")
    User getUsername(String username);

    @Query("SELECT * FROM User where email= :mail")
    User getEmail(String mail);*/

    @Query("SELECT * FROM Komentar where idForuma= :idForuma")
    LiveData<List<Komentar>> getComments(int idForuma);

    @Query("SELECT * FROM Komentar where user= :username ORDER BY datum asc")
    LiveData<List<Komentar>> getCommentsProfile(String username);

    @Insert
    void insert(Komentar komentar);

    @Update
    void update(Komentar komentar);

    @Delete
    void delete(Komentar komentar);

    //Delete All
    @Query("DELETE FROM Komentar")
    void deleteAll();
}
