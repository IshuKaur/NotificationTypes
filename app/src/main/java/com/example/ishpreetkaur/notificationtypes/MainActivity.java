package com.example.ishpreetkaur.notificationtypes;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button textNoti,imageNoti,loadNoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textNoti=findViewById(R.id.textNoti);
        imageNoti=findViewById(R.id.imageNoti);
        loadNoti=findViewById(R.id.loadNoti);

        textNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTextNotification();
            }
        });

        imageNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageNotification();
            }
        });

        loadNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadNotification();
            }
        });
    }

    public void showTextNotification()
    {
        Intent intent=new Intent(MainActivity.this,SecondActivity.class);
        TaskStackBuilder taskStackBuilder=TaskStackBuilder.create(MainActivity.this);
        taskStackBuilder.addParentStack(SecondActivity.class);
        taskStackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent=taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(MainActivity.this)
                        .setAutoCancel(true)
                        .setSmallIcon(android.R.drawable.gallery_thumb)
                        // .setColor(Color.RED)
                        .setContentTitle("Wao! Try out this magic Prouduct");

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        NotificationCompat.BigPictureStyle s = new NotificationCompat.BigPictureStyle().bigPicture(largeIcon);
        s.setSummaryText("This is ash proudct buy it");
        mBuilder.setStyle(s);

        mBuilder.setContentIntent(pendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }

    public void showImageNotification()
    {
        String replyLabel = "Reply Here";
        Intent resultIntent = new Intent(MainActivity.this, SecondActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);

        stackBuilder.addParentStack(SecondActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.mipmap.ic_launcher, replyLabel, resultPendingIntent)
                        .setAllowGeneratedReplies(true)
                        .build();


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(MainActivity.this)
                        .addAction(action)
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("This is the example of Notification tutorial")
                        .setContentText("Image Notification");

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(100, mBuilder.build());
    }

    public void showLoadNotification()
    {
        Intent resultIntent = new Intent(MainActivity.this, SecondActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);

        stackBuilder.addParentStack(SecondActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        // Create the reply action and add the remote input.

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.mipmap.ic_launcher)  // its taking 128 px not more..than that.
                        .setAutoCancel(true)
                        .setContentTitle("Downloading Resource");

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder.setProgress(0, 100, true);
        mNotificationManager.notify(1, mBuilder.build());
    }
}
