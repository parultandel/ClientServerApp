package com.example.tandels.issapplication.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;


public class IssAppUtil {

    /**
     * convert RISE TIME from milliseconds into "MM-dd-yyyy'T' HH:mm:ss"
     *
     * @param milliSeconds
     * @return
     */
    public static final String sdf_MMddyyyyTHHmmss = "MM-dd-yyyy'T' HH:mm:ss";
    public static String formateDateTime(long milliSeconds) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sdf_MMddyyyyTHHmmss);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * convert DURATION from seconds into "MM:SS"
     *
     * @param millis
     * @return
     */
    public static String formateDuration(int millis) {
        return secondsToString(millis);

    }

    private static String secondsToString(int pTime) {
        return String.format("%02d:%02d", pTime / 60, pTime % 60);
    }


}
