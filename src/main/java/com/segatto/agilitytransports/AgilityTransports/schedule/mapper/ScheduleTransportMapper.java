package com.segatto.agilitytransports.AgilityTransports.schedule.mapper;

import com.segatto.agilitytransports.AgilityTransports.commons.mapper.PageableMapper;
import com.segatto.agilitytransports.AgilityTransports.schedule.dto.in.ScheduleTransportPostDtoIn;
import com.segatto.agilitytransports.AgilityTransports.schedule.dto.in.ScheduleTransportPutDtoIn;
import com.segatto.agilitytransports.AgilityTransports.schedule.dto.out.ScheduleTransportGetDtoOut;
import com.segatto.agilitytransports.AgilityTransports.schedule.dto.out.ScheduleTransportPostDtoOut;
import com.segatto.agilitytransports.AgilityTransports.schedule.dto.out.ScheduleTransportPutDtoOut;
import com.segatto.agilitytransports.AgilityTransports.schedule.entity.ScheduleTransportEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTransportMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ScheduleTransportGetDtoOut convertEntityToGetDtoOut(ScheduleTransportEntity schedule) {
        ScheduleTransportGetDtoOut getScheduleDtoOut = modelMapper.map(schedule, ScheduleTransportGetDtoOut.class);
        return getScheduleDtoOut;
    }

    public ScheduleTransportEntity convertPostDtoInToEntity(ScheduleTransportPostDtoIn postScheduleDtoIn) {
        ScheduleTransportEntity schedule = modelMapper.map(postScheduleDtoIn, ScheduleTransportEntity.class);
        return schedule;
    }

    public ScheduleTransportPostDtoOut convertEntityToPostDtoOut(ScheduleTransportEntity schedule) {
        ScheduleTransportPostDtoOut postScheduleDtoOut = modelMapper.map(schedule, ScheduleTransportPostDtoOut.class);
        return postScheduleDtoOut;
    }

    public ScheduleTransportEntity convertPutDtoInToEntity(ScheduleTransportPutDtoIn putScheduleDtoIn) {
        ScheduleTransportEntity schedule = modelMapper.map(putScheduleDtoIn, ScheduleTransportEntity.class);
        return schedule;
    }

    public ScheduleTransportPutDtoOut convertEntityToPutDtoOut(ScheduleTransportEntity schedule) {
        ScheduleTransportPutDtoOut putScheduleDtoOut = modelMapper.map(schedule, ScheduleTransportPutDtoOut.class);
        return putScheduleDtoOut;
    }

}
