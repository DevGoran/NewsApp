package com.example.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        // Get the list of articles from {@link QueryUtils}
        ArrayList<News> articles = QueryUtils.extractNews();

        // Find a reference to the {@link ListView} in the layout.
        ListView newsListView = findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of articles.
        NewsAdapter adapter = new NewsAdapter(
                this, articles);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsListView.setAdapter(adapter);

        // Opens the selected article on click.
        newsListView.setOnItemClickListener((parent, view, position, id) -> {
            String url = articles.get(position).getUrl();
            Intent openArticle = new Intent(Intent.ACTION_VIEW);
            openArticle.setData(Uri.parse(url));
            startActivity(openArticle);
        });
    }
}