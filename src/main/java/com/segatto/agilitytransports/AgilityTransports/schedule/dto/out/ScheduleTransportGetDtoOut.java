package com.segatto.agilitytransports.AgilityTransports.schedule.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleTransportGetDtoOut implements Serializable {

    private Long id;
    private String signCode;
    private Instant scheduleDate;
    private String detailDescription;

}
