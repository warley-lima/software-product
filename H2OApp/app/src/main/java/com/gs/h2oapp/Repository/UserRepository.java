package com.gs.h2oapp.Repository;

import android.content.Context;
import android.os.AsyncTask;

import com.gs.h2oapp.Dao.UserDao;
import com.gs.h2oapp.DataBase.TaComSedeDatabase;
import com.gs.h2oapp.Entity.User;

import java.util.List;

/**
 * Created by Warley Lima 
 */

public class UserRepository {
    private UserDao userDao;
    private List<User> userList;

   public UserRepository(Context application) {
       userDao = TaComSedeDatabase.getInstance(application).getUserDao();
    }

    public List<User> getAllUser() {
        userList = new getUserAsyncTask(userDao).doInBackground();
        return userList;
    }
    public int getTotalAdress(){
       return new getTotalUserAsyncTask(userDao).doInBackground();
    }

    public Long insert (User word) {
        long a = new insertAsync(userDao).doInBackground(word);
        return a;
    }

    public Integer update (User word) {
        return new updateAsync(userDao).doInBackground(word);
    }

    public Integer delete (User a) {
        return new deleteAsync(userDao).doInBackground(a);
    }

    private static class updateAsync extends AsyncTask<User, Void, Integer> {
        private UserDao mAsyncTaskDao;
        updateAsync(UserDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Integer doInBackground(final User... params) {
            return mAsyncTaskDao.update(params[0]);
        }
    }

    private static class insertAsync extends AsyncTask<User, Void, Long> {
        private UserDao mAsyncTaskDao;
        insertAsync(UserDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Long doInBackground(final User... params) {
            return mAsyncTaskDao.insert(params[0]);
        }
    }

    private static class deleteAsync extends AsyncTask<User, Void, Integer> {
        private UserDao mAsyncTaskDao;
        deleteAsync(UserDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Integer doInBackground(final User... params) {
            return mAsyncTaskDao.delete(params[0]);
        }
    }

    private static class getTotalUserAsyncTask extends AsyncTask<Void, Void, Integer > {
        private UserDao mAsyncTaskDao;
        getTotalUserAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Integer doInBackground( Void... params) {
            return mAsyncTaskDao.getTotalUser();
        }
    }

    private static class getUserAsyncTask extends AsyncTask<Void, Void, List<User> > {
        private UserDao mAsyncTaskDao;
        getUserAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<User>  doInBackground( Void... params) {
            return mAsyncTaskDao.getUser();
        }
    }
}
