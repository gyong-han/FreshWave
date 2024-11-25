package com.semi.gam.util.date;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ChangeDate {
    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yy-MM-dd");

    public static String changeDate(String x){
        LocalDate parse = LocalDate.parse(x, inputFormatter);
        String date = parse.format(outputFormatter);
        return date;
    }
}
