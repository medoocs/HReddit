package com.example.hreddit.Data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.hreddit.Model.ForumStats;

public class ForumStatsViewModel extends AndroidViewModel {
    private ForumStatsRepository repository;
    private ForumStats forumStats;
    public ForumStatsViewModel(@NonNull Application application) {
        super(application);
        repository = new ForumStatsRepository(application);

    }

    public void insert(ForumStats forum) {
        repository.insert(forum);
    }

    public void update(ForumStats forum) {
        repository.update(forum);
    }

    public void delete(ForumStats forum) {
        repository.delete(forum);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public ForumStats getUserStatsForForum(String username, int idForuma){
        forumStats = repository.getUserStatsForForum(username, idForuma);
        return forumStats;
    }


}
