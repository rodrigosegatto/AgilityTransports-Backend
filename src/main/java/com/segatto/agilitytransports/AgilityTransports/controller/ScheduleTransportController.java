package com.segatto.agilitytransports.AgilityTransports.controller;

import com.segatto.agilitytransports.AgilityTransports.commons.PaginationSort;
import com.segatto.agilitytransports.AgilityTransports.commons.messages.MessagesReturnResponse;
import com.segatto.agilitytransports.AgilityTransports.filter.ScheduleTransportFilter;
import com.segatto.agilitytransports.AgilityTransports.dto.in.ScheduleTransportPostDtoIn;
import com.segatto.agilitytransports.AgilityTransports.dto.in.ScheduleTransportPutDtoIn;
import com.segatto.agilitytransports.AgilityTransports.dto.out.ScheduleTransportGetDtoOut;
import com.segatto.agilitytransports.AgilityTransports.dto.out.ScheduleTransportPostDtoOut;
import com.segatto.agilitytransports.AgilityTransports.dto.out.ScheduleTransportPutDtoOut;
import com.segatto.agilitytransports.AgilityTransports.entity.ScheduleTransportEntity;
import com.segatto.agilitytransports.AgilityTransports.mapper.ScheduleTransportMapper;
import com.segatto.agilitytransports.AgilityTransports.service.ScheduleTransportService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.EntityNotFoundException;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/schedule-transport")
public class ScheduleTransportController {

    @Autowired
    private ScheduleTransportService scheduleTransportService;

    @Autowired
    private ScheduleTransportMapper scheduleTransportMapper;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Page<ScheduleTransportEntity> getAllSchedules(@ParameterObject Pageable pageable) {
        return scheduleTransportService.getAllSchedules(pageable);
    }

    @GetMapping("/search")
    @ResponseStatus(code = HttpStatus.OK)
    public Page<ScheduleTransportEntity> getAllSchedulesByFilter(@ParameterObject @ModelAttribute ScheduleTransportFilter filter,
                                                                 @RequestParam(required = false)
                                                                 @Parameter(name = "sort", description = "Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")
                                                                         List<String> sort) {
        return scheduleTransportService.getAllSchedulesByFilter(filter, sort);
        /*Page<ScheduleTransportEntity> schedules = scheduleTransportService.getAllSchedulesByFilter(filter);

        List<ScheduleTransportGetDtoOut> schedulesDtoOut = schedules.stream()
                .map(schedule -> scheduleTransportMapper.convertEntityToGetDtoOut(schedule))
                .collect(Collectors.toList());

        return schedulesDtoOut;*/
    }

    @GetMapping("/search/by-sign-code")
    @ResponseStatus(code = HttpStatus.OK)
    public Page<ScheduleTransportEntity> getAllSchedules(@RequestParam String signCode,
                                                         Pageable pageable) {
        return scheduleTransportService.getAllSchedulesBySignCode(signCode, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ScheduleTransportGetDtoOut getScheduleById(@PathVariable Long id){
        try {
            ScheduleTransportEntity schedule = scheduleTransportService.getScheduleById(id);
            ScheduleTransportGetDtoOut scheduleDtoOut = scheduleTransportMapper.convertEntityToGetDtoOut(schedule);
            return scheduleDtoOut;
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MessagesReturnResponse.NOT_FOUND_REGISTRY, ex);
        }
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ScheduleTransportPostDtoOut createSchedule(@RequestBody ScheduleTransportPostDtoIn scheduleDtoIn) {
        ScheduleTransportEntity schedule = scheduleTransportMapper.convertPostDtoInToEntity(scheduleDtoIn);
        schedule = scheduleTransportService.createSchedule(schedule);
        ScheduleTransportPostDtoOut scheduleDtoOut = scheduleTransportMapper.convertEntityToPostDtoOut(schedule);
        return scheduleDtoOut;
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ScheduleTransportPutDtoOut updateSchedule(@PathVariable Long id,
                                                  @RequestBody ScheduleTransportPutDtoIn scheduleDtoIn) {
        try {
            ScheduleTransportEntity schedule = scheduleTransportMapper.convertPutDtoInToEntity(scheduleDtoIn);
            schedule = scheduleTransportService.updateSchedule(id, schedule);
            ScheduleTransportPutDtoOut scheduleDtoOut = scheduleTransportMapper.convertEntityToPutDtoOut(schedule);
            return scheduleDtoOut;
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MessagesReturnResponse.NOT_FOUND_REGISTRY, ex);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteSchedule(@PathVariable Long id) {
        scheduleTransportService.deleteSchedule(id);
    }

}
