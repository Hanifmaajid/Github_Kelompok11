package com.example.github.data.local.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.github.data.local.entity.Favorite;

import java.util.List;


import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insertData(Favorite favorite);

    @Query("select * from favorite_db")
    Flowable<List<Favorite>> getAllFavoriteRepo();

    @Update
    Single<Integer> updateData(Favorite item);

    @Delete
    Single<Integer> deleteFavorite(Favorite item);

}
