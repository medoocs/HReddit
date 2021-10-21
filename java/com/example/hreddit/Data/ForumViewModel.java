package com.example.hreddit.Data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hreddit.Model.Forum;

import java.util.List;

public class ForumViewModel extends AndroidViewModel {
    private ForumRepository repository;
    private LiveData<List<Forum>> allForums;
    private Forum forum;
    public ForumViewModel(@NonNull Application application) {
        super(application);
        repository = new ForumRepository(application);

    }

    public void insert(Forum forum) {
        repository.insert(forum);
    }

    public void update(Forum forum) {
        repository.update(forum);
    }

    public void delete(Forum forum) {
        repository.delete(forum);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<Forum>> getAllForums(String[] kategorija, String sort, String search) {

        allForums = repository.getForums(kategorija, sort, search);
        return allForums;
    }

    public LiveData<List<Forum>> getForumsById(int[] id) {

        allForums = repository.getForumsById(id);
        return allForums;
    }

    public Forum getForumByAll(String naslov, String tekst, String username, String datum){
        forum = repository.getForumByAll(naslov, tekst, username, datum);
        return forum;
    }

    public Forum getForumById(int id){
        forum = repository.getForumById(id);
        return forum;
    }

    public LiveData<List<Forum>> getAllForumsProfile(String username) {

        allForums = repository.getForumsProfile(username);
        return allForums;
    }
}
