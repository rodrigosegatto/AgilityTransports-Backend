package com.segatto.agilitytransports.AgilityTransports.repository;

import com.segatto.agilitytransports.AgilityTransports.entity.ScheduleTransportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleTransportRepository extends JpaRepository<ScheduleTransportEntity, Long> {

}
