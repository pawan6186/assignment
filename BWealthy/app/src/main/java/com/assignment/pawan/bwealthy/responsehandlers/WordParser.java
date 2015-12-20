package com.assignment.pawan.bwealthy.responsehandlers;

import com.assignment.pawan.bwealthy.models.BaseWrapper;
import com.assignment.pawan.bwealthy.models.Word;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class WordParser implements BaseParser {

    @Override
    public List<BaseWrapper> getParsedData(String data) {
        List<BaseWrapper> words;

        Gson gson = new Gson();

        Type listType = new TypeToken<List<Word>>() {
        }.getType();
        words = new Gson().fromJson(data, listType);

        return words;
    }

}
