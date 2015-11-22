package com.zabador.motherspills.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zabador.motherspills.alarm.model.Alarm;
import com.zabador.motherspills.sms.SmsManager;
import com.zabador.motherspills.sms.SmsManagerFactory;

import java.util.Calendar;
import java.util.Date;

/**
 * Application's alarm broadcast receiver.
 * Created on 11/21/15.
 *
 * @author Adrian Pena
 */
public class AlarmReceiver extends BroadcastReceiver {
    /**
     * The pending intents received in this broadcast receiver are expected to provide the alarm's id.
     */
    public static final String INTENT_EXTRA_ALARM_ID = "com.zabador.motherspills.alarm.AlarmReceiver.ALARM_ID";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null && intent.getExtras().containsKey(INTENT_EXTRA_ALARM_ID)) {
            AlarmManager alarmManager = AlarmManagerFactory.getAlarmManager();

            long alarmId = intent.getExtras().getLong(INTENT_EXTRA_ALARM_ID);
            Alarm alarm = alarmManager.getAlarm(alarmId);

            if(alarm != null) {
                //send the message
                SmsManager smsManager = SmsManagerFactory.getSmsManager();
                smsManager.sendSms(context, alarm.getRecipient(), alarm.getContent());

                //increment one day
                Date alarmDate = alarm.getDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(alarmDate);
                calendar.add(Calendar.DATE, 1);

                //save and schedule the alarm
                alarm.setDate(new Date(calendar.getTimeInMillis()));
                alarmManager.saveAlarm(context, alarm);
            }
        }
    }
}
