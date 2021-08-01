package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * An {@link NewsAdapter} knows how to create a list item layout for each news article
 * in the data source (a list of {@link News} objects).
 */
public class NewsAdapter extends ArrayAdapter<News> {

    /**
     * Create a new {@link NewsAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param news    is the list of {@link News}s to be displayed.
     */
    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }

    /**
     * ViewHolder class.
     */
    static class ViewHolder {
        private TextView title;
        private TextView section;
        private TextView author;
        private TextView date;
    }

    /**
     * Returns a list item view that displays information about the news at the given position
     * in the list of news.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        // Check if an existing view is being reused, otherwise inflate the view.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.title);
            holder.section = convertView.findViewById(R.id.section);
            holder.author = convertView.findViewById(R.id.author);
            holder.date = convertView.findViewById(R.id.date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Get the News object located at this position in the list.
        News currentNewsArticle = getItem(position);

        // Get the text from the currentNewsArticle object and set this text on the title TextView.
        holder.title.setText(currentNewsArticle.getTitle());

        // Get the text from the currentNewsArticle object and set this text on the section TextView.
        holder.section.setText(currentNewsArticle.getSection());

        // Get the text from the currentNewsArticle object and set this text on the author TextView.
        holder.author.setText(currentNewsArticle.getAuthor());

        // Get the text from the currentNewsArticle object and set this text on the date TextView.
        holder.date.setText(currentNewsArticle.getDate());

        // Return the whole list item layout (containing 4 TextView's) so that it can be shown in
        // the ListView.
        return convertView;
    }
}