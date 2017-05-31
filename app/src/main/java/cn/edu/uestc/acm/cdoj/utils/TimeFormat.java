package cn.edu.uestc.acm.cdoj.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Grea on 2016/9/30.
 */

public class TimeFormat {

    public static String getFormatTime(long timeLength) {
        timeLength /= 1000;
        String formatTime;
        long days = timeLength / (60 * 60 * 24);
        long hours = (timeLength % (60 * 60 * 24)) / (60 * 60);
        long minutes = (timeLength % (60 * 60)) / 60;
        long seconds = timeLength % 60;
        if (days > 0) {
            formatTime = days + "d:" + hours + "h:" + minutes + "m:" + seconds + "s";
        } else if (hours > 0) {
            formatTime = hours + "h:" + minutes + "m:" + seconds+ "s";
        } else if (minutes > 0) {
            formatTime = minutes + "m:" + seconds+ "s";
        } else {
            formatTime = seconds + "s";
        }
        return formatTime;
    }

    public static String getFormatDate(long dateLength) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateLength);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return simpleDateFormat.format(calendar.getTime());
    }

}
