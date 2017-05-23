package bobberclobber.scramble_studios.se.samplingtimer.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import bobberclobber.scramble_studios.se.samplingtimer.R;
import bobberclobber.scramble_studios.se.samplingtimer.activity.GraphActivity;

/**
 * Created by Josef on 06/04/2015.
 * <p>
 *     This class handles what action to take when one of the randomly scheduled alarms goes off
 * </p>
 */
public class AlarmReceiver extends BroadcastReceiver {

    // This is called when one of the alarms are activated
    @Override
    public void onReceive(Context context, Intent intent) {
        // Play notification tone
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringtone = RingtoneManager.getRingtone(context, notification);
        ringtone.play();

        // Show notification
        showNotification(context, intent.getIntExtra("id", 0));
    }

    // Shows a notification which opens the GraphActivity when clicked
    private void showNotification(Context context, int id) {
        // Build the notification
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.st_ico)
                .setContentTitle("Sample Time")
                .setContentText("Time to make an update!");
        // Create an intent for the GraphActivity
        Intent graphIntent = new Intent(context, GraphActivity.class);

        // The stack builder object will contain an artificial back stack for the started activity
        // This ensures that navigating backwards from the activity leads to the home screen
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        // Adds the back stack for the intent (nut not the intent itself)
        stackBuilder.addParentStack(GraphActivity.class);
        // Adds the intent that starts the activity to the top of the stack
        stackBuilder.addNextIntent(graphIntent);
        PendingIntent graphPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(graphPendingIntent);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // The id allows us to modify the notification later on
        manager.notify(id, mBuilder.build());
    }
}
