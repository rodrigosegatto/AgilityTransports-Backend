package com.segatto.agilitytransports.AgilityTransports.schedule.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.segatto.agilitytransports.AgilityTransports.commons.DefaultValuesCustomPaginate;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleTransportFilter {

    @Parameter(name = "scheduleDateStart", description = "Start schedule date", example = "2023-02-19T00:00:00Z")
    private Instant scheduleDateStart;

    @Parameter(name = "scheduleDateEnd", description = "End schedule date", example = "2023-02-20T00:00:00Z")
    private Instant scheduleDateEnd;

    @Parameter(name = "page", description = "One-based page index (1..N). Default is 1", example = "1")
    private int page;

    @Parameter(name = "size", description = "The size of the page to be returned. Default is 20", example = "20")
    private int size = Integer.parseInt(DefaultValuesCustomPaginate.SIZE);

    public int getPage() {
        return page <= 0 ? Integer.parseInt(DefaultValuesCustomPaginate.PAGE) : page;
    }
}
