package com.gs.h2oapp.Repository;

import android.content.Context;
import android.os.AsyncTask;

import com.gs.h2oapp.Dao.AdressDao;
import com.gs.h2oapp.Dao.AdressDao;
import com.gs.h2oapp.DataBase.TaComSedeDatabase;
import com.gs.h2oapp.Entity.Adress;

import java.util.List;

/**
 * Created by Warley Lima 
 */

public class AdressRepository {
    private AdressDao adressDao;
    private List<Adress> lstOrders;

   public AdressRepository(Context application) {
       adressDao = TaComSedeDatabase.getInstance(application).getAdressDao();
    }

    public List<Adress> getAllAdress() {
        lstOrders = new getAdressAsyncTask(adressDao).doInBackground();
        return lstOrders;
    }
    public int getTotalAdress(){
       return new getTotalAdressAsyncTask(adressDao).doInBackground();
    }

    public Long insert (Adress word) {
        long a = new insertAsync(adressDao).doInBackground(word);
        return a;
    }


    public Integer update (Adress word) {
        Integer a = new updateAsync(adressDao).doInBackground(word);
        return a;
    }

    public Integer delete (Adress a) {
        Integer ret = new deleteAsync(adressDao).doInBackground(a);
        return ret;
    }

    private static class updateAsync extends AsyncTask<Adress, Void, Integer> {
        private AdressDao mAsyncTaskDao;
        updateAsync(AdressDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Integer doInBackground(final Adress... params) {
            return mAsyncTaskDao.update(params[0]);
        }
    }

    private static class insertAsync extends AsyncTask<Adress, Void, Long> {
        private AdressDao mAsyncTaskDao;
        insertAsync(AdressDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Long doInBackground(final Adress... params) {
            return mAsyncTaskDao.insert(params[0]);
        }
    }

    private static class deleteAsync extends AsyncTask<Adress, Void, Integer> {
        private AdressDao mAsyncTaskDao;
        deleteAsync(AdressDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Integer doInBackground(final Adress... params) {
            return mAsyncTaskDao.delete(params[0]);
        }
    }

    private static class getTotalAdressAsyncTask extends AsyncTask<Void, Void, Integer > {
        private AdressDao mAsyncTaskDao;
        getTotalAdressAsyncTask(AdressDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Integer doInBackground( Void... params) {
            return mAsyncTaskDao.getTotalAdress();
        }
    }

    private static class getAdressAsyncTask extends AsyncTask<Void, Void, List<Adress> > {
        private AdressDao mAsyncTaskDao;
        getAdressAsyncTask(AdressDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<Adress>  doInBackground( Void... params) {
            return mAsyncTaskDao.getAdress();
        }
    }
}
