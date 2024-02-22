package com.segatto.agilitytransports.AgilityTransports.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleTransportPutDtoIn implements Serializable {

    @JsonProperty("signCode")
    private String signCode;

    @JsonProperty("scheduleDate")
    private Instant scheduleDate;

    @JsonProperty("detailDescription")
    private String detailDescription;

}
