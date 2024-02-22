package com.segatto.agilitytransports.AgilityTransports.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleTransportFilter {

    @JsonProperty("scheduleDateStart")
    private Instant scheduleDateStart;

    @JsonProperty("scheduleDateEnd")
    private Instant scheduleDateEnd;

}
