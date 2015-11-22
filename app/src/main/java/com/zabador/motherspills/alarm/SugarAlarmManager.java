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
    public void removeAlarm(long id) {
        Alarm alarm = getAlarm(id);
        if(alarm != null) {
            alarm.delete();
        }
    }

    @Override
    public void removeAlarm(Alarm alarm) {
        removeAlarm(alarm.getId());
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
