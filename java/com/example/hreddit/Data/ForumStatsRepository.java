package com.example.hreddit.Data;

import android.app.Application;
import android.os.AsyncTask;

import com.example.hreddit.Model.ForumStats;

public class ForumStatsRepository {
    private ForumStatsDao forumStatsDao;
    private ForumStats forumStats;
    public ForumStatsRepository(Application application) {
        UserDataBase database = UserDataBase.getInstance(application);
        forumStatsDao = database.getForumStatsDao();
    }

    public void insert(ForumStats forumStats) {
        new InsertNoteAsyncTask(forumStatsDao).execute(forumStats);
    }

    public void update(ForumStats forumStats) {
        new UpdateNoteAsyncTask(forumStatsDao).execute(forumStats);
    }

    public void delete(ForumStats forumStats) {
        new DeleteNoteAsyncTask(forumStatsDao).execute(forumStats);
    }

    public void deleteAll() {
        new DeleteAllNotesAsyncTask(forumStatsDao).execute();
    }

    public ForumStats getUserStatsForForum(String username, int idForuma) {
        try{
            return new ForumStatsRepository.GetUserStatsForForumAsyncTask(forumStatsDao, username, idForuma).execute().get();
        }catch(Exception e){
            return null;
        }
    }
    private static class GetUserStatsForForumAsyncTask extends AsyncTask<Void, Void, ForumStats> {
        private ForumStatsDao forumStatsDao;
        int idForuma;
        String username;
        private GetUserStatsForForumAsyncTask(ForumStatsDao forumStatsDao, String username, int idForuma) {
            this.forumStatsDao = forumStatsDao;
            this.idForuma = idForuma;
            this.username = username;
        }

        @Override
        protected ForumStats doInBackground(Void... voids) {
            return forumStatsDao.getUserStatsForForum(this.username, this.idForuma);
        }
    }

    private static class InsertNoteAsyncTask extends AsyncTask<ForumStats, Void, Void> {
        private ForumStatsDao forumStatsDao;

        private InsertNoteAsyncTask(ForumStatsDao forumStatsDao) {
            this.forumStatsDao = forumStatsDao;
        }

        @Override
        protected Void doInBackground(ForumStats... forumStats) {
            forumStatsDao.insert(forumStats[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<ForumStats, Void, Void> {
        private ForumStatsDao forumStatsDao;

        private UpdateNoteAsyncTask(ForumStatsDao forumStatsDao) {
            this.forumStatsDao = forumStatsDao;
        }

        @Override
        protected Void doInBackground(ForumStats... forumStats) {
            forumStatsDao.update(forumStats[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<ForumStats, Void, Void> {
        private ForumStatsDao forumStatsDao;

        private DeleteNoteAsyncTask(ForumStatsDao forumStatsDao) {
            this.forumStatsDao = forumStatsDao;
        }

        @Override
        protected Void doInBackground(ForumStats... forumStats) {
            forumStatsDao.delete(forumStats[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private ForumStatsDao forumStatsDao;

        private DeleteAllNotesAsyncTask(ForumStatsDao forumStatsDao) {
            this.forumStatsDao = forumStatsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            forumStatsDao.deleteAll();
            return null;
        }
    }
}
