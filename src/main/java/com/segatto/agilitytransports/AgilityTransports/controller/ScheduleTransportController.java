package com.segatto.agilitytransports.AgilityTransports.controller;

import com.segatto.agilitytransports.AgilityTransports.commons.messages.MessagesReturnResponse;
import com.segatto.agilitytransports.AgilityTransports.entity.ScheduleTransportEntity;
import com.segatto.agilitytransports.AgilityTransports.service.ScheduleTransportService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/schedule-transport")
public class ScheduleTransportController {

    @Autowired
    private ScheduleTransportService scheduleTransportService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<ScheduleTransportEntity> getAllSchedules() {
        return scheduleTransportService.getAllSchedules();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ScheduleTransportEntity getScheduleById(@PathVariable Long id){
        try {
            return scheduleTransportService.getScheduleById(id);
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MessagesReturnResponse.NOT_FOUND_REGISTRY, ex);
        }
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ScheduleTransportEntity createSchedule(@RequestBody ScheduleTransportEntity schedule) {
        return scheduleTransportService.createSchedule(schedule);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ScheduleTransportEntity updateSchedule(@PathVariable Long id,
                                                  @RequestBody ScheduleTransportEntity schedule) {
        try {
            return scheduleTransportService.updateSchedule(id, schedule);
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MessagesReturnResponse.NOT_FOUND_REGISTRY, ex);
        }
    }

}
