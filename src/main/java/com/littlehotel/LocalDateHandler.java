package com.littlehotel;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("localDateHandler")
public class LocalDateHandler {


    public LocalDate getLocalDateWithString(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("[MM/dd/yyyy][yyyy-MM-dd]");
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        return localDate;
    }

    private List<String> getFirstAndLastDaysWihtString(String datesSring) {
        return Stream.of(datesSring.split(" - "))
                .map(elem -> new String(elem))
                .collect(Collectors.toList());
    }

    public List<LocalDate> getFirstAndLastDay(String datesSring) {
        ArrayList<LocalDate> localDates = new ArrayList<>();
        List<String> dates = getFirstAndLastDaysWihtString(datesSring);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate theFirstDay = LocalDate.parse(dates.get(0), dateTimeFormatter);
        LocalDate theLastDay = LocalDate.parse(dates.get(1), dateTimeFormatter);
        localDates.add(theFirstDay);
        localDates.add(theLastDay);
        return localDates;
    }

    public Long getDaysOfPeriod(LocalDate in, LocalDate out) {
        return ChronoUnit.DAYS.between(in, out);
    }

    public List<LocalDate> getAllDatesOfPeriod(LocalDate theFirstDay, LocalDate thelastDay) {
        List<LocalDate> localDates = new ArrayList<>();

        long days = ChronoUnit.DAYS.between(theFirstDay, thelastDay);
        for (long i = 0; i <= days; i++) {
            localDates.add(theFirstDay.plusDays(i));
        }
        return localDates;
    }

    public Long getDaysOfPeriodByString (String dates){
        List <LocalDate>localDates = getFirstAndLastDay(dates);
        return getDaysOfPeriod(localDates.get(0),localDates.get(1));
    }
}
