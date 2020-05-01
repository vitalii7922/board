package com.tsystems.javaschool.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Service {
    public HttpURLConnection initConnection(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        return conn;
    }
}
