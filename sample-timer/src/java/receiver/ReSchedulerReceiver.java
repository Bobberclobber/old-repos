package bobberclobber.scramble_studios.se.samplingtimer.receiver;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import bobberclobber.scramble_studios.se.samplingtimer.util.AlarmScheduler;

/**
 * Created by Josef on 08/04/2015.
 * <p>
 *     This receiver is called once every day and reschedules the random alarms at that point.
 *     It includes checks to see if fields are initialized or not
 * </p>
 */
public class ReSchedulerReceiver extends BroadcastReceiver {
    AlarmManager alarmManager;
    Intent alarmIntent;

    // This should be called every day at 00:00.
    // At that point, [alarmNum] alarms should be scheduled between the previously entered times
    @Override
    public void onReceive(Context context, Intent intent) {
        // Initializes fields used when scheduling alarms
        int alarmNum = intent.getIntExtra("alarmNum", 0);
        int startMin = intent.getIntExtra("startMin", 0);
        int endMin = intent.getIntExtra("endMin", 0);
        int diff = endMin - startMin;

        // Makes sure the alarm manager and the alarm intent is initialized
        if (alarmManager == null)
            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmIntent == null)
            alarmIntent = new Intent(context, AlarmReceiver.class);

        // Schedules all alarms
        AlarmScheduler.scheduleAllAlarms(alarmNum, diff, startMin / 60, startMin % 60,
                context, alarmManager, alarmIntent);
    }
}
