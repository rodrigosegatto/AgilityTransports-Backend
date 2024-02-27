package com.segatto.agilitytransports.AgilityTransports.schedule.controller;

import com.segatto.agilitytransports.AgilityTransports.commons.mapper.PageableMapper;
import com.segatto.agilitytransports.AgilityTransports.commons.messages.MessagesReturnResponse;
import com.segatto.agilitytransports.AgilityTransports.schedule.filter.ScheduleTransportFilter;
import com.segatto.agilitytransports.AgilityTransports.schedule.dto.in.ScheduleTransportPostDtoIn;
import com.segatto.agilitytransports.AgilityTransports.schedule.dto.in.ScheduleTransportPutDtoIn;
import com.segatto.agilitytransports.AgilityTransports.schedule.dto.out.ScheduleTransportGetDtoOut;
import com.segatto.agilitytransports.AgilityTransports.schedule.dto.out.ScheduleTransportPostDtoOut;
import com.segatto.agilitytransports.AgilityTransports.schedule.dto.out.ScheduleTransportPutDtoOut;
import com.segatto.agilitytransports.AgilityTransports.schedule.entity.ScheduleTransportEntity;
import com.segatto.agilitytransports.AgilityTransports.schedule.mapper.ScheduleTransportMapper;
import com.segatto.agilitytransports.AgilityTransports.schedule.service.ScheduleTransportService;
import com.segatto.agilitytransports.AgilityTransports.schedule.response.ScheduleTransportResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Autowired
    private PageableMapper pageableMapper;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses(
        value = {
            @ApiResponse(
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = ScheduleTransportResponse.class)))
                })
        })
    public Page<ScheduleTransportGetDtoOut> getAllSchedules(@ParameterObject Pageable pageable) {
        Page<ScheduleTransportEntity> schedulesPageable = scheduleTransportService.getAllSchedules(pageable);
        return pageableMapper.mapEntityPageIntoDtoPage(schedulesPageable, ScheduleTransportGetDtoOut.class);
    }

    @GetMapping("/search")
    @ResponseStatus(code = HttpStatus.OK)
    public Page<ScheduleTransportGetDtoOut> getAllSchedulesByFilter(@ParameterObject @ModelAttribute ScheduleTransportFilter filter,
                                                                 @RequestParam(required = false)
                                                                 @Parameter(name = "sort", description = "Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")
                                                                         List<String> sort) {
        Page<ScheduleTransportEntity> schedulesPageable = scheduleTransportService.getAllSchedulesByFilter(filter, sort);
        return pageableMapper.mapEntityPageIntoDtoPage(schedulesPageable, ScheduleTransportGetDtoOut.class);
    }

    @GetMapping("/search/by-sign-code")
    @ResponseStatus(code = HttpStatus.OK)
    public Page<ScheduleTransportGetDtoOut> getAllSchedules(@RequestParam @Parameter(description = "Sign Code") String signCode,
                                                         @ParameterObject Pageable pageable) {
        Page<ScheduleTransportEntity> schedulesPageable = scheduleTransportService.getAllSchedulesBySignCode(signCode, pageable);
        return pageableMapper.mapEntityPageIntoDtoPage(schedulesPageable, ScheduleTransportGetDtoOut.class);
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ScheduleTransportGetDtoOut getScheduleById(@PathVariable @Parameter(description = "Id of schedule") Long id){
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
