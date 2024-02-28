package com.segatto.agilitytransports.AgilityTransports.schedule.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleTransportPostDtoIn implements Serializable {

    @JsonProperty("signCode")
    private String signCode;

    @JsonProperty("scheduleDate")
    private Instant scheduleDate;

    @JsonProperty("detailDescription")
    @Size(min = 3, message = "Field of description must be at least 3 characters long")
    private String detailDescription;

}
