package com.segatto.agilitytransports.AgilityTransports.repository;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.segatto.agilitytransports.AgilityTransports.commons.PageImplJacksonSerializer;
import com.segatto.agilitytransports.AgilityTransports.entity.ScheduleTransportEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@JsonSerialize(using = PageImplJacksonSerializer.class)
public interface ScheduleTransportRepository extends JpaRepository<ScheduleTransportEntity, Long> {

    Page<ScheduleTransportEntity> findBySignCode(String signCode, Pageable pageable);

}
