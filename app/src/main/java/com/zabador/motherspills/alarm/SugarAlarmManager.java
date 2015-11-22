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
        alarm.save();
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
}
