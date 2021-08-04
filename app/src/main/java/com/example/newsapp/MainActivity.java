package com.example.newsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * Adapter for the list of news
     */
    private NewsAdapter mAdapter;

    /**
     * Sample JSON response for a query
     * "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test"
     */
    private static final String JSON_RESPONSE = "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Deactivate night mode on app.
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        // Inflate the layout.
        setContentView(R.layout.activity_main);

        // Create an {@link AsyncTask} to perform the HTTP request to the given URL
        // on a background thread. When the result is received on the main UI thread,
        // then update the UI.
        NewsAsyncTask task = new NewsAsyncTask();
        task.execute(JSON_RESPONSE);

        // Find a reference to the {@link ListView} in the layout.
        ListView newsListView = findViewById(R.id.list);

        // Create a new adapter that takes an empty list of news as input
        mAdapter = new NewsAdapter(this, new ArrayList<>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected news.
        newsListView.setOnItemClickListener((adapterView, view, position, l) -> {
            // Find the current news that was clicked on.
            News currentENews = mAdapter.getItem(position);

            // Convert the String URL into a URI object (to pass into the Intent constructor).
            Uri newsUri = Uri.parse(currentENews.getUrl());

            // Create a new intent to view the news URI.
            Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

            // Send the intent to launch a new activity.
            startActivity(websiteIntent);
        });
    }

    @SuppressLint("StaticFieldLeak")
    private class NewsAsyncTask extends AsyncTask<String, Void, List<News>> {

        @Override
        protected List<News> doInBackground(String... urls) {
            return QueryUtils.fetchNewsData(urls[0]);
        }

        @Override
        protected void onPostExecute(List<News> data) {
            // Clear the adapter of previous news data.
            mAdapter.clear();

            // If there is a valid list of {@link News}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
}