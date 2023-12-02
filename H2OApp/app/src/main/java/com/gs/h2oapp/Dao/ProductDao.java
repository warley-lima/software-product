package com.gs.h2oapp.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gs.h2oapp.Entity.ProductOrder;

import java.util.List;

/**
 * Created by Warley Lima on 
 */
@Dao
public interface ProductDao {
   /* @Insert
    public void insertProduct(ProductOrder p);*/

    @Insert
    void insert(List<ProductOrder> p);


   // boolean insert2(List<ProductOrder> p);
    @Update
    void update(ProductOrder... p);

    @Delete
    void delete(ProductOrder... p);

    @Query("SELECT * FROM ProductOrder WHERE id_order_fk=:idOrder")
    List<ProductOrder> findProductsByOrder(final long idOrder);
}
