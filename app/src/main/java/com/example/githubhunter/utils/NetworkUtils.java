package com.example.githubhunter.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    final static String GITHUB_BASE_URL = "https://akabab.github.io/superhero-api/api";

    public static URL buildUrl(String githubSearchQuery) {
        Uri buildUri = null;
        if (githubSearchQuery.isEmpty()){
            buildUri = Uri.parse( GITHUB_BASE_URL).buildUpon()
                    .appendPath("all.json")
                    .build();
        }else{
            buildUri = Uri.parse( GITHUB_BASE_URL).buildUpon()
                    .appendPath("/id/" + githubSearchQuery + ".json")
                    .build();
        }

        URL url = null;

        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConection = (HttpURLConnection) url.openConnection();
        InputStream in = urlConection.getInputStream();
        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("\\A");

        try {
            boolean hasInput = scanner.hasNext();
            if (hasInput) return scanner.next();
            else return null;
        } finally {
            urlConection.disconnect();
        }
    }
}
