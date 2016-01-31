package xiaofei.com.androidstudioprojects.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import xiaofei.com.androidstudioprojects.MainActivity;

/**
 * Created by xiaofei on 2015/12/26.
 */
public class PollService extends IntentService {

    public PollService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }
}
