package com.segatto.agilitytransports.AgilityTransports.schedule.repository;

import com.segatto.agilitytransports.AgilityTransports.schedule.entity.ScheduleTransportEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleTransportRepository extends JpaRepository<ScheduleTransportEntity, Long> {

    Page<ScheduleTransportEntity> findBySignCode(String signCode, Pageable pageable);

}
