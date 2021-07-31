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
     * Sample JSON response for a query
     */
    private static final String SAMPLE_JSON_RESPONSE = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":18255,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":1826,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"commentisfree/2021/jul/24/vaccine-passports-rights-freedoms-covid\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2021-07-24T11:00:21Z\",\"webTitle\":\"It's time for a serious debate about vaccine passports | Shami Chakrabarti\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2021/jul/24/vaccine-passports-rights-freedoms-covid\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2021/jul/24/vaccine-passports-rights-freedoms-covid\",\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"},{\"id\":\"politics/2021/jul/01/labour-mp-shares-his-struggles-with-addiction-in-commons-debate-for-pride\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2021-07-01T18:00:15Z\",\"webTitle\":\"Labour MP shares addiction struggles in Commons Pride debate\",\"webUrl\":\"https://www.theguardian.com/politics/2021/jul/01/labour-mp-shares-his-struggles-with-addiction-in-commons-debate-for-pride\",\"apiUrl\":\"https://content.guardianapis.com/politics/2021/jul/01/labour-mp-shares-his-struggles-with-addiction-in-commons-debate-for-pride\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"commentisfree/2021/may/22/the-free-speech-law-will-make-university-debate-harder-not-easier\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2021-05-22T09:00:56Z\",\"webTitle\":\"The 'free speech' law will make university debate harder, not easier | David Renton\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2021/may/22/the-free-speech-law-will-make-university-debate-harder-not-easier\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2021/may/22/the-free-speech-law-will-make-university-debate-harder-not-easier\",\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"},{\"id\":\"business/2021/may/21/rich-list-2021-results-will-renew-debate-on-wealth-tax-in-britain\",\"type\":\"article\",\"sectionId\":\"business\",\"sectionName\":\"Business\",\"webPublicationDate\":\"2021-05-21T16:05:10Z\",\"webTitle\":\"Rich List 2021: results will renew debate on wealth tax in Britain\",\"webUrl\":\"https://www.theguardian.com/business/2021/may/21/rich-list-2021-results-will-renew-debate-on-wealth-tax-in-britain\",\"apiUrl\":\"https://content.guardianapis.com/business/2021/may/21/rich-list-2021-results-will-renew-debate-on-wealth-tax-in-britain\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2021/mar/11/snp-focus-on-scottish-independence-is-wrong-debate-keir-starmer\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2021-03-11T17:06:03Z\",\"webTitle\":\"SNP focus on Scottish independence is 'wrong debate', says Starmer \",\"webUrl\":\"https://www.theguardian.com/politics/2021/mar/11/snp-focus-on-scottish-independence-is-wrong-debate-keir-starmer\",\"apiUrl\":\"https://content.guardianapis.com/politics/2021/mar/11/snp-focus-on-scottish-independence-is-wrong-debate-keir-starmer\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"music/2021/apr/10/music-streaming-debate-what-songwriter-artist-and-industry-insider-say-publication-parliamentary-report\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2021-04-10T07:00:01Z\",\"webTitle\":\"The music streaming debate: what the artists, songwriters and industry insiders say\",\"webUrl\":\"https://www.theguardian.com/music/2021/apr/10/music-streaming-debate-what-songwriter-artist-and-industry-insider-say-publication-parliamentary-report\",\"apiUrl\":\"https://content.guardianapis.com/music/2021/apr/10/music-streaming-debate-what-songwriter-artist-and-industry-insider-say-publication-parliamentary-report\",\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"commentisfree/2021/jun/11/bbc-drama-britain-prisons-jimmy-mcgovern-time\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2021-06-11T14:03:23Z\",\"webTitle\":\"Will it take a BBC drama to finally start a rational debate about Britain\u2019s prisons? | Owen Jones\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2021/jun/11/bbc-drama-britain-prisons-jimmy-mcgovern-time\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2021/jun/11/bbc-drama-britain-prisons-jimmy-mcgovern-time\",\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"},{\"id\":\"world/2021/mar/10/mps-hit-back-after-india-summons-envoy-over-farmers-protest-debate\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2021-03-10T16:29:33Z\",\"webTitle\":\"MPs hit back after India summons envoy over farmers' protest debate\",\"webUrl\":\"https://www.theguardian.com/world/2021/mar/10/mps-hit-back-after-india-summons-envoy-over-farmers-protest-debate\",\"apiUrl\":\"https://content.guardianapis.com/world/2021/mar/10/mps-hit-back-after-india-summons-envoy-over-farmers-protest-debate\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"uk-news/2021/feb/21/keep-out-of-bristols-slaver-street-names-debate-ministers-are-told\",\"type\":\"article\",\"sectionId\":\"uk-news\",\"sectionName\":\"UK news\",\"webPublicationDate\":\"2021-02-21T08:15:55Z\",\"webTitle\":\"Keep out of Bristol\u2019s slaver street names debate, ministers are told\",\"webUrl\":\"https://www.theguardian.com/uk-news/2021/feb/21/keep-out-of-bristols-slaver-street-names-debate-ministers-are-told\",\"apiUrl\":\"https://content.guardianapis.com/uk-news/2021/feb/21/keep-out-of-bristols-slaver-street-names-debate-ministers-are-told\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"society/2021/jan/18/tories-oppose-universal-credit-cut-keir-starmer-labour\",\"type\":\"article\",\"sectionId\":\"society\",\"sectionName\":\"Society\",\"webPublicationDate\":\"2021-01-18T14:35:48Z\",\"webTitle\":\"Boris Johnson believes political debate should be 'kind and civil'\",\"webUrl\":\"https://www.theguardian.com/society/2021/jan/18/tories-oppose-universal-credit-cut-keir-starmer-labour\",\"apiUrl\":\"https://content.guardianapis.com/society/2021/jan/18/tories-oppose-universal-credit-cut-keir-starmer-labour\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}]}}";

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
    public static ArrayList<News> extractNews() {

        // Create an empty ArrayList that we can start adding articles to.
        ArrayList<News> articles = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the SAMPLE_JSON_RESPONSE string
            JSONObject baseJsonResponse = new JSONObject(SAMPLE_JSON_RESPONSE);

            // Extract the JSONObject associated with the key called "response".
            JSONObject responseObject = baseJsonResponse.getJSONObject("response");

            // Extract the JSONArray associated with the key called "results",
            // which represents a list of articles.
            JSONArray resultsArray = responseObject.getJSONArray("results");

            // For each earthquake in the earthquakeArray, create an {@link Earthquake} object
            for (int i = 0; i < resultsArray.length(); i++) {

                // Get a single article at position i within the list of news.
                JSONObject currentEarthquake = resultsArray.getJSONObject(i);

                // Extract the value for the key called "mag"
                String webTitle = currentEarthquake.getString("webTitle");

                // Extract the value for the key called "place"
                String sectionName = currentEarthquake.getString("sectionName");

                // Extract the value for the key called "time"
                String nameOfAuthor = "test";

                // Extract the value for the key called "time"
                String publicationDate = currentEarthquake.getString("webPublicationDate");

                // Create a new {@link Earthquake} object with the magnitude, location, time,
                // and url from the JSON response.
                News article = new News(webTitle, sectionName, nameOfAuthor, publicationDate);

                // Add the new {@link Earthquake} to the list of earthquakes.
                articles.add(article);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return articles;
    }
}
