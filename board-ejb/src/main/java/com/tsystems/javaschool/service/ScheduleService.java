package com.tsystems.javaschool.service;

import org.modelmapper.ModelMapper;
import com.tsystems.javaschool.converter.ScheduleConverter;
import com.tsystems.javaschool.data.ScheduleDao;
import com.tsystems.javaschool.dto.ScheduleDto;
import com.tsystems.javaschool.dto.TrainDto;
import com.tsystems.javaschool.model.Schedule;
import com.tsystems.javaschool.model.Train;

import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public class ScheduleService {

    @Inject
    ScheduleDao scheduleDao;

    @Inject
    ScheduleConverter scheduleConverter;

    private static final ModelMapper modelMapper = new ModelMapper();

    public void addSchedule(TrainDto trainDto, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        Train train = null;
        train = modelMapper.map(trainDto, Train.class);
        Schedule scheduleDeparture = null;
        Schedule scheduleArrival = null;

        scheduleDeparture = new Schedule();
        scheduleDeparture.setTrain(train);
        scheduleDeparture.setDepartureTime(departureTime);
        scheduleDeparture.setStation(train.getOriginStation());
        scheduleDao.create(scheduleDeparture);

        scheduleArrival = new Schedule();
        scheduleArrival.setTrain(train);
        scheduleArrival.setArrivalTime(arrivalTime);
        scheduleArrival.setStation(train.getDestinationStation());
        scheduleDao.create(scheduleArrival);

    }

    public Schedule getScheduleByTrainId(long id) {
        return scheduleDao.findByTrainId(id);
    }


    public List<ScheduleDto> getSchedulesByStationId(long id) {
        List<Schedule> schedules = scheduleDao.findByStationId(id);
        List<ScheduleDto> scheduleDtos = null;
        try {
            if (schedules != null) {
                scheduleDtos = schedules.stream().map(s -> scheduleConverter.convertToScheduleDto(s))
                        .collect(Collectors.toList());
                List<Long> trainsId = new ArrayList<>();

                for (int i = 0; i < scheduleDtos.size(); i++) {
                    for (int j = i + 1; j < scheduleDtos.size(); j++) {
                        if (scheduleDtos.get(i).getTrain().getNumber() == scheduleDtos.get(j).getTrain().getNumber()) {
                            scheduleDtos.get(i).setDepartureTime(scheduleDtos.get(j).getDepartureTime());
                            trainsId.add(scheduleDtos.get(j).getTrain().getId());
                        }
                    }
                }

                Iterator<ScheduleDto> iterator = scheduleDtos.iterator();

                while (iterator.hasNext()) {
                    long trainId = iterator.next().getTrain().getId();
                    if (trainsId.contains(trainId)) {
                        iterator.remove();
                    }
                }
                Collections.sort(scheduleDtos);
            }
        } catch (NullPointerException e) {
        }
        return scheduleDtos;
    }
}
