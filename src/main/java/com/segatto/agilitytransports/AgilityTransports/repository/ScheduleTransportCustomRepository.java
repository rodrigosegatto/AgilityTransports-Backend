package com.segatto.agilitytransports.AgilityTransports.repository;

import com.segatto.agilitytransports.AgilityTransports.filter.ScheduleTransportFilter;
import com.segatto.agilitytransports.AgilityTransports.entity.ScheduleTransportEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScheduleTransportCustomRepository {

    private final EntityManager entityManager;
    private String query;
    private TypedQuery queryFinal;

    public ScheduleTransportCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<ScheduleTransportEntity> findByFilter(ScheduleTransportFilter filter) {
        this.query = "select ST from ScheduleTransportEntity as ST where 1=1";

        this.addParameterFilterInQuery(filter);

        this.queryFinal = entityManager.createQuery(query, ScheduleTransportEntity.class);

        this.setParameterFilter(filter);

        return queryFinal.getResultList();
    }

    public Long getTotalSchedulesByFilter(ScheduleTransportFilter filter) {
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
