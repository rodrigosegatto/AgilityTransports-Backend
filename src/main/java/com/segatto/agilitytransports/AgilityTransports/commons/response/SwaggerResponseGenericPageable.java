package com.segatto.agilitytransports.AgilityTransports.commons.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwaggerResponseGenericPageable<T> {

    private List<T> content;

    @Schema(description = "Determine if it's the first page.")
    private boolean first;

    @Schema(description = "Determine if it's the last page.")
    private boolean last;

    @Schema(description = "Determine if the content is empty.")
    private boolean empty;

    @Schema(description = "Determine the number of items per page to be returned.")
    private int size;

    @Schema(description = "Determine current page.")
    private int page;

    @Schema(description = "Determine the number of items in current page.")
    private int numberOfElements;

    @Schema(description = "Determine the number total of pages to be returned.")
    private int totalPages;

    @Schema(description = "Determine the number total of items to be returned.")
    private long totalElements;

}
