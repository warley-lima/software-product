package com.gs.h2oapp.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gs.h2oapp.Entity.Order;

import java.util.List;

/**
 * Created by Warley Lima 
 */
@Dao
public interface OrderDao {

   /* @Query("SELECT * FROM Order")
    List<Order> getOrders();*/

    /*@Insert  LiveData<List<MyObject>>
    public void insertUsersAndFriends(Order order, List<User> friends);*/

    @Query("SELECT * FROM `Order`")
    List<Order> getAllOrders();
    //LiveData<List<Order>> getAllOrders();

    @Insert
    long insert(Order o);

    @Delete
    int delete(Order... o);

    @Query("UPDATE `Order` SET id_order_server= :idOrderServer WHERE id_order = :idOrder")
     int updateIdOrderServer(long idOrder, long idOrderServer);



}
