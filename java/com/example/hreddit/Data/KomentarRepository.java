package com.example.hreddit.Data;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.hreddit.Model.Forum;
import com.example.hreddit.Model.Komentar;

import java.util.List;

public class KomentarRepository {
    private KomentarDao komentarDao;
    private LiveData<List<Komentar>> allComments;
    public KomentarRepository(Application application) {
        UserDataBase database = UserDataBase.getInstance(application);
        komentarDao = database.getKomentarDao();

    }

    public void insert(Komentar komentar) {
        new InsertNoteAsyncTask(komentarDao).execute(komentar);
    }

    public void update(Komentar komentar) {
        new UpdateNoteAsyncTask(komentarDao).execute(komentar);
    }

    public void delete(Komentar komentar) {
        new DeleteNoteAsyncTask(komentarDao).execute(komentar);
    }

    public void deleteAll() {
        new DeleteAllNotesAsyncTask(komentarDao).execute();
    }

    public LiveData<List<Komentar>> getComments(int id) {
        allComments = komentarDao.getComments(id);
        return allComments;
    }

    public LiveData<List<Komentar>> getCommentsProfile(String username) {
        allComments = komentarDao.getCommentsProfile(username);
        return allComments;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Komentar, Void, Void> {
        private KomentarDao komentarDao;

        private InsertNoteAsyncTask(KomentarDao komentarDao) {
            this.komentarDao = komentarDao;
        }

        @Override
        protected Void doInBackground(Komentar... comments) {
            komentarDao.insert(comments[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Komentar, Void, Void> {
        private KomentarDao komentarDao;

        private UpdateNoteAsyncTask(KomentarDao komentarDao) {
            this.komentarDao = komentarDao;
        }

        @Override
        protected Void doInBackground(Komentar... comments) {
            komentarDao.update(comments[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Komentar, Void, Void> {
        private KomentarDao komentarDao;

        private DeleteNoteAsyncTask(KomentarDao komentarDao) {
            this.komentarDao = komentarDao;
        }

        @Override
        protected Void doInBackground(Komentar... forums) {
            komentarDao.delete(forums[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private KomentarDao komentarDao;

        private DeleteAllNotesAsyncTask(KomentarDao komentarDao) {
            this.komentarDao = komentarDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            komentarDao.deleteAll();
            return null;
        }
    }
}
