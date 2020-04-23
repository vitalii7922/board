package com.tsystems.javaschool.data;

import com.tsystems.javaschool.dto.ScheduleDto;
import com.tsystems.javaschool.service.ScheduleService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ScheduleBean {

    @Inject
    ScheduleService scheduleService;

    private List<ScheduleDto> scheduleDtoList;

    String page = "schedule";

    public List<ScheduleDto> getSchedules() {
        return scheduleDtoList;
    }

    public String view() {
        return page;
    }

    @PostConstruct
    public void setSchedules(String id) {
        scheduleDtoList = scheduleService.retrieveSchedules(id);
    }
}
