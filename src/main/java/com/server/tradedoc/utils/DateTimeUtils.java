package com.server.tradedoc.utils;

import com.server.tradedoc.utils.error.CustomException;
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

    public static Timestamp convertStringRequestToTimesTamp(String date , String dateFormat){
        try{
            if(StringUtils.isBlank(date)){
                return null;
            }else{
                DateFormat formatter = new SimpleDateFormat(dateFormat);
                Timestamp result = null;
                if(date.contains("T")){
                    java.sql.Date dateAfterFormat = (java.sql.Date) formatter.parse(date.trim().replaceAll("Z$" , "+0000") );
                    result = new Timestamp(dateAfterFormat.getTime());
                }else {
                    Date dateAfterFormat = formatter.parse(date);
                    result = new Timestamp(dateAfterFormat.getTime());
                }
                return result;
            }
        }catch (Exception e){
            throw new CustomException("convert date to timestamp fail" , CommonUtils.putError("date" , "ERR_007"));
        }
    }

}
