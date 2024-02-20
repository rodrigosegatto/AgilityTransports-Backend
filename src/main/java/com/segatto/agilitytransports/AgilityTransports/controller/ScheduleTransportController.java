package com.segatto.agilitytransports.AgilityTransports.controller;

import com.segatto.agilitytransports.AgilityTransports.entity.ScheduleTransportEntity;
import com.segatto.agilitytransports.AgilityTransports.service.ScheduleTransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule-transport")
public class ScheduleTransportController {

    @Autowired
    private ScheduleTransportService scheduleTransportService;

    @GetMapping
    public List<ScheduleTransportEntity> getAllSchedules() {
        return scheduleTransportService.getAllSchedules();
    }

    @PostMapping
    public ScheduleTransportEntity createUser(@RequestBody ScheduleTransportEntity schedule) {
        return scheduleTransportService.createSchedule(schedule);
    }

}
