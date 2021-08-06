package com.example.newsapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<News>> {

    /**
     * Loader ID.
     */
    private static final int NEWS_LOADER_ID = 0;

    /**
     * Adapter for the list of news.
     */
    private NewsAdapter mAdapter;

    /**
     * TextView that is displayed when the list is empty.
     */
    private TextView mEmptyStateTextView;

    /**
     * URL from which the data is being fetched.
     */
    private static final String REQUEST_URL = "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Deactivate night mode on app.
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        // Inflate the layout.
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView} in the layout.
        ListView newsListView = findViewById(R.id.list);

        // Set setEmptyView on the newsListView, in order to set the empty_view String when no data is
        // found.
        mEmptyStateTextView = findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of news as input.
        mAdapter = new NewsAdapter(this, new ArrayList<>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface.
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

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Only initialize Loader if there is an internet connection, else inform the user about that there is no internet connection.
        if (checkInterntConnectionState()) {
            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_spinner);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(getString(R.string.no_internt));
        }
    }

    @Override
    public android.content.Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new NewsLoader(MainActivity.this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        View loadingIndicator = findViewById(R.id.loading_spinner);
        // Check if there is either no data or internet connection and set the appropriate value to
        // mEmptyStateTextView.
        if (!checkInterntConnectionState()) {
            //state that there is no internet connection
            mEmptyStateTextView.setText(R.string.no_internt);
        } else if (checkInterntConnectionState()) {
            //There is internet but list is still empty
            mEmptyStateTextView.setText(R.string.no_news);
        }
        // Hide loading indicator when the data has been loaded.
        loadingIndicator.setVisibility(View.GONE);

        // Clear the adapter of previous news data.
        mAdapter.clear();

        // If there is a valid list of {@link News}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    /**
     * Check if device is connected to the internet and return a boolean value.
     **/
    private boolean checkInterntConnectionState() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}