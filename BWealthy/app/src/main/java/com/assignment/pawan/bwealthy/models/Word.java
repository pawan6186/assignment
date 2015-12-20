package com.assignment.pawan.bwealthy.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pawan.b.gupta on 29/08/15.
 */
public class Word extends BaseWrapper {
    @SerializedName("id")
    private int WordId;
    private String word;
    private int variant;
    private String meaning;
    private double ratio;

    public int getWordId() {
        return WordId;
    }

    public void setWordId(int wordId) {
        WordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getVariant() {
        return variant;
    }

    public void setVariant(int variant) {
        this.variant = variant;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
