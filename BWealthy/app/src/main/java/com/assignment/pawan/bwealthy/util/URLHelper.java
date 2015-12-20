package com.assignment.pawan.bwealthy.util;


public class URLHelper {


    public static String API_ENDPOINT = "http://appsculture.com/vocab";
    public static String WORD_LIST = "/words.json";
    public static final String none = "";

    public static String getAPIEndpointURL(String requstAPI) {
        return String.format("%s%s", API_ENDPOINT, requstAPI);
    }


}
