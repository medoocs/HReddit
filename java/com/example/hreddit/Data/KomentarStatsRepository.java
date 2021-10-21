package com.example.hreddit.Data;

import android.app.Application;
import android.os.AsyncTask;

import com.example.hreddit.Model.KomentarStats;

public class KomentarStatsRepository {
    private KomentarStatsDao komentarStatsDao;
    private KomentarStats komentarStats;
    public KomentarStatsRepository(Application application) {
        UserDataBase database = UserDataBase.getInstance(application);
        komentarStatsDao = database.getKomentarStatsDao();
    }

    public void insert(KomentarStats komentarStats) {
        new InsertNoteAsyncTask(komentarStatsDao).execute(komentarStats);
    }

    public void update(KomentarStats komentarStats) {
        new UpdateNoteAsyncTask(komentarStatsDao).execute(komentarStats);
    }

    public void delete(KomentarStats komentarStats) {
        new DeleteNoteAsyncTask(komentarStatsDao).execute(komentarStats);
    }

    public void deleteAll() {
        new DeleteAllNotesAsyncTask(komentarStatsDao).execute();
    }

    public KomentarStats getUserStatsForKomentar(String username, int idKomentara) {
        try{
            return new KomentarStatsRepository.GetUserStatsForKomentarAsyncTask(komentarStatsDao, username, idKomentara).execute().get();
        }catch(Exception e){
            return null;
        }
    }
    private static class GetUserStatsForKomentarAsyncTask extends AsyncTask<Void, Void, KomentarStats> {
        private KomentarStatsDao komentarStatsDao;
        int idKomentara;
        String username;
        private GetUserStatsForKomentarAsyncTask(KomentarStatsDao komentarStatsDao, String username, int idKomentara) {
            this.komentarStatsDao = komentarStatsDao;
            this.idKomentara = idKomentara;
            this.username = username;
        }

        @Override
        protected KomentarStats doInBackground(Void... voids) {
            return komentarStatsDao.getUserStatsForKomentar(this.username, this.idKomentara);
        }
    }

    private static class InsertNoteAsyncTask extends AsyncTask<KomentarStats, Void, Void> {
        private KomentarStatsDao komentarStatsDao;

        private InsertNoteAsyncTask(KomentarStatsDao komentarStatsDao) {
            this.komentarStatsDao = komentarStatsDao;
        }

        @Override
        protected Void doInBackground(KomentarStats... komentarStats) {
            komentarStatsDao.insert(komentarStats[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<KomentarStats, Void, Void> {
        private KomentarStatsDao komentarStatsDao;

        private UpdateNoteAsyncTask(KomentarStatsDao komentarStatsDao) {
            this.komentarStatsDao = komentarStatsDao;
        }

        @Override
        protected Void doInBackground(KomentarStats... komentarStats) {
            komentarStatsDao.update(komentarStats[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<KomentarStats, Void, Void> {
        private KomentarStatsDao komentarStatsDao;

        private DeleteNoteAsyncTask(KomentarStatsDao komentarStatsDao) {
            this.komentarStatsDao = komentarStatsDao;
        }

        @Override
        protected Void doInBackground(KomentarStats... komentarStats) {
            komentarStatsDao.delete(komentarStats[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private KomentarStatsDao komentarStatsDao;

        private DeleteAllNotesAsyncTask(KomentarStatsDao komentarStatsDao) {
            this.komentarStatsDao = komentarStatsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            komentarStatsDao.deleteAll();
            return null;
        }
    }
}
