package com.technology.util.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间工具类
 */
public class Times {

    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(DEFAULT_FORMAT);

    public static String format(LocalDateTime localDateTime) {
        return FORMAT.format(localDateTime);
    }

    public static String format(LocalDateTime localDateTime, String formatMode) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(formatMode);
        return timeFormatter.format(localDateTime);
    }

    public static LocalDateTime parse(String date) {
        return  LocalDateTime.parse(date,FORMAT);
    }

//    public static LocalDateTime parse(String date, String formatMode) {
//        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(formatMode);
//        return  timeFormatter.parse(date, LocalDateTime::from);
//    }


    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        System.out.println(Times.parse("2017-10-11 11:21:31"));
//        String str = "1986-04-08 12:30";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
//        System.out.println(dateTime);
    }

}
