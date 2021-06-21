package com.example.github.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.github.R;
import com.example.github.data.network.response.Repo;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    static final String EXTRA_DATA = "EXTRA_DATA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //intent
        String Username = getIntent().getStringExtra("NameUser");
        String Language = getIntent().getStringExtra("Language");
        String Repository = getIntent().getStringExtra("Repository");
        Log.d("repolist", Username);

        TextView tvUsername, repoName, langDetail;
        ImageView btnFavo;

        tvUsername = findViewById(R.id.tv_Username);
        repoName = findViewById(R.id.tv_nameDetail);
        langDetail = findViewById(R.id.tv_langDetail);
        btnFavo = findViewById(R.id.btn_favorite);

        tvUsername.setText(Username);
        repoName.setText(Repository);
        langDetail.setText(Language);

        btnFavo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, FavoriteActivity.class);
                intent.putExtra("Username", Username);
                intent.putExtra("Language", Language);
                intent.putExtra("Repository", Repository);
                startActivity(intent);
                Log.d("IntentDetailActivity", intent.toString());
            }
        });
    }
}