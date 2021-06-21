package com.example.github.data.network;

import com.example.github.data.network.response.RepositoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubApiService {

    @GET("search/repositories")
    Call<RepositoryResponse> queryRepositories(@Query("q") String query);

}
