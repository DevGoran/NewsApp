package com.example.newsapp;

/**
 * Custom class for news articles.
 */
public class News {

    /**
     * Title of the News article.
     */
    private final String mTitle;

    /**
     * Section of the News article.
     */
    private final String mSection;

    /**
     * Author of the News article.
     */
    private final String mAuthor;

    /**
     * When the article was published.
     */
    private final String mDate;

    /**
     * URL of the News article.
     */
    private final String mUrl;

    /**
     * Create a new CityCategoryItem object.
     *
     * @param title   is the title of the article.
     * @param section is the section the article belongs to.
     * @param author  is the author of the article.
     * @param date    is the date the article was published.
     * @param url     is the URL of the article.
     */
    public News(String title, String section, String author, String date, String url) {
        mTitle = title;
        mSection = section;
        mAuthor = author;
        mDate = date;
        mUrl = url;
    }

    /**
     * Returns title of the article.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Returns section of the article.
     */
    public String getSection() {
        return mSection;
    }

    /**
     * Returns author of the article.
     */
    public String getAuthor() {
        return mAuthor;
    }

    /**
     * Returns when the article was posted.
     */
    public String getDate() {
        return mDate;
    }

    /**
     * Returns the URL of the article.
     */
    public String getUrl() {
        return mUrl;
    }
}