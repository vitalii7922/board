package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.dto.ScheduleDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScheduleController {

    private ScheduleDto scheduleDto;

    public static void main(String[] args) {

        try {
            URL url = new URL("http://localhost:8181/schedules/421");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
