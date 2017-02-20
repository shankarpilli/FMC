package com.versatilemobitech.fmc.fcm;

/**
 * Created by Manoj on 7/14/2016.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.SplashActivity;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Map<String, String> params = remoteMessage.getData();
        if (params != null && params.size() > 0) {
            Utility.showLog("dataChat", "<><>" + params);
            Utility.showLog(TAG, "From: " + remoteMessage.getFrom());
            Utility.showLog(TAG, "Notification Message Body: " + params.get("message"));
        }
    }

    @Override
    public void zzm(Intent intent) {
        Intent launchIntent = new Intent(this, SplashActivity.class);
        launchIntent.setAction(Intent.ACTION_MAIN);
        launchIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* R    equest code */, launchIntent,
                PendingIntent.FLAG_ONE_SHOT);
        Bitmap rawBitmap = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification)
                .setLargeIcon(rawBitmap)
                .setContentTitle(getApplicationContext().getResources().getString(R.string.app_name))
                .setContentText(intent.getStringExtra("gcm.notification.body"))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}