package com.zabador.motherspills.alarm;

import android.content.Context;

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
        return 0;
    }

    @Override
    public Alarm getAlarm(long id) {
        return null;
    }

    @Override
    public List<Alarm> getAlarms() {
        return null;
    }

    @Override
    public void removeAlarm(long id) {

    }

    @Override
    public void removeAlarm(Alarm alarm) {

    }

    public static SugarAlarmManager getInstance() {
        if(sInstance == null) {
            sInstance = new SugarAlarmManager();
        }
        return sInstance;
    }
}
