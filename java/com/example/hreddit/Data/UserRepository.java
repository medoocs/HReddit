package com.example.hreddit.Data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.hreddit.Model.User;

import java.util.List;

public class UserRepository {
    private UserDao userDao;

    public UserRepository(Application application) {
        UserDataBase database = UserDataBase.getInstance(application);
        userDao = database.getUserDao();
    }

    public User getUser(String email, String password) {
        try{
            return new GetUserAsyncTask(userDao, email, password).execute().get();
        }catch(Exception e){
            return null;
        }

    }

    public User getEmail(String email) {
        try{
            return new GetEmailAsyncTask(userDao, email).execute().get();
        }catch(Exception e){
            return null;
        }

    }

    public User getPass(String email) {
        try{
            return new GetPassAsyncTask(userDao, email).execute().get();
        }catch(Exception e){
            return null;
        }

    }

    public User getUsername(String username) {
        try{
            return new GetUsernameAsyncTask(userDao, username).execute().get();
        }catch(Exception e){
            return null;
        }

    }



    public void insert(User user) {
        new InsertNoteAsyncTask(userDao).execute(user);
    }

    public void update(User user) {
        new UpdateNoteAsyncTask(userDao).execute(user);
    }

    public void delete(User user) {
        new DeleteNoteAsyncTask(userDao).execute(user);
    }

    public void deleteAll() {
        new DeleteAllNotesAsyncTask(userDao).execute();
    }

    private static class GetUserAsyncTask extends AsyncTask<Void, Void, User> {
        private UserDao userDao;
        String email, password;
        private GetUserAsyncTask(UserDao userDao, String email, String password) {
            this.userDao = userDao;
            this.email = email;
            this.password = password;
        }

        @Override
        protected User doInBackground(Void... voids) {
            return userDao.getUser(this.email, this.password);
        }
    }

    private static class GetUsernameAsyncTask extends AsyncTask<Void, Void, User> {
        private UserDao userDao;
        String username;
        private GetUsernameAsyncTask(UserDao userDao, String username) {
            this.userDao = userDao;
            this.username = username;
        }

        @Override
        protected User doInBackground(Void... voids) {
            return userDao.getUsername(this.username);
        }
    }

    private static class GetEmailAsyncTask extends AsyncTask<Void, Void, User> {
        private UserDao userDao;
        String email;
        private GetEmailAsyncTask(UserDao userDao, String email) {
            this.userDao = userDao;
            this.email = email;
        }

        @Override
        protected User doInBackground(Void... voids) {
            return userDao.getEmail(this.email);
        }
    }

    private static class GetPassAsyncTask extends AsyncTask<Void, Void, User> {
        private UserDao userDao;
        String email;
        private GetPassAsyncTask(UserDao userDao, String email) {
            this.userDao = userDao;
            this.email = email;
        }

        @Override
        protected User doInBackground(Void... voids) {
            return userDao.getPass(this.email);
        }
    }

    private static class InsertNoteAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertNoteAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private UpdateNoteAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private DeleteNoteAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        private DeleteAllNotesAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAll();
            return null;
        }
    }
}
