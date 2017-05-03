package com.example.vimadhavan.assignment123;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String CUSTOM_ACTION="com.example.vimadhavan.assignment123.CUSTOM_ACTION";


    private Button broadCastBtn,cancelBtn;

    private static final int NOTIFY_ME_ID = 101;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder mBuilder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        broadCastBtn=(Button) findViewById(R.id.broadcastBtn);
        cancelBtn=(Button) findViewById(R.id.cancelBtn);

        broadCastBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);




    }

    private void displayNotification() {
        broadCastBtn.setVisibility(View.GONE);
        Bitmap icon1 = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);
        Intent resultIntent = new Intent(MainActivity.this, MainActivity.class);

        mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("Event Details Short")
                .setContentText("Short Description")
                .setContentIntent(PendingIntent.getActivity(this, 0, resultIntent,PendingIntent.FLAG_ONE_SHOT))
                .setSmallIcon(R.drawable.ic_comment_black_24dp).setLargeIcon(icon1);

        String[] events = new String[6];

        events[0] = "Hello....!";
        events[1] = "How are you";
        events[2] = "Hey!!!";
        events[3] = "I am fine....";
        events[4] = "What about you? all is well?";
        events[5] = "Yes! every thing is all right... ";

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        // Sets a title for the Inbox style big view
        inboxStyle.setBigContentTitle("Event Details");

        // Moves events into the big view
        for (int i = 0; i < events.length; i++) {

            inboxStyle.addLine(events[i]);
        }
        // Moves the big view style object into the notification object.
        mBuilder.setStyle(inboxStyle);

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // mId allows you to update the notification later on.
        mNotificationManager.notify(NOTIFY_ME_ID, mBuilder.build());

        cancelBtn.setVisibility(View.VISIBLE);

    }

    private void cancelNotification() {
        cancelBtn.setVisibility(View.GONE);
        mNotificationManager.cancel(NOTIFY_ME_ID);
        broadCastBtn.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.broadcastBtn:
                displayNotification();
                break;

            case R.id.cancelBtn:
                cancelNotification();
                break;
        }

    }
}
