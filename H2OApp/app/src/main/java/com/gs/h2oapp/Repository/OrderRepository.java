package com.gs.h2oapp.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.gs.h2oapp.Dao.OrderDao;
import com.gs.h2oapp.DataBase.TaComSedeDatabase;
import com.gs.h2oapp.Entity.Order;

import java.util.List;

/**
 * Created by Warley Lima
 */

public class OrderRepository {
    private OrderDao orderDao;
    //private LiveData<List<Order>> lstOrders;
    private List<Order> lstOrders;

   public OrderRepository(Context application) {

       orderDao = TaComSedeDatabase.getInstance(application).getOrderDao();
    }

    public List<Order> getAllOrders() {
        lstOrders =  new getOrdersAsyncTask(orderDao).doInBackground();
        return lstOrders;
    }


    public Long insert (Order word) {
       // long  a =  new insertAsyncTask(orderDao).execute(word);
        long  a =  new insertAsyncTask(orderDao).doInBackground(word);
        return a;
    }
    public Integer updateIdOrderServer (long idOrder, long idOrderServer) {
        Integer ret = new updateAsync(orderDao).doInBackground(idOrder,idOrderServer);
        return ret;
    }

    public Integer delete (Order a) {
        Integer ret = new deleteAsync(orderDao).doInBackground(a);
        return ret;
    }

    private static class insertAsyncTask extends AsyncTask<Order, Void, Long> {
        private OrderDao mAsyncTaskDao;
        insertAsyncTask(OrderDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Long doInBackground(final Order... params) {
           // long  a = mAsyncTaskDao.insert(params[0]);
          //  System.out.println("PRIMAY KEY INSERT-------------------------------- " + a);
            return mAsyncTaskDao.insert(params[0]);
        }
    }


    private static class updateAsync extends AsyncTask<Long, Void, Integer> {
        private OrderDao mAsyncTaskDao;
        updateAsync(OrderDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Integer doInBackground(final Long... params) {
            return mAsyncTaskDao.updateIdOrderServer(params[0], params[1]);
        }
    }

    private static class deleteAsync extends AsyncTask<Order, Void, Integer> {
        private OrderDao mAsyncTaskDao;
        deleteAsync(OrderDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Integer doInBackground(final Order... params) {
            return mAsyncTaskDao.delete(params[0]);
        }
    }

    private static class getOrdersAsyncTask extends AsyncTask<Void, Void, List<Order> > {
        private OrderDao mAsyncTaskDao;
        getOrdersAsyncTask(OrderDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<Order>  doInBackground( Void... params) {
            return mAsyncTaskDao.getAllOrders();
        }
    }
}
