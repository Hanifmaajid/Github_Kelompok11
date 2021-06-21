package com.example.github.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.github.R;
import com.example.github.RepositoryAdapter;
import com.example.github.data.network.ApiConfig;
import com.example.github.data.network.response.Repo;
import com.example.github.data.network.response.RepositoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView dataListView;
    private EditText requestTag;
    private TextView errorMassage;
    private ProgressBar progressBar;
    private RepositoryAdapter adapter;
    private ImageButton btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        errorMassage = findViewById(R.id.et_errorMassage);
        requestTag = findViewById(R.id.et_searchUser);
        dataListView = findViewById(R.id.rv_searchUser);
        adapter = new RepositoryAdapter(getApplicationContext());
        dataListView.setAdapter(adapter);
        btnSearch = findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);

        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Repo repo = adapter.getItem(position);
                intent.putExtra("NameUser", repo.getOwner().getLogin());
                intent.putExtra("Language", repo.getLanguage());
                intent.putExtra("Repository", repo.getName());

                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search:{
                String query = requestTag.getText().toString();
                if (!query.isEmpty()){
//                    Toast.makeText(this, "mencari", Toast.LENGTH_SHORT).show();
                    showLoading(true);
                } else {
                    requestTag.setError("input tidak boleh kosong");
                }
                Call<RepositoryResponse> call = ApiConfig.githubApiService().queryRepositories(query);

                call.enqueue(new Callback<RepositoryResponse>() {
                    @Override
                    public void onResponse(Call<RepositoryResponse> call, Response<RepositoryResponse> response) {
                        showLoading(true);
                        if(response.isSuccessful()){
                            showLoading(false);
                            adapter.clear();
                            adapter.addAll(response.body().getRepos());
                        } else {
                            showLoading(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<RepositoryResponse> call, Throwable t) {
                        showErrorMassage();
                        showLoading(false);
                    }
                });
                break;
            }
        }

    }

    private void showErrorMassage() {
        dataListView.setVisibility(View.INVISIBLE);
        errorMassage.setVisibility(View.VISIBLE);
    }

    private void showLoading(Boolean state) {
        if(state){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}