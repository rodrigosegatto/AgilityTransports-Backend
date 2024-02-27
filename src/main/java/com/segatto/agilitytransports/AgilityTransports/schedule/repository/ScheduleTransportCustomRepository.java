package com.segatto.agilitytransports.AgilityTransports.schedule.repository;

import com.segatto.agilitytransports.AgilityTransports.commons.util.PaginationSort;
import com.segatto.agilitytransports.AgilityTransports.commons.util.Util;
import com.segatto.agilitytransports.AgilityTransports.schedule.filter.ScheduleTransportFilter;
import com.segatto.agilitytransports.AgilityTransports.schedule.entity.ScheduleTransportEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScheduleTransportCustomRepository {

    private final Util util;
    private final PaginationSort paginationSort;
    private final EntityManager entityManager;
    private String query;
    private TypedQuery queryFinal;

    public ScheduleTransportCustomRepository(EntityManager entityManager,
                                             Util util,
                                             PaginationSort paginationSort) {
        this.entityManager = entityManager;
        this.util = util;
        this.paginationSort = paginationSort;
    }

    public Page<ScheduleTransportEntity> findByFilter(ScheduleTransportFilter filter, List<String> sort) {
        query = "select ST from ScheduleTransportEntity as ST where 1=1";

        addParameterFilterInQuery(filter);
        addParameterOrderByInQuery(sort);
        queryFinal = entityManager.createQuery(query, ScheduleTransportEntity.class);
        setParameterFilter(filter);

        int firstResult = util.getFirstPageStartZero(filter.getPage(), filter.getSize());
        queryFinal.setFirstResult(firstResult);
        queryFinal.setMaxResults(filter.getSize());
        List<ScheduleTransportEntity> queryListResult = queryFinal.getResultList();

        Long totalElements = getTotalSchedulesByFilter(filter);

        Pageable pageable = PageRequest.of(filter.getPage()-1, filter.getSize());
        return new PageImpl(queryListResult, pageable, totalElements);
    }

    private Long getTotalSchedulesByFilter(ScheduleTransportFilter filter) {
        query = "select count(1) from ScheduleTransportEntity as ST where 1=1";

        addParameterFilterInQuery(filter);
        queryFinal = entityManager.createQuery(query, ScheduleTransportEntity.class);
        setParameterFilter(filter);

        return (Long) queryFinal.getSingleResult();
    }

    private void addParameterFilterInQuery(ScheduleTransportFilter filter) {
        if (filter.getScheduleDateStart() != null)
            query = query.concat(" and scheduleDate >= :scheduleDateStart");

        if (filter.getScheduleDateEnd() != null)
            query = query.concat(" and scheduleDate <= :scheduleDateEnd");
    }

    private void setParameterFilter(ScheduleTransportFilter filter) {
        if (filter.getScheduleDateStart() != null)
            queryFinal.setParameter("scheduleDateStart",filter.getScheduleDateStart());

        if (filter.getScheduleDateEnd() != null)
            queryFinal.setParameter("scheduleDateEnd",filter.getScheduleDateEnd());
    }

    private void addParameterOrderByInQuery(List<String> sort) {
        query = query.concat(" ").concat(paginationSort.getOrderByConvertedToString(sort));
    }

}
