package com.gs.h2oapp.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.gs.h2oapp.Dao.AdressDao;
import com.gs.h2oapp.Dao.OrderDao;
import com.gs.h2oapp.Dao.ProductDao;
import com.gs.h2oapp.Dao.UserDao;
import com.gs.h2oapp.Entity.Adress;
import com.gs.h2oapp.Entity.Order;
import com.gs.h2oapp.Entity.ProductOrder;
import com.gs.h2oapp.Entity.User;

/**
 * Created by Warley Lima 
 */
@Database(entities = {Order.class, ProductOrder.class, Adress.class, User.class}, version = 1)
public abstract class TaComSedeDatabase extends RoomDatabase {

    private static final String DB_NAME = "AguaNoAppDB.db";
    private static volatile TaComSedeDatabase instance;

    public static synchronized TaComSedeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static TaComSedeDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                TaComSedeDatabase.class,
                DB_NAME).allowMainThreadQueries().build();
    }


    public abstract AdressDao getAdressDao();
    public abstract UserDao getUserDao();
    public abstract OrderDao getOrderDao();
    public abstract ProductDao getProductDao();
}

