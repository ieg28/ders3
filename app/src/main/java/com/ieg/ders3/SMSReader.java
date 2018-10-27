package com.ieg.ders3;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReader extends BroadcastReceiver {
    private static final String TAG = "Ders3 SMS Reader";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle pudsBundle = intent.getExtras();
        Object[] pdus = (Object[]) pudsBundle.get("pdus");

        SmsMessage messages = SmsMessage.createFromPdu((byte[]) pdus[0]);

        String msg = "";
        if (messages != null) {
            msg = String.format("from: %s\nbody: %s", messages.getUserData(), messages.getMessageBody());

            Intent myIntent = new Intent(Helper.SMS_INTENT);
            myIntent.putExtra(Helper.SMS_VALIDATE, msg);
            LocalBroadcastManager.getInstance(context).sendBroadcast(myIntent);
        }

        Toast.makeText(context, "Yeni SMS: " + messages.getMessageBody(),
                Toast.LENGTH_LONG).show();

        Log.d(TAG, "yeni sms geldi\n" + msg);
    }
}
