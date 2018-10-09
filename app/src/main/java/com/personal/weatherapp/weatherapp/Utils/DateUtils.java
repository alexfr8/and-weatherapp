package com.personal.weatherapp.weatherapp.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public final class DateUtils {

    final static String openweahterformat = "yyyy-MM-dd HH:mm:ss";
    final static String objetiveFormat ="yyyy/MM/dd";

    public static Calendar stringToCalendar(String date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.openweahterformat, Locale.ENGLISH);
        cal.setTime(sdf.parse(date));// all done
        return cal;
    }

    public static String stringBadFormedDateToGoodFormat(String date) {
        String result="";
        try {
            Calendar cal = DateUtils.stringToCalendar(date);

            SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.objetiveFormat);

            if (cal != null) {
                result = sdf.format(cal.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
