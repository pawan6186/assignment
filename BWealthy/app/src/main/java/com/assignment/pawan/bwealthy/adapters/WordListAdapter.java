package com.assignment.pawan.bwealthy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.assignment.pawan.bwealthy.AppController;
import com.assignment.pawan.bwealthy.R;
import com.assignment.pawan.bwealthy.models.Word;
import com.assignment.pawan.bwealthy.util.URLHelper;
import com.assignment.pawan.bwealthy.util.Util;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by pawan.b.gupta on 21/10/15.
 */
public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.CustomViewHolder> {
    List<Word> words = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public WordListAdapter(Context context, List<Word> words) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.words = words;
    }

    public void delete(int position) {
        words.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        Word word = words.get(position);
        holder.word.setText(Util.toSentenceCase(word.getWord()));
        holder.wordMeaning.setText(Util.toSentenceCase(word.getMeaning()));
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        holder.icon.setDefaultImageResId(R.drawable.placeholder);
        holder.icon.setImageUrl(URLHelper.API_ENDPOINT + "/images/" + word.getWordId() + ".png", imageLoader);
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.word)
        TextView word;
        @Bind(R.id.icon)
        NetworkImageView icon;
        @Bind(R.id.word_meaning)
        TextView wordMeaning;

        public CustomViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }
}
