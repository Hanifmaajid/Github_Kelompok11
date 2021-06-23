package com.example.github.data.local.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.github.data.local.entity.Favorite;

@Database(entities = {Favorite.class}, version = 1,exportSchema = false)
public abstract class FavoriteDatabase extends RoomDatabase {
    public abstract DAO dao();

    private static FavoriteDatabase favoriteDatabase;

    public static FavoriteDatabase iniDB(Context context){
        if(favoriteDatabase == null){
            favoriteDatabase = Room.databaseBuilder(context, FavoriteDatabase.class, "favoriteDB")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return favoriteDatabase;
    }

    public static void destroyInstance(){
        favoriteDatabase = null;
    }
}
