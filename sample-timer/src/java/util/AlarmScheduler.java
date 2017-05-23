package bobberclobber.scramble_studios.se.samplingtimer.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Josef on 08/04/2015.
 * <p>
 *     A support class used when scheduling the randomly generated alarms
 * </p>
 */
public class AlarmScheduler {
    // Schedules all alarms
    // Returns a vector containing the ID of all scheduled alarms
    public static Vector<Integer> scheduleAllAlarms(int alarmNumber, int diff, int startHour, int startMin,
                                        Context context,  AlarmManager alarmManager, Intent alarmIntent) {
        Vector<Integer> ids = new Vector<Integer>();

        // Generate [alarmNumber] random numbers between the start and end times
        Random randomGenerator = new Random();
        ArrayList<Integer> minuteOffsets = new ArrayList<Integer>();
        minuteOffsets.add(0, -1); // -1 is used when generating random alarm times

        for (int i = 0; i < alarmNumber; i++) {
            // Generate random offsets such that all are different from each other
            int minuteOffset = -1;
            while (minuteOffsets.contains(minuteOffset))
                minuteOffset = randomGenerator.nextInt(diff + 1);
            minuteOffsets.add(i, minuteOffset);

            int hour = startHour + (minuteOffset / 60);
            int minute = startMin + (minuteOffset % 60);
            ids.add(scheduleAlarm(hour, minute, minuteOffset, context, alarmManager,  alarmIntent));
        }

        return ids;
    }

    // Schedules an alarm at the given time
    // Returns the ID of the scheduled alarm
    public static int scheduleAlarm(int hour, int minute, int id, Context context, AlarmManager alarmManager, Intent alarmIntent) {
        System.out.println("Time: " + hour + ":" + minute + " ID: " + id);
        // Set wake-up time using a calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        // Sets the alarm
        alarmIntent.putExtra("id", id);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, alarmIntent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        return id;
    }
}
