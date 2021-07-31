package com.example.newsapp;

public class News {

    /**
     * Title of the News.
     */
    private final String mTitle;

    /**
     * Location of the News.
     */
    private final String mSection;

    /**
     * Author of the News.
     */
    private final String mAuthor;

    /**
     * Date of the News.
     */
    private final String mDate;

    /**
     * Create a new CityCategoryItem object.
     *
     * @param title   is the title of the article.
     * @param section is the section the article belongs to.
     * @param author  is the author of the article.
     * @param date    is the date the article was published.
     */
    public News(String title, String section, String author, String date) {
        mTitle = title;
        mSection = section;
        mAuthor = author;
        mDate = date;
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
}
