package com.example.newsapp;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a fake list of articles.
        ArrayList<News> articles = new ArrayList<>();
        articles.add(new News("The day of the dead 1", "Cryptocurrencies", "John Doe","Feb 2, 2016"));
        articles.add(new News("The day of the dead 2", "Cryptocurrencies", "John Doe","July 20, 2015"));
        articles.add(new News("The day of the dead 3", "Cryptocurrencies", "John Doe","Nov 10, 2014"));
        articles.add(new News("The day of the dead 4", "Cryptocurrencies", "John Doe","May 3, 2014"));
        articles.add(new News("The day of the dead 5", "Cryptocurrencies", "John Doe","Jan 31, 2013"));
        articles.add(new News("The day of the dead 6", "Cryptocurrencies", "John Doe","Aug 19, 2012"));
        articles.add(new News("The day of the dead 7", "Cryptocurrencies", "John Doe","Oct 30, 2011"));

        // Find a reference to the {@link ListView} in the layout.
        ListView newsListView = findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of articles.
        NewsAdapter adapter = new NewsAdapter(
                this, articles);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsListView.setAdapter(adapter);
    }
}