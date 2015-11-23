package com.zabador.motherspills.alarm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.zabador.motherspills.alarm.model.Alarm;

import java.util.List;

/**
 * An Alarm Manager implementation using SugarORM for the data base persistence.
 * Created on 11/21/15.
 *
 * @author Adrian Pena
 */
class SugarAlarmManager implements AlarmManager{

    private static SugarAlarmManager sInstance;

    @Override
    public long saveAlarm(Context context, Alarm alarm) {
        alarm.save();
        setAlarm(context, alarm);
        return alarm.getId();
    }

    @Override
    public Alarm getAlarm(long id) {
        return Alarm.findById(Alarm.class, id);
    }

    @Override
    public List<Alarm> getAlarms() {
        return Alarm.listAll(Alarm.class);
    }

    @Override
    public void removeAlarm(Context context, long id) {
        Alarm alarm = getAlarm(id);
        removeAlarm(context, alarm);
    }

    @Override
    public void removeAlarm(Context context, Alarm alarm) {
        if(alarm != null && alarm.getId() != null) {
            //remove from OS Alarm Manager
            Intent intent = new Intent(context, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarm.getId().intValue(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

            android.app.AlarmManager alarmManager = (android.app.AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);

            //remove from data base.
            alarm.delete();
        }
    }

    public static SugarAlarmManager getInstance() {
        if(sInstance == null) {
            sInstance = new SugarAlarmManager();
        }
        return sInstance;
    }

    private void setAlarm(Context context, Alarm alarm) {
        //create the pending alarm intent
        final Intent messageAlarmReceiver = new Intent(context, AlarmReceiver.class);
        messageAlarmReceiver.putExtra(AlarmReceiver.INTENT_EXTRA_ALARM_ID, alarm.getId());
        final PendingIntent alarmIntent = PendingIntent.getBroadcast(context, alarm.getId().intValue(), messageAlarmReceiver, PendingIntent.FLAG_UPDATE_CURRENT);

        //schedule the alarm
        android.app.AlarmManager alarmManager = (android.app.AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(android.app.AlarmManager.RTC_WAKEUP, alarm.getDate().getTime(), alarmIntent);
    }
}
