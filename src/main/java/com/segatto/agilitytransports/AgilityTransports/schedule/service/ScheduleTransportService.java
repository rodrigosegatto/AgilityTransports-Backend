package com.segatto.agilitytransports.AgilityTransports.schedule.service;

import com.segatto.agilitytransports.AgilityTransports.schedule.filter.ScheduleTransportFilter;
import com.segatto.agilitytransports.AgilityTransports.schedule.entity.ScheduleTransportEntity;
import com.segatto.agilitytransports.AgilityTransports.schedule.repository.ScheduleTransportCustomRepository;
import com.segatto.agilitytransports.AgilityTransports.schedule.repository.ScheduleTransportRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleTransportService {

    @Autowired
    private ScheduleTransportRepository scheduleTransportRepository;

    @Autowired
    private ScheduleTransportCustomRepository scheduleTransportCustomRepository;

    public Page<ScheduleTransportEntity> getAllSchedules(Pageable pageable) {
        return scheduleTransportRepository.findAll(pageable);
    }

    public Page<ScheduleTransportEntity> getAllSchedulesBySignCode(String signCode, Pageable pageable) {
        return scheduleTransportRepository.findBySignCode(signCode, pageable);
    }

    public Page<ScheduleTransportEntity> getAllSchedulesByFilter(ScheduleTransportFilter filter, List<String> sort) {
        return scheduleTransportCustomRepository.findByFilter(filter, sort);
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
