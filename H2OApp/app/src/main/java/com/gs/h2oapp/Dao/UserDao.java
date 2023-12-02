package com.gs.h2oapp.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gs.h2oapp.Entity.User;

import java.util.List;

/**
 * Created by Warley Lima
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM `User`")
    List<User> getUser();

    @Query("SELECT MAX(id_user) FROM `User`")
    int getTotalUser();

    @Insert
    long insert(User o);

    @Update
    int update(User... o);

    @Delete
    int delete(User... o);



}
