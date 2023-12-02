package com.gs.h2oapp.Repository;

import android.content.Context;
import android.os.AsyncTask;

import com.gs.h2oapp.Dao.ProductDao;
import com.gs.h2oapp.DataBase.TaComSedeDatabase;
import com.gs.h2oapp.Entity.ProductOrder;

import java.util.List;

/**
 * Created by Warley Lima
 */

public class ProductDtoRepository {
    private ProductDao productDao;
    private List<ProductOrder> lstProducts;

   public ProductDtoRepository(Context application) {
       productDao = TaComSedeDatabase.getInstance(application).getProductDao();
    }

    public List<ProductOrder> getProductsByOrder(long idOrder) {
        lstProducts =  new getProductsAsynk(productDao).doInBackground(idOrder);
        return lstProducts;
    }


    public void insert (List<ProductOrder> lstProductOrder) {
       try {
           new insertAsyncTask(productDao).doInBackground(lstProductOrder);
          // return true;
       } catch (Exception e){
          // return false;
       }
    }

    public boolean insert2 (List<ProductOrder> lstProductOrder) {
        try {
            new insertAsyncTask(productDao).doInBackground(lstProductOrder);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private static class insertAsyncTask extends AsyncTask<List<ProductOrder>, Void, Void> {
        private ProductDao mAsyncTaskDao;
        insertAsyncTask(ProductDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final List<ProductOrder>... params) {
             mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class getProductsAsynk extends AsyncTask<Long, Void, List<ProductOrder> > {
        private ProductDao mAsyncTaskDao;
        getProductsAsynk(ProductDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected List<ProductOrder>  doInBackground(Long... params) {
            return mAsyncTaskDao.findProductsByOrder(params[0]);
        }
    }
}
