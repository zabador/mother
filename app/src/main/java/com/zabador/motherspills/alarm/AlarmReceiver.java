package com.zabador.motherspills.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zabador.motherspills.alarm.model.Alarm;

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
    public static final String INTENT_EXTRA_ALARM_ID = "alarm_id";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null && intent.getExtras().containsKey(INTENT_EXTRA_ALARM_ID)) {
            AlarmManager alarmManager = AlarmManagerFactory.getAlarmManager();

            long alarmId = intent.getExtras().getLong(INTENT_EXTRA_ALARM_ID);
            Alarm alarm = alarmManager.getAlarm(alarmId);

            if(alarm != null) {
                //TODO: do something with the alarm
            }
        }
    }
}
