package com.example.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * Sample JSON response for a query
     * "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test"
     */
    private static final String JSON_RESPONSE = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":18255,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":1826,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"commentisfree/2021/jul/24/vaccine-passports-rights-freedoms-covid\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2021-07-24T11:00:21Z\",\"webTitle\":\"It's time for a serious debate about vaccine passports | Shami Chakrabarti\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2021/jul/24/vaccine-passports-rights-freedoms-covid\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2021/jul/24/vaccine-passports-rights-freedoms-covid\",\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"},{\"id\":\"politics/2021/jul/01/labour-mp-shares-his-struggles-with-addiction-in-commons-debate-for-pride\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2021-07-01T18:00:15Z\",\"webTitle\":\"Labour MP shares addiction struggles in Commons Pride debate\",\"webUrl\":\"https://www.theguardian.com/politics/2021/jul/01/labour-mp-shares-his-struggles-with-addiction-in-commons-debate-for-pride\",\"apiUrl\":\"https://content.guardianapis.com/politics/2021/jul/01/labour-mp-shares-his-struggles-with-addiction-in-commons-debate-for-pride\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"commentisfree/2021/may/22/the-free-speech-law-will-make-university-debate-harder-not-easier\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2021-05-22T09:00:56Z\",\"webTitle\":\"The 'free speech' law will make university debate harder, not easier | David Renton\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2021/may/22/the-free-speech-law-will-make-university-debate-harder-not-easier\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2021/may/22/the-free-speech-law-will-make-university-debate-harder-not-easier\",\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"},{\"id\":\"business/2021/may/21/rich-list-2021-results-will-renew-debate-on-wealth-tax-in-britain\",\"type\":\"article\",\"sectionId\":\"business\",\"sectionName\":\"Business\",\"webPublicationDate\":\"2021-05-21T16:05:10Z\",\"webTitle\":\"Rich List 2021: results will renew debate on wealth tax in Britain\",\"webUrl\":\"https://www.theguardian.com/business/2021/may/21/rich-list-2021-results-will-renew-debate-on-wealth-tax-in-britain\",\"apiUrl\":\"https://content.guardianapis.com/business/2021/may/21/rich-list-2021-results-will-renew-debate-on-wealth-tax-in-britain\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2021/mar/11/snp-focus-on-scottish-independence-is-wrong-debate-keir-starmer\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2021-03-11T17:06:03Z\",\"webTitle\":\"SNP focus on Scottish independence is 'wrong debate', says Starmer \",\"webUrl\":\"https://www.theguardian.com/politics/2021/mar/11/snp-focus-on-scottish-independence-is-wrong-debate-keir-starmer\",\"apiUrl\":\"https://content.guardianapis.com/politics/2021/mar/11/snp-focus-on-scottish-independence-is-wrong-debate-keir-starmer\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"music/2021/apr/10/music-streaming-debate-what-songwriter-artist-and-industry-insider-say-publication-parliamentary-report\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2021-04-10T07:00:01Z\",\"webTitle\":\"The music streaming debate: what the artists, songwriters and industry insiders say\",\"webUrl\":\"https://www.theguardian.com/music/2021/apr/10/music-streaming-debate-what-songwriter-artist-and-industry-insider-say-publication-parliamentary-report\",\"apiUrl\":\"https://content.guardianapis.com/music/2021/apr/10/music-streaming-debate-what-songwriter-artist-and-industry-insider-say-publication-parliamentary-report\",\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"commentisfree/2021/jun/11/bbc-drama-britain-prisons-jimmy-mcgovern-time\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2021-06-11T14:03:23Z\",\"webTitle\":\"Will it take a BBC drama to finally start a rational debate about Britain\u2019s prisons? | Owen Jones\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2021/jun/11/bbc-drama-britain-prisons-jimmy-mcgovern-time\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2021/jun/11/bbc-drama-britain-prisons-jimmy-mcgovern-time\",\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"},{\"id\":\"world/2021/mar/10/mps-hit-back-after-india-summons-envoy-over-farmers-protest-debate\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2021-03-10T16:29:33Z\",\"webTitle\":\"MPs hit back after India summons envoy over farmers' protest debate\",\"webUrl\":\"https://www.theguardian.com/world/2021/mar/10/mps-hit-back-after-india-summons-envoy-over-farmers-protest-debate\",\"apiUrl\":\"https://content.guardianapis.com/world/2021/mar/10/mps-hit-back-after-india-summons-envoy-over-farmers-protest-debate\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"uk-news/2021/feb/21/keep-out-of-bristols-slaver-street-names-debate-ministers-are-told\",\"type\":\"article\",\"sectionId\":\"uk-news\",\"sectionName\":\"UK news\",\"webPublicationDate\":\"2021-02-21T08:15:55Z\",\"webTitle\":\"Keep out of Bristol\u2019s slaver street names debate, ministers are told\",\"webUrl\":\"https://www.theguardian.com/uk-news/2021/feb/21/keep-out-of-bristols-slaver-street-names-debate-ministers-are-told\",\"apiUrl\":\"https://content.guardianapis.com/uk-news/2021/feb/21/keep-out-of-bristols-slaver-street-names-debate-ministers-are-told\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"society/2021/jan/18/tories-oppose-universal-credit-cut-keir-starmer-labour\",\"type\":\"article\",\"sectionId\":\"society\",\"sectionName\":\"Society\",\"webPublicationDate\":\"2021-01-18T14:35:48Z\",\"webTitle\":\"Boris Johnson believes political debate should be 'kind and civil'\",\"webUrl\":\"https://www.theguardian.com/society/2021/jan/18/tories-oppose-universal-credit-cut-keir-starmer-labour\",\"apiUrl\":\"https://content.guardianapis.com/society/2021/jan/18/tories-oppose-universal-credit-cut-keir-starmer-labour\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}]}}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Deactivate night mode on app.
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        // Inflate the layout.
        setContentView(R.layout.activity_main);

        // Get the list of articles from {@link QueryUtils}
        ArrayList<News> articles = QueryUtils.extractFeatureFromJson(JSON_RESPONSE);

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