package com.tsystems.javaschool.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.javaschool.dto.StationDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class StationService {

    private List<StationDto> stationList;

    @Inject
    Service service;

    private static final Log log = LogFactory.getLog(StationService.class);

    public List<StationDto> retrieveStations() {
        try {
            URL url = new URL("http://localhost:8181/stations");
            HttpURLConnection conn = service.initConnection(url);
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String stationListJson = br.readLine();
            ObjectMapper mapper = new ObjectMapper();
            if (stationListJson != null) {
                this.stationList = Arrays.asList(mapper.readValue(stationListJson, StationDto[].class));
            }
            conn.disconnect();
        } catch (IOException e) {
            log.error(e.getCause());
        }
        return stationList;
    }
}
