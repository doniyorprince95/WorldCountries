package com.vyke.Countries.time;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by Raider on 18-08-2017.
 */

public class LocalTime {
    private TimeZone UTC = TimeZone.getTimeZone("UTC");
    //String []Szone=TimeZone.getAvail;
    private long milliseconds = System.currentTimeMillis();
    private long offset = TimeZone.getDefault().getOffset(milliseconds);
    private long UTCTIME = milliseconds - offset;


    public long getLocalTimeOffSet(String UTC) {
        if (UTC.contains("+")) {
            String ms = UTC.replace("+", "").replace("UTC", "").replace("[", "").replace("]", "");
            String[] mns = ms.split(":");
            try {
                int hh = Integer.parseInt(mns[0]);
                int mm = Integer.parseInt(mns[1]);
                return UTCTIME + ((hh * 60L * 60L) * 1000L + (mm * 60L) * 1000L);
            } catch (NumberFormatException n) {
                n.printStackTrace();
                n.getMessage();
                Log.e("Error in time ", UTC);
            }

        } else if (UTC.contains("-")) {
            String ms = UTC.replace("-", "").replace("UTC", "").replace("[", "").replace("]", "");
            String[] mns = ms.split(":");
            try {
                int hh = Integer.parseInt(mns[0]);
                int mm = Integer.parseInt(mns[1]);
                return UTCTIME - ((hh * 60L * 60L) * 1000L + (mm * 60L) * 1000L);
            } catch (NumberFormatException n) {
                n.printStackTrace();
                n.getMessage();
                Log.e("Error in time ", UTC);
            }

        } else {
            return UTCTIME;
        }
        return 0;
    }


    public String getTime(long timemilli) {

        SimpleDateFormat newTimeFormator = new SimpleDateFormat("hh:mm a");
        return newTimeFormator.format(timemilli);


    }

    public String getDay(long daymilli) {

        SimpleDateFormat newTimeFormator = new SimpleDateFormat("EEEE");
        return newTimeFormator.format(daymilli);

    }

    public String getDate(long datemili) {

        SimpleDateFormat newTimeFormator = new SimpleDateFormat("dd MMM, yyyy");
        return newTimeFormator.format(datemili);

    }
}


//Burkina faso
