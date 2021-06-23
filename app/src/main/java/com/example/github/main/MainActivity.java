package com.example.github.main;

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

import com.example.github.R;
import com.example.github.detail.DetailActivity;
import com.example.github.favorite.FavoriteActivity;
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
    private ImageButton btnSearch, btn_toFavorite;

//    private static final int GITHUB_SEARCH_LOADER = 1;
//    private static final String GITHUB_QUERY_TAG = "query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        errorMassage = findViewById(R.id.et_errorMassage);
        requestTag = findViewById(R.id.et_searchUser);
        dataListView = findViewById(R.id.rv_searchUser);
        btn_toFavorite = findViewById(R.id.btn_toFavorite);
        btnSearch = findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);

        adapter = new RepositoryAdapter(this);
        dataListView.setAdapter(adapter);

        btn_toFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(i);
            }
        });

        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Repo repo = adapter.getItem(position);
                intent.putExtra("NameUser", repo.getOwner().getLogin());
                intent.putExtra("Language", repo.getLanguage());
                intent.putExtra("Repository", repo.getName());
                intent.putExtra("Id", repo.getId());

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
//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistanceState) {
//        super.onSaveInstanceState(outState, outPersistanceState);
//        outState.putString(GITHUB_QUERY_TAG, requestTag.getText().toString().trim());
//    }

//    private void showJsonDataView() {
//        errorMassage.setVisibility(View.INVISIBLE);
//        dataListView.setVisibility(View.VISIBLE);
//    }







//    public void searchRepo(View view) {
//        makeGithubSearchQuery();
//    }

//    private void makeGithubSearchQuery() {
//        String githubQuery = requestTag.getText().toString();
//
//        Bundle queryBundle = new Bundle();
//        queryBundle.putString(GITHUB_QUERY_TAG, githubQuery);
//
//        LoaderManager loaderManager = getSupportLoaderManager();
//        Loader<String> githubSearchLoader = loaderManager.getLoader(GITHUB_SEARCH_LOADER);
//        if (githubSearchLoader == null) {
//            loaderManager.initLoader(GITHUB_SEARCH_LOADER, queryBundle, this);
//        } else {
//            loaderManager.restartLoader(GITHUB_SEARCH_LOADER, queryBundle, this);
//        }
//    }


//    public Loader<List<Repository>> onCreateLoader(int id, final Bundle args) {
//        return new AsyncTaskLoader<List<Repository>>(this) {
//            List<Repository> mRepositoryList;
//
//            @Override
//            protected void onStartLoading() {
//                if (args == null) {
//                    return;
//                }
//                progressBar.setVisibility(View.VISIBLE);
//
//                if (mRepositoryList != null) {
//                    deliverResult(mRepositoryList);
//                } else {
//                    forceLoad();
//                }
//            }
//
//            @Nullable
//            @Override
//            public List<Repository> loadInBackground() {
//                String searchQueryUrlString = args.getString(GITHUB_QUERY_TAG);
//
//                try {
//                    List<Repository> githubSearchResult = Networking.getDataFromApi(searchQueryUrlString);
//                    return githubSearchResult;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    return null;
//                }
//            }
//
//            @Override
//            public void deliverResult(@Nullable @org.jetbrains.annotations.Nullable List<Repository> githubJson) {
//                mRepositoryList = githubJson;
//                super.deliverResult(githubJson);
//            }
//        };
//    }
//
//    public void onLoadFinished(Loader<List<Repository>> loader, List<Repository> data) {
//
//        progressBar.setVisibility(View.INVISIBLE);
//
//        if (data == null) {
//            showErrorMassage();
//        } else {
//            //clear list view old data
//            adapter.clear();
//            Log.d("datarepo", data.toString());
//            adapter.addAll(data);
//            showJsonDataView();
//        }
//    }
//
//    public void onLoaderReset(Loader<List<Repository>> loader) {
//
//    }
}