package com.segatto.agilitytransports.AgilityTransports.commons;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.PageImpl;

import java.io.IOException;

@JsonComponent
public class PageImplJacksonSerializer extends JsonSerializer<PageImpl> {

    @Override
    public void serialize(PageImpl page, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("content", page.getContent());
        jsonGenerator.writeBooleanField("first", page.isFirst());
        jsonGenerator.writeBooleanField("last", page.isLast());
        jsonGenerator.writeBooleanField("empty", page.isEmpty());
        jsonGenerator.writeNumberField("size", page.getSize());
        jsonGenerator.writeNumberField("page", page.getNumber()+1);
        jsonGenerator.writeNumberField("numberOfElements", page.getNumberOfElements());
        jsonGenerator.writeNumberField("totalPages", page.getTotalPages());
        jsonGenerator.writeNumberField("totalElements", page.getTotalElements());
        jsonGenerator.writeEndObject();
    }
}
