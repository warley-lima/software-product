package com.gs.h2oapp.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gs.h2oapp.Entity.Adress;
import com.gs.h2oapp.Entity.Order;

import java.util.List;

/**
 * Created by Warley Lima 
 */
@Dao
public interface AdressDao {

    @Query("SELECT * FROM `Adress`")
    List<Adress> getAdress();

    @Query("SELECT MAX(id_adress) FROM `Adress`")
    int getTotalAdress();

    @Insert
    long insert(Adress o);

    @Update
    int update(Adress... o);

    @Delete
    int delete(Adress... o);



}
