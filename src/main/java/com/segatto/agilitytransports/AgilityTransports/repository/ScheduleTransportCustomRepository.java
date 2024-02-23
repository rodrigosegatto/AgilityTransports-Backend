package com.segatto.agilitytransports.AgilityTransports.repository;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.segatto.agilitytransports.AgilityTransports.commons.DefaultValuesCustomPaginate;
import com.segatto.agilitytransports.AgilityTransports.commons.PageImplJacksonSerializer;
import com.segatto.agilitytransports.AgilityTransports.commons.util.Util;
import com.segatto.agilitytransports.AgilityTransports.filter.ScheduleTransportFilter;
import com.segatto.agilitytransports.AgilityTransports.entity.ScheduleTransportEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JsonSerialize(using = PageImplJacksonSerializer.class)
public class ScheduleTransportCustomRepository {

    private final Util util;
    private final EntityManager entityManager;
    private String query;
    private TypedQuery queryFinal;

    public ScheduleTransportCustomRepository(EntityManager entityManager, Util util) {
        this.entityManager = entityManager;
        this.util = util;
    }

    public Page<ScheduleTransportEntity> findByFilter(ScheduleTransportFilter filter) {
        this.query = "select ST from ScheduleTransportEntity as ST where 1=1";

        this.addParameterFilterInQuery(filter);
        this.queryFinal = entityManager.createQuery(query, ScheduleTransportEntity.class);
        this.setParameterFilter(filter);

        int firstResult = util.getFirstPageStartZero(filter.getPage(), filter.getSize());
        queryFinal.setFirstResult(firstResult);
        queryFinal.setMaxResults(filter.getSize());
        List<ScheduleTransportEntity> queryListResult = queryFinal.getResultList();

        Long totalElements = this.getTotalSchedulesByFilter(filter);

        Pageable pageable = PageRequest.of(filter.getPage()-1, filter.getSize());
        return new PageImpl(queryListResult, pageable, totalElements);
    }

    private Long getTotalSchedulesByFilter(ScheduleTransportFilter filter) {
        this.query = "select count(1) from ScheduleTransportEntity as ST where 1=1";

        this.addParameterFilterInQuery(filter);
        this.queryFinal = entityManager.createQuery(query, ScheduleTransportEntity.class);
        this.setParameterFilter(filter);

        return (Long) queryFinal.getSingleResult();
    }

    private void addParameterFilterInQuery(ScheduleTransportFilter filter) {
        if (filter.getScheduleDateStart() != null)
            this.query = this.query + " and scheduleDate >= :scheduleDateStart";

        if (filter.getScheduleDateEnd() != null)
            this.query = this.query + " and scheduleDate <= :scheduleDateEnd";
    }

    private void setParameterFilter(ScheduleTransportFilter filter) {
        if (filter.getScheduleDateStart() != null)
            this.queryFinal.setParameter("scheduleDateStart",filter.getScheduleDateStart());

        if (filter.getScheduleDateEnd() != null)
            this.queryFinal.setParameter("scheduleDateEnd",filter.getScheduleDateEnd());
    }

}
