package com.example.github.data.network.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RepositoryResponse{

	@SerializedName("total_count")
	private int totalCount;

	@SerializedName("incomplete_results")
	private boolean incompleteResults;

	@SerializedName("items")
	private List<Repo> repos;

	public int getTotalCount(){
		return totalCount;
	}

	public boolean isIncompleteResults(){
		return incompleteResults;
	}

	public List<Repo> getRepos(){
		return repos;
	}
}