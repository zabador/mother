package com.zabador.motherspills.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TimePicker;

import com.zabador.motherspills.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created on 11/21/15.
 *
 * @author Skye Schneider
 */
public class AddNewAlarmActivity extends Activity {

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
    }
}
