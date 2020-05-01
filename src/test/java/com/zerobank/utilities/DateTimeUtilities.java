package com.zerobank.utilities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DateTimeUtilities {

    /**
     * This method returns current date as a string.
     * Provide a format as a parameter
     *
     * MM - to specify month like: 01, 02, 03,
     * MMM - to specify month like: Jan, Feb, Mar
     *
     * dd - to specify day, like 01, 02, 03
     *
     * yyyy - to specify year like: 2010, 2020
     *
     * @param format for example: MMM dd, yyyy = Mar 29, 2020
     * @return current date as a string
     *
     * https://www.journaldev.com/17899/java-simpledateformat-java-date-format
     */
    public static String getCurrentDate(String format){
        return LocalDate.now().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * This method returns difference between end and start time
     * @param start time
     * @param end time
     * @param format like h:m a --> 5:15 AM, 8:07 PM
     * @return difference between end time and start time as a long
     */
    public static long getTimeDifference(String start, String end, String format){
        LocalTime startTime = LocalTime.parse(start, DateTimeFormatter.ofPattern(format));
        LocalTime endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern(format));
        return ChronoUnit.HOURS.between(startTime, endTime);
    }

    /**
     * Converts list of string to the list of local dates
     * @param dates list of strings
     * @return List of LocalDate objects
     */
    public static List<LocalDate> convertToLocalDateObjects(List<String> dates){
        List<LocalDate> localDateList=new ArrayList<>();
        for(String date:dates)
            localDateList.add(LocalDate.parse(date));
        return localDateList;
    }

    /**
     * Converts list of local dates objects to list of strings
     * @param dates List of local date objects
     * @return List of string
     */
    public static List<String> convertToStringObjects(List<LocalDate> dates){
        List<String> stringDates=new ArrayList<>();
        for(LocalDate date:dates)
            stringDates.add(date.toString());
        return stringDates;
    }
}
