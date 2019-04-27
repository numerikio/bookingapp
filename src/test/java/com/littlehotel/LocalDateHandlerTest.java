package com.littlehotel;


import static junit.framework.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class LocalDateHandlerTest {

    private LocalDateHandler localDateHandler = new LocalDateHandler();

    private LocalDate localDate = LocalDate.of(2019, 4, 25);
    private LocalDate localDate2 = LocalDate.of(2019, 4, 29);


    @Test
    public void getDatesTest() {

        List<LocalDate> localDates = new ArrayList();
        localDates.add(localDate);
        localDates.add(localDate2);
        String dateString = "04/25/2019 - 04/29/2019";

        assertEquals(localDates, localDateHandler.getFirstAndLastDay(dateString));
    }

    @Test
    public void getDaysTest() {
        long days = localDateHandler.getDaysOfPeriod(localDate,localDate2);
        assertEquals(4, days);
    }


}
