package com.zabador.motherspills.sms;

import android.content.Context;

/**
 * Application's SMS Service
 * Created on 11/21/15.
 *
 * @author Adrian Pena
 */
public interface SmsManager {
    /**
     * Given the phone number it sends an SMS with the message.
     * @param context an Android context.
     * @param phoneNumber the phone number to send the SMS to.
     * @param message the message to sent
     */
    void sendSms(Context context, String phoneNumber, String message);
}
