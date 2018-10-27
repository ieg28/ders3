package com.ieg.ders3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ImageButton.OnClickListener {

    private static final String TAG = "My MainActivity";

    private ImageButton buttonList;
    private ImageButton buttonSend;

    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonList = (ImageButton) findViewById(R.id.btn_list_msg);
        buttonSend = (ImageButton) findViewById(R.id.btn_send_msg);

        container = (FrameLayout) findViewById(R.id.container);

        buttonList.setOnClickListener(this);
        buttonSend.setOnClickListener(this);
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
            }
        }

    };

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (view.getId()) {
            case R.id.btn_list_msg: {
                transaction.replace(R.id.container, new ListMsgFragment()).commit();
                break;
            }
            case R.id.btn_send_msg: {
                transaction.replace(R.id.container, new SendMsgFragment()).commit();
                break;
            }
        }
    }
}
