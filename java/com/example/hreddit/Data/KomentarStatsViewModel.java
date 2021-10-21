package com.example.hreddit.Data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.hreddit.Model.KomentarStats;

public class KomentarStatsViewModel extends AndroidViewModel {
    private KomentarStatsRepository repository;
    private KomentarStats KomentarStats;
    public KomentarStatsViewModel(@NonNull Application application) {
        super(application);
        repository = new KomentarStatsRepository(application);

    }

    public void insert(KomentarStats komentarStats) {
        repository.insert(komentarStats);
    }

    public void update(KomentarStats komentarStats) {
        repository.update(komentarStats);
    }

    public void delete(KomentarStats komentarStats) {
        repository.delete(komentarStats);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public KomentarStats getUserStatsForKomentar(String username, int idForuma){
        KomentarStats = repository.getUserStatsForKomentar(username, idForuma);
        return KomentarStats;
    }


}
