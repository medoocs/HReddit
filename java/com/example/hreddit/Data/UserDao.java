package com.example.hreddit.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hreddit.Model.User;


@Dao
public interface UserDao {
    @Query("SELECT * FROM User where email= :mail and password= :password")
    User getUser(String mail, String password);

    @Query("SELECT * FROM User where username= :username")
    User getUsername(String username);

    @Query("SELECT * FROM User where email= :mail")
    User getEmail(String mail);

    @Query("SELECT * FROM User where email= :email")
    User getPass(String email);

    @Query("SELECT * FROM User where id= :id")
    User getUserById(int id);

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    //Delete All
    @Query("DELETE FROM User")
    void deleteAll();
}
