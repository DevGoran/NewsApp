package com.example.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Loads a list of articles by using an AsyncTaskLoader to perform the
 * network request to the given URL.
 */
public class NewsLoader extends AsyncTaskLoader<List<News>> {

    /**
     * Query URL.
     */
    private final String mUrl;

    /**
     * Constructs a new {@link NewsLoader}.
     *
     * @param context of the activity.
     * @param url     to load data from.
     */
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        // Triggers the loadInBackground() method to execute.
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of articles.
        return QueryUtils.fetchNewsData(mUrl);
    }
}