package com.segatto.agilitytransports.AgilityTransports.service;

import com.segatto.agilitytransports.AgilityTransports.entity.ScheduleTransportEntity;
import com.segatto.agilitytransports.AgilityTransports.repository.ScheduleTransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleTransportService {

    @Autowired
    private ScheduleTransportRepository scheduleTransportRepository;

    public List<ScheduleTransportEntity> getAllSchedules() {
        return scheduleTransportRepository.findAll();
    }

    public ScheduleTransportEntity createSchedule(ScheduleTransportEntity schedule) {
        return scheduleTransportRepository.save(schedule);
    }
}
