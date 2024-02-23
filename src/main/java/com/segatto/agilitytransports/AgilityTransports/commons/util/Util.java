package com.segatto.agilitytransports.AgilityTransports.commons.util;

import org.springframework.stereotype.Component;

@Component
public class Util {

    public int getFirstPageStartZero(int page, int size) {
        return (page-1) * size;
    }

}
