package com.example.notifi;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity
{
    Button notify;
    EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        notify= findViewById(R.id.button);
        e= findViewById(R.id.editText);

        notify.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    String str = e.getText().toString();
                    intent.putExtra("message", str);
                    PendingIntent pending = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    Notification noti = new NotificationCompat.Builder(MainActivity.this, getString(R.string.CHANNEL_ID)).setContentTitle("New Message").setContentText(e.getText().toString()).setSmallIcon(R.drawable.ic_launcher_foreground).setSubText("Tap to view ·").setContentIntent(pending).setChannelId(getString(R.string.CHANNEL_ID)).setAutoCancel(true).build();
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    noti.flags |= Notification.FLAG_AUTO_CANCEL;
                    manager.notify(1, noti);
                }
                else{
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    String str = e.getText().toString();
                    intent.putExtra("message",str);
                    PendingIntent pending = PendingIntent.getActivity(MainActivity.this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
                    Notification noti = new Notification.Builder(MainActivity.this).setContentTitle("New Message").setContentText(e.getText().toString()).setSmallIcon(R.drawable.ic_launcher_background).setContentIntent(pending).build();
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    noti.flags |= Notification.FLAG_AUTO_CANCEL;
                    manager.notify(0, noti);
                }


            }
        });
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(getString(R.string.CHANNEL_ID),getString(R.string.CHANNEL_1), NotificationManager.IMPORTANCE_DEFAULT );
            notificationChannel.setDescription(getString(R.string.CHANNEL_DESCRIPTION));
            notificationChannel.setShowBadge(true);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}