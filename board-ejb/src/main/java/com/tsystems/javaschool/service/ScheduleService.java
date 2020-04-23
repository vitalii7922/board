package com.tsystems.javaschool.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.javaschool.dto.ScheduleDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


public class ScheduleService {

    private List<ScheduleDto> scheduleDtoList;

    public List<ScheduleDto> retrieveSchedules(String id) {
        try {
            URL url = new URL("http://localhost:8181/schedules/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output = br.readLine();
            ObjectMapper mapper = new ObjectMapper();
            if (output != null) {
                scheduleDtoList = Arrays.asList(mapper.readValue(output, ScheduleDto[].class));
            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scheduleDtoList;
    }
}
