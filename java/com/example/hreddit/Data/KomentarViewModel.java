package com.example.hreddit.Data;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hreddit.Model.Forum;
import com.example.hreddit.Model.Komentar;

import java.util.List;

public class KomentarViewModel extends AndroidViewModel {
    private KomentarRepository repository;
    private LiveData<List<Komentar>> allComments;
    public KomentarViewModel(@NonNull Application application) {
        super(application);
        repository = new KomentarRepository(application);
    }

    public void insert(Komentar komentar) {
        repository.insert(komentar);
    }

    public void update(Komentar komentar) {
        repository.update(komentar);
    }

    public void delete(Komentar komentar) {
        repository.delete(komentar);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<Komentar>> getAllComments(int id) {
        allComments = repository.getComments(id);
        return allComments;
    }

    public LiveData<List<Komentar>> getAllCommentsProfile(String username) {
        allComments = repository.getCommentsProfile(username);
        return allComments;
    }
}