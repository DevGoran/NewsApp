package com.example.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods related to requesting and receiving news.
 */
public final class QueryUtils {

    /**
     * Tag for the log messages.
     */
    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the dataset and return an {@link News} object to represent a single article.
     */
    public static List<News> fetchNewsData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object and
        // return the {@link Event}.
        return extractFeatureFromJson(jsonResponse);
    }

    /**
     * Returns a new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the news JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of {@link News} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<News> extractFeatureFromJson(String newsJSON) {
        // Create an empty ArrayList that we can start adding articles to.
        ArrayList<News> articles = new ArrayList<>();

        // Try to parse the data. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the newsJSON string
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

                // Extract the value for the key called "webTitle".
                String fullWebsiteTitle = currentArticle.getString("webTitle");
                String webTitle = fullWebsiteTitle.split("\\|")[0];

                // Extract the value for the key called "sectionName".
                String sectionName = currentArticle.getString("sectionName");

                // Extract the value for the author's name.
                String nameOfAuthor = getAuthorsName(fullWebsiteTitle);

                // Extract the value for the key called "webPublicationDate".
                String publicationDate = currentArticle.getString("webPublicationDate").substring(0, 10);

                // Extract the value for the key called "webUrl".
                String articleUrl = currentArticle.getString("webUrl");

                // Create a new {@link News} object with title, section, author, date
                // and URL from the JSON response.
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
        // Checks whether the webTitle contains a vertical slash "|" or not. If it doesn't contain one,
        // return an empty String, since no author is available. Else, extract the author as a sub string and return it.
        if (!webTitle.contains("|")) {
            return "";
        } else {
            return webTitle.substring(webTitle.indexOf("|") + 1).trim() + ", ";
        }
    }
}