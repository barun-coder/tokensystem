package com.displayfort.dftoken.utils;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Husain on 16/05/2019 17:48.
 * SportsInCode
 */
public class DateTimeUtils {
    public static final String FORMAT_MMM_YYYY = "MMM yyyy";
    public static final String FORMAT_YYYY = "yyyy";
    public static final String SERVER_FORMAT = "yyyy-MM-dd";
    public static final String FORMAT_MMM_DD_YYYY = "MMM dd,yyyy";

    public static String getStringintoAnyFormat(String Date, String inFormat,
                                                String Format) {
        SimpleDateFormat inSdf = new SimpleDateFormat(inFormat);
        java.util.Date inDate;
        try {
            inDate = inSdf.parse(Date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(inDate);
            String dateString = DateFormat.format(Format, inDate).toString();
            return dateString;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Format;

    }

    public static Calendar getCalendarFromDate(String Date, String inFormat) {
        SimpleDateFormat inSdf = new SimpleDateFormat(inFormat);
        java.util.Date inDate;
        Calendar calendar = Calendar.getInstance();
        try {
            inDate = inSdf.parse(Date);
            calendar.setTime(inDate);
            return calendar;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return calendar;

    }
}

