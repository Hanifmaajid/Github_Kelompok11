package com.example.github.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.github.R;
import com.example.github.data.local.database.DAO;
import com.example.github.data.local.entity.Favorite;
import com.example.github.data.local.database.FavoriteDatabase;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailActivity extends AppCompatActivity {

    private DAO dao;
    private String username,language,repository;
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dao = FavoriteDatabase.iniDB(DetailActivity.this).dao();

        //intent
        id = getIntent().getIntExtra("Id",0);
        username = getIntent().getStringExtra("NameUser");
        language = getIntent().getStringExtra("Language");
        repository = getIntent().getStringExtra("Repository");

        TextView tvUsername, repoName, langDetail;
        ImageView btnFavo;

        tvUsername = findViewById(R.id.tv_Username);
        repoName = findViewById(R.id.tv_nameDetail);
        langDetail = findViewById(R.id.tv_langDetail);
        btnFavo = findViewById(R.id.btn_favorite);

        tvUsername.setText(username);
        repoName.setText(repository);
        langDetail.setText(language);

        btnFavo.setOnClickListener(v -> {

            Favorite favorite = new Favorite();
            favorite.setId(id);
            favorite.setUsernameFav(username);
            favorite.setLanguageFav(language);
            favorite.setRepositoryFav(repository);

            dao.insertData(favorite).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(s -> {
                Toast.makeText(this, "sukses", Toast.LENGTH_SHORT).show();
            }, e -> {
                Log.d("errorInsert", e.getMessage());
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
    }
}