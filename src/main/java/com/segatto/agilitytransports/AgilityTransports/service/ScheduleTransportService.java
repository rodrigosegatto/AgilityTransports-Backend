package com.segatto.agilitytransports.AgilityTransports.service;

import com.segatto.agilitytransports.AgilityTransports.filter.ScheduleTransportFilter;
import com.segatto.agilitytransports.AgilityTransports.entity.ScheduleTransportEntity;
import com.segatto.agilitytransports.AgilityTransports.repository.ScheduleTransportCustomRepository;
import com.segatto.agilitytransports.AgilityTransports.repository.ScheduleTransportRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleTransportService {

    @Autowired
    private ScheduleTransportRepository scheduleTransportRepository;

    @Autowired
    private ScheduleTransportCustomRepository scheduleTransportCustomRepository;

    public List<ScheduleTransportEntity> getAllSchedules(ScheduleTransportFilter filter) {
        return scheduleTransportCustomRepository.findByFilter(filter);
    }

    public ScheduleTransportEntity getScheduleById(Long id) {
        ScheduleTransportEntity schedule = scheduleTransportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));

        return scheduleTransportRepository.save(schedule);
    }

    public ScheduleTransportEntity createSchedule(ScheduleTransportEntity schedule) {
        return scheduleTransportRepository.save(schedule);
    }

    public ScheduleTransportEntity updateSchedule(Long id, ScheduleTransportEntity schedule) {
        ScheduleTransportEntity scheduleToBeUpdated = scheduleTransportRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));

        if (schedule.getSignCode() != null)
            scheduleToBeUpdated.setSignCode(schedule.getSignCode());

        if (schedule.getScheduleDate() != null)
            scheduleToBeUpdated.setScheduleDate(schedule.getScheduleDate());

        if (schedule.getDetailDescription() != null)
            scheduleToBeUpdated.setDetailDescription(schedule.getDetailDescription());

        return scheduleTransportRepository.save(scheduleToBeUpdated);
    }

    public void deleteSchedule(Long id) {
        scheduleTransportRepository.deleteById(id);
    }
}
