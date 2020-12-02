package com.server.tradedoc.utils;

import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateTimeUtils
 *
 * @author DatDV
 */
public class DateTimeUtils {

    public static String getDateTimeNow(String format){
        if(format == null){
            format = "dd/MM/yyyy HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date dateNow = new Date(System.currentTimeMillis());
        return formatter.format(dateNow);
    }

    public static String formatDateTimeQuery(String date) throws ParseException {
        String dateNew = date;
        if (date.trim().contains("-")){
            dateNew = date.trim().replace("-" , "/");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dateNew);
        return formatter.format(date1);
    }

}
