package bobberclobber.scramble_studios.se.samplingtimer.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Vector;

import bobberclobber.scramble_studios.se.samplingtimer.R;
import bobberclobber.scramble_studios.se.samplingtimer.receiver.AlarmReceiver;
import bobberclobber.scramble_studios.se.samplingtimer.receiver.ReSchedulerReceiver;
import bobberclobber.scramble_studios.se.samplingtimer.util.AlarmScheduler;


/**
 * <p>
 *     The main activity class
 * </p>
 */
public class MainActivity extends Activity {
    private final int RE_SCHEDULER_ID = -1;

    private AlarmManager alarmManager;
    private Intent alarmIntent;
    private Intent reschedulingIntent;

    private Vector<Integer> alarmIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Initialize the alarm manager and the alarm intent
        Context context = getApplicationContext();
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmIntent = new Intent(context, AlarmReceiver.class);
        reschedulingIntent = new Intent(context, ReSchedulerReceiver.class);

        // Modify the start and end time pickers
        TimePicker startTimePicker = (TimePicker) findViewById(R.id.start_time_picker);
        TimePicker endTimePicker = (TimePicker) findViewById(R.id.end_time_picker);
        startTimePicker.setIs24HourView(true);
        endTimePicker.setIs24HourView(true);

        // Modify the alarm amount picker
        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.alarm_number_picker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(100);
        numberPicker.setWrapSelectorWheel(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.standard_menu, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        // TODO: Load ids stored in the preference file to the alarm IDs vector
        alarmIDs = new Vector<Integer>();
    }

    @Override
    protected void onStop() {
        // TODO: Store scheduled alarm ids in preferences file

        super.onStop();
    }

    public void onStartButtonClick(View view) {
        TimePicker startTimePicker = (TimePicker) findViewById(R.id.start_time_picker);
        TimePicker endTimePicker = (TimePicker) findViewById(R.id.end_time_picker);
        NumberPicker alarmNumberPicker = (NumberPicker) findViewById(R.id.alarm_number_picker);

        // Retrieve commonly used values
        int startHour = startTimePicker.getCurrentHour();
        int startMinute = startTimePicker.getCurrentMinute();
        int endHour = endTimePicker.getCurrentHour();
        int endMinute = endTimePicker.getCurrentMinute();
        int alarmNumber = alarmNumberPicker.getValue();

        // Calculate the difference (in minutes) between the start and end times
        int startMinCount = startHour * 60 + startMinute;
        int endMinCount = endHour * 60 + endMinute;
        int diff = endMinCount - startMinCount;

        Context context = getApplicationContext();
        // Check that start time isn't after end time
        if (diff <= 0) {
            CharSequence msg = "Start time must be prior to end time!";
            Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.show();
        } else if(alarmNumber >= diff) { // If the user is trying to schedule too many alarms in the given time
            CharSequence msg = "Too many alarms for given time period!";
            Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            // Cancel the rescheduling alarm if it exists
            PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), RE_SCHEDULER_ID, reschedulingIntent, 0);
            alarmManager.cancel(alarmPendingIntent);
            // Cancel any existing regular alarms
            cancelAllAlarms();
            // Schedule the alarms and retrieve a vector of the IDs
            alarmIDs = AlarmScheduler.scheduleAllAlarms(alarmNumber, diff, startHour, startMinute, getApplicationContext(), alarmManager, alarmIntent);
            // Setup the re-scheduling alarm
            scheduleReschedulingAlarm(alarmNumber, startMinCount, endMinCount);
        }
    }

    public void onCancelAlarmsButtonClick(View view) {
        // Cancel the rescheduling alarm if it exists
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), RE_SCHEDULER_ID, reschedulingIntent, 0);
        alarmManager.cancel(alarmPendingIntent);

        // Cancel all the regular alarms
        cancelAllAlarms();

        // Show confirmation message
        CharSequence msg = "Alarms cancelled!";
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    // Cancel all active alarms, excluding the re-scheduling alarm
    private void cancelAllAlarms() {
        for (Integer i : alarmIDs) {
            PendingIntent cancelIntent = PendingIntent.getBroadcast(getApplicationContext(), i, alarmIntent, 0);
            alarmManager.cancel(cancelIntent);
        }
    }

    // Schedules an alarm which reschedules the other alarms once every day
    private void scheduleReschedulingAlarm(int alarmNumber, int startMin, int endMin) {
        // Cancel the rescheduling alarm if it exists
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), RE_SCHEDULER_ID, reschedulingIntent, 0);
        alarmManager.cancel(alarmPendingIntent);

        // Schedule an alarm which reschedules all alarms every day
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);

        // Schedule the alarm using id -1
        reschedulingIntent.putExtra("alarmNum", alarmNumber);
        reschedulingIntent.putExtra("startMin", startMin);
        reschedulingIntent.putExtra("endMin", endMin);
        alarmPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), RE_SCHEDULER_ID, reschedulingIntent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmPendingIntent);
    }
}
