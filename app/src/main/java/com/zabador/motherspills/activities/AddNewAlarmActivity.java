package com.zabador.motherspills.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TimePicker;

import com.zabador.motherspills.R;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created on 11/21/15.
 *
 * @author Skye Schneider
 */
public class AddNewAlarmActivity extends Activity {
    private static final String TAG = AddNewAlarmActivity.class.getSimpleName();

    private Date mDate;

    @Bind(R.id.recipient_number)
    EditText mRecipientNumber;
    @Bind(R.id.text_messge)
    EditText mTextMessage;
    @Bind(R.id.new_alarm_name)
    EditText mAlarmName;
    @Bind(R.id.time_picker)
    TimePicker mTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_alarm);
        ButterKnife.bind(this);
        mTimePicker.setIs24HourView(true);

        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Log.d(TAG, " hour = " + hourOfDay + " minute = " + minute);
                mDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, hourOfDay, minute);
                mDate = calendar.getTime();
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                mDate = cal.getTime();


                boolean beforeNow = mDate.before(Calendar.getInstance().getTime());
                if (beforeNow) {
                    Log.d(TAG, "It was before");
                    cal.add(Calendar.DATE, 1);
                    mDate = cal.getTime();
                }

            }
        });
    }
}
