package com.tsystems.javaschool.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.javaschool.dto.ScheduleDto;
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

/**
 * author Vitalii Nefedov
 */
public class ScheduleService {

    @Inject
    Service service;

    private List<ScheduleDto> scheduleDtoList;

    private static final Log log = LogFactory.getLog(ScheduleService.class);

    /**
     *
     * @param id station id
     * @return list of schedule dto by specified url
     */
    public List<ScheduleDto> retrieveSchedules(String id) {
        try {
            URL url = new URL("http://localhost:8181/schedules/" + id);
            HttpURLConnection conn = service.initConnection(url);
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String scheduleListJson = br.readLine();
            ObjectMapper mapper = new ObjectMapper();
            if (scheduleListJson != null) {
                scheduleDtoList = Arrays.asList(mapper.readValue(scheduleListJson, ScheduleDto[].class));
            }
            conn.disconnect();
        } catch (IOException e) {
            log.error(e.getCause());
        }
        return scheduleDtoList;
    }
}
