package com.example.hreddit.Data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hreddit.Model.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository repository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public void insert(User user) {
        repository.insert(user);
    }

    public void update(User user) {
        repository.update(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public User getUser(String email, String password) {
        return repository.getUser(email, password);
    }

    public User getUsername(String username) {
        return repository.getUsername(username);
    }

    public User getEmail(String email) {
        return repository.getEmail(email);
    }

    public User getPass(String email) {
        return repository.getPass(email);
    }

}
