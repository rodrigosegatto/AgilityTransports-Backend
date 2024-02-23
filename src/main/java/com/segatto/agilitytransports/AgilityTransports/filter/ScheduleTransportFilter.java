package com.segatto.agilitytransports.AgilityTransports.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.segatto.agilitytransports.AgilityTransports.commons.DefaultValuesCustomPaginate;
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

    @JsonProperty(value = "page", defaultValue = DefaultValuesCustomPaginate.PAGE)
    private int page;

    @JsonProperty(value = "size", defaultValue = DefaultValuesCustomPaginate.SIZE)
    private int size;

    public int getPage() {
        return page <= 0 ? page+1 : page;
    }
}
