package com.example.github;

import android.net.Uri;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class Networking {
    private final static String GITHUB_BASE_URL = "https://api.github.com/search/repositories";
    private final static String PARAM_QUERY = "q";

    private final static String PARAM_SORT = "sort";
    private final static String SORT_BY = "stars";

    private static URL buildUrl(String gitHubSearchQuery) {
        //CREATE FULL LINK
        Uri buildUri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, gitHubSearchQuery)
//                .appendQueryParameter(PARAM_SORT, SORT_BY)
                .build();

        //CONVERT THE URI TO URL
        URL url = null;
        try {
            url = new URL(buildUri.toString());
            Log.d("urlrepo", url.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String getRepositoryFromHttpUrl(URL url) throws IOException {
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    private static List<Repository> jsonFormatter(String jsonResponse) {
        List<Repository> repositoryList = new ArrayList<>();
        Log log = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray items = jsonObject.getJSONArray("items");

            //get first 50 Repositories
            int dataLength = 50;
            if (items.length() < dataLength) {
                dataLength = items.length();
            }
            for (int i = 0; i < dataLength; i++) {
                JSONObject currentRepo = items.getJSONObject(i);
                Log.d("repo", currentRepo.toString());
                String repoName = currentRepo.getString("name");
                String repoOwner = currentRepo.getJSONObject("owner").getString("login");
                String repoLanguage = currentRepo.getString("language");
                String repoStars = currentRepo.getString("stars_count");

                log.v("Data", "Number + 1");

                Repository repository = new Repository(repoName, repoOwner, repoLanguage, repoStars);
                repositoryList.add(repository);
            }

        } catch (JSONException e) {
            log.v("Network", "Cant read JSON");
        }
        return repositoryList;
    }

    public static List<Repository> getDataFromApi(String query) throws IOException {
        //create URL
        URL apiURL = buildUrl(query);
        String jsonResponse = getRepositoryFromHttpUrl(apiURL);
        List<Repository> repos = jsonFormatter(jsonResponse);
        return repos;
    }
}
