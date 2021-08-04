package com.example.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving news.
 */
public final class QueryUtils {

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link News} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<News> extractFeatureFromJson(String newsJSON) {

        // Create an empty ArrayList that we can start adding articles to.
        ArrayList<News> articles = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the SAMPLE_JSON_RESPONSE string
            JSONObject baseJsonResponse = new JSONObject(newsJSON);

            // Extract the JSONObject associated with the key called "response".
            JSONObject responseObject = baseJsonResponse.getJSONObject("response");

            // Extract the JSONArray associated with the key called "results",
            // which represents a list of articles.
            JSONArray resultsArray = responseObject.getJSONArray("results");

            // For each article in the resultsArray, create an {@link News} object.
            for (int i = 0; i < resultsArray.length(); i++) {

                // Get a single article at position i within the list of news.
                JSONObject currentArticle = resultsArray.getJSONObject(i);

                // Extract the value for the key called "webTitle"
                String fullWebsiteTitle = currentArticle.getString("webTitle");
                String webTitle = fullWebsiteTitle.split("\\|")[0];

                // Extract the value for the key called "sectionName"
                String sectionName = currentArticle.getString("sectionName");

                // Extract the value for the author's name.
                String nameOfAuthor = getAuthorsName(fullWebsiteTitle);

                // Extract the value for the key called "webPublicationDate"
                String publicationDate = currentArticle.getString("webPublicationDate").substring(0, 10);

                // Extract the value for the key called "webPublicationDate"
                String articleUrl = currentArticle.getString("webUrl");

                // Create a new {@link News} object with title, section, author,
                // and date from the JSON response.
                News article = new News(webTitle, sectionName, nameOfAuthor, publicationDate, articleUrl);

                // Add the new {@link News} to the list of articles.
                articles.add(article);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the article JSON results", e);
        }

        // Return the list of articles.
        return articles;
    }

    /**
     * Return the authors name if one is provided by the JSON response.
     */
    private static String getAuthorsName(String webTitle) {
        if (!webTitle.contains("|")) {
            return "";
        } else {
            return webTitle.substring(webTitle.indexOf("|") + 1).trim() + ", ";
        }
    }
}