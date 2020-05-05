package com.tsystems.javaschool.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * author Vitalii Nefedov
 */
public class Service {
    /**
     * @param url url for connection
     * @return HttpURLConnection object by specified url
     * @throws IOException if an error occurred connecting to the server
     */
    public HttpURLConnection initConnection(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        return conn;
    }
}
