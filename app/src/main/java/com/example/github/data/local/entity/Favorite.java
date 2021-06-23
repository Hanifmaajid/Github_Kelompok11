package com.example.github.data.local.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_db")

public class Favorite {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "ID")
    private int id;

    @ColumnInfo(name = "Username")
    private String usernameFav;

    @ColumnInfo(name = "Language")
    private String languageFav;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsernameFav() {
        return usernameFav;
    }

    public void setUsernameFav(String usernameFav) {
        this.usernameFav = usernameFav;
    }

    public String getLanguageFav() {
        return languageFav;
    }

    public void setLanguageFav(String languageFav) {
        this.languageFav = languageFav;
    }

    public String getRepositoryFav() {
        return repositoryFav;
    }

    public void setRepositoryFav(String repositoryFav) {
        this.repositoryFav = repositoryFav;
    }

    @ColumnInfo(name = "Repository")
    private String repositoryFav;

}
