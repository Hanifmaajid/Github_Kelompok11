package com.example.github.favorite;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github.R;
import com.example.github.data.local.database.DAO;
import com.example.github.data.local.entity.Favorite;
import com.example.github.data.local.database.FavoriteDatabase;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteActivity extends AppCompatActivity implements OnFavoriteRepoItemClickListener {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RecyclerView favoriteRecyclerView;
    private FavoriteRepositoryAdapter adapter;
    private DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite2);
        dao = FavoriteDatabase.iniDB(this).dao();

        favoriteRecyclerView = findViewById(R.id.rv_favorite);
        adapter = new FavoriteRepositoryAdapter(this);

        favoriteRecyclerView.setAdapter(adapter);
        favoriteRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        compositeDisposable.add(
                dao.getAllFavoriteRepo().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(listFavorite -> {
                    if (!listFavorite.isEmpty()) {
                        adapter.setData(listFavorite);
                    } else {
                        Toast.makeText(this, "Anda belum memiliki repository favorite", Toast.LENGTH_SHORT).show();
                    }
                }, error -> {
                    Toast.makeText(this, "Data gagal ditampilkan", Toast.LENGTH_SHORT).show();
                })
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    @Override
    public void onItemClick(Favorite favorite) {
        dao.deleteFavorite(favorite).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(success-> {
            Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
        }, error->{
            Toast.makeText(this, "Data gagal dihapus", Toast.LENGTH_SHORT).show();
        });
    }
}
