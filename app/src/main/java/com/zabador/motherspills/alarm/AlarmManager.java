package com.zabador.motherspills.alarm;

import android.content.Context;

import com.zabador.motherspills.alarm.model.Alarm;

/**
 * The Alarm Manager provides the API for the Alarm operations.
 * Created on 11/21/15.
 *
 * @author Adrian Pena
 */
public interface AlarmManager {

    /**
     * Saves and sets an {@link Alarm} in the system.
     * @param context an Android context.
     * @param alarm the Alarm to save and set.
     */
    void saveAlarm(Context context, Alarm alarm);
}
