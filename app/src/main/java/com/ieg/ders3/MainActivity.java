package com.ieg.ders3;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "My MainActivity";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.txt_label);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(Helper.SMS_INTENT));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase(Helper.SMS_INTENT)) {
                final String message = intent.getStringExtra(Helper.SMS_VALIDATE);
                //Do whatever you want with the code here
                Log.d(TAG, "Get Validate:\n" + message);

                String[] s = message.split("\n");
                if (s.length >= 2) {
                    Helper.ShowNoti(MainActivity.this, s[0], s[1]);
                }

                textView.setText(message);
            }
        }

    };
}
