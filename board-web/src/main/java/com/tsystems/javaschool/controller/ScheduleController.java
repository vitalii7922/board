package com.tsystems.javaschool.controller;


import com.tsystems.javaschool.service.ScheduleService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class ScheduleController {

    @Inject
    private ScheduleService scheduleService;
}
