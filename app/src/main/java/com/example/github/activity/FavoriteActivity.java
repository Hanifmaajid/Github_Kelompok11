package com.example.github.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.github.R;

public class FavoriteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        //intent
        String Username = getIntent().getStringExtra("Username");
        String Language = getIntent().getStringExtra("Language");
        String Repository = getIntent().getStringExtra("Repository");
        Log.d("NameUser", Username);


    }
}
