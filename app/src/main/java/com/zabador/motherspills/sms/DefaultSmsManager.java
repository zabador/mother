package com.zabador.motherspills.sms;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Default SMS Manager implementation using the Android's telephony services.
 * Created on 11/21/15.
 *
 * @author Adrian Pena
 */
class DefaultSmsManager implements SmsManager {
    private static DefaultSmsManager sInstance;

    private DefaultSmsManager() {
        //NOP
    }

    @Override
    public void sendSms(Context context, String phoneNumber, String message) {
        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();

        //create pending intent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, new Intent(context, SmsMessageReceiver.class), 0);
        smsManager.sendTextMessage(phoneNumber, null, message, pendingIntent, null);
    }

    public static DefaultSmsManager getInstance() {
        if(sInstance == null) {
            sInstance = new DefaultSmsManager();
        }
        return sInstance;
    }
}
