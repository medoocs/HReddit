package com.example.hreddit.Data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.hreddit.Model.Forum;
import com.example.hreddit.Model.User;

import java.util.List;

public class ForumRepository {
    private ForumDao forumDao;
    private LiveData<List<Forum>> allForums;
    private Forum forum;
    public ForumRepository(Application application) {
        UserDataBase database = UserDataBase.getInstance(application);
        forumDao = database.getForumDao();

    }

    public void insert(Forum forum) {
        new InsertNoteAsyncTask(forumDao).execute(forum);
    }

    public void update(Forum forum) {
        new UpdateNoteAsyncTask(forumDao).execute(forum);
    }

    public void delete(Forum forum) {
        new DeleteNoteAsyncTask(forumDao).execute(forum);
    }

    public void deleteAll() {
        new DeleteAllNotesAsyncTask(forumDao).execute();
    }

    public Forum getForumById(int id) {
        try{
            return new ForumRepository.GetForumByIdAsyncTask(forumDao, id).execute().get();
        }catch(Exception e){
            return null;
        }
    }
    private static class GetForumByIdAsyncTask extends AsyncTask<Void, Void, Forum> {
        private ForumDao forumDao;
        int id;
        private GetForumByIdAsyncTask(ForumDao forumDao, int id) {
            this.forumDao = forumDao;
            this.id = id;
        }

        @Override
        protected Forum doInBackground(Void... voids) {
            return forumDao.getForumById(this.id);
        }
    }

    public Forum getForumByAll(String naslov, String tekst, String username, String datum) {
        try{
            return new ForumRepository.GetForumByAllAsyncTask(forumDao, naslov, tekst, username, datum).execute().get();
        }catch(Exception e){
            return null;
        }
    }
    private static class GetForumByAllAsyncTask extends AsyncTask<Void, Void, Forum> {
        private ForumDao forumDao;
        String naslov, tekst, username, datum;
        private GetForumByAllAsyncTask(ForumDao forumDao, String naslov, String tekst, String username, String datum) {
            this.forumDao = forumDao;
            this.naslov = naslov;
            this.tekst = tekst;
            this.username = username;
            this.datum = datum;
        }

        @Override
        protected Forum doInBackground(Void... voids) {
            return forumDao.getForumByAll(this.naslov, this.tekst, this.username, this.datum);
        }
    }

    public LiveData<List<Forum>> getForums(String[] kategorija, String sort, String search) {
        Boolean isEmpty = true;
        for(int i = 0; i < 8; ++i) if(!kategorija[i].isEmpty()) isEmpty = false;
        if(isEmpty) allForums = forumDao.getForums(sort, search);
        else allForums = forumDao.getCatForums(kategorija, sort, search);

        return allForums;
    }

    public LiveData<List<Forum>> getForumsById(int[] id) {
        allForums = forumDao.getForumsById(id);
        return allForums;
    }

    public LiveData<List<Forum>> getForumsProfile(String username) {
        allForums = forumDao.getForumsProfile(username);

        return allForums;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Forum, Void, Void> {
        private ForumDao forumDao;

        private InsertNoteAsyncTask(ForumDao forumDao) {
            this.forumDao = forumDao;
        }

        @Override
        protected Void doInBackground(Forum... forums) {
            forumDao.insert(forums[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Forum, Void, Void> {
        private ForumDao forumDao;

        private UpdateNoteAsyncTask(ForumDao forumDao) {
            this.forumDao = forumDao;
        }

        @Override
        protected Void doInBackground(Forum... forums) {
            forumDao.update(forums[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Forum, Void, Void> {
        private ForumDao forumDao;

        private DeleteNoteAsyncTask(ForumDao forumDao) {
            this.forumDao = forumDao;
        }

        @Override
        protected Void doInBackground(Forum... forums) {
            forumDao.delete(forums[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private ForumDao forumDao;

        private DeleteAllNotesAsyncTask(ForumDao forumDao) {
            this.forumDao = forumDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            forumDao.deleteAll();
            return null;
        }
    }
}
