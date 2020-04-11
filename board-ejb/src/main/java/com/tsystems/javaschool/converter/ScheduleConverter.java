package com.tsystems.javaschool.converter;


import com.tsystems.javaschool.dto.ScheduleDto;
import com.tsystems.javaschool.model.Schedule;

import javax.inject.Inject;

public class ScheduleConverter {

    @Inject
    TimeConverter timeConverter;

    public ScheduleDto convertToScheduleDto(Schedule schedule) {
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setId(schedule.getId());
        scheduleDto.setTrain(schedule.getTrain());
        scheduleDto.setStation(schedule.getStation());
        if (schedule.getArrivalTime() != null) {
            scheduleDto.setArrivalTime(timeConverter.convertDateTime(schedule.getArrivalTime()));
        }
        if (schedule.getDepartureTime() != null) {
            scheduleDto.setDepartureTime(timeConverter.convertDateTime(schedule.getDepartureTime()));
        }
        return scheduleDto;
    }
}
