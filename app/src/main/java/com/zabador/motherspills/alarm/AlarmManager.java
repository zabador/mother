package com.zabador.motherspills.alarm;

import android.content.Context;

import com.zabador.motherspills.alarm.model.Alarm;

import java.util.List;

/**
 * The Alarm Manager provides the API for the Alarm operations.
 * Created on 11/21/15.
 *
 * @author Adrian Pena
 */
public interface AlarmManager {

    /**
     * Saves and sets an {@link Alarm} in the system.
     * @param context the Android context.
     * @param alarm the alarm to save.
     * @return the id of the alarm.
     */
    long saveAlarm(Context context, Alarm alarm);

    /**
     * Gets the alarm for the given id.
     * @param id the alarm's id.
     * @return the alarm for the given id, <code>null</code> if the id is invalid.
     */
    Alarm getAlarm(long id);

    /**
     * Retrieves all the alarms.
     * @return the list of all the saved alarms.
     */
    List<Alarm> getAlarms();

    /**
     * Removes the alarm tied to the given id.
     * @param context the Android context.
     * @param id the id of the alarm to remove.
     */
    void removeAlarm(Context context, long id);

    /**
     * Removes the given alarm if the alarm id is valid in the repository.
     * @param context the Android context.
     * @param alarm the alarm to remove.
     */
    void removeAlarm(Context context, Alarm alarm);
}
