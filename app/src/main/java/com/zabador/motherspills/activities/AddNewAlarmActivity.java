package com.zabador.motherspills.activities;

import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.zabador.motherspills.R;
import com.zabador.motherspills.alarm.AlarmManagerFactory;
import com.zabador.motherspills.alarm.model.Alarm;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created on 11/21/15.
 *
 * @author Skye Schneider
 */
public class AddNewAlarmActivity extends Activity {
    private static final String TAG = AddNewAlarmActivity.class.getSimpleName();

    private Date mDate;
    private String mNumber;

    @Bind(R.id.recipient_number)
    SearchView mRecipientNumber;
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

        setupSearchView();
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

    @OnClick(R.id.save_button)
    public void onSaveAlarm() {
        if (!mAlarmName.getText().toString().isEmpty() && !mTextMessage.getText().toString().isEmpty() && !mNumber.isEmpty()) {
            Alarm alarm = new Alarm(mAlarmName.getText().toString(), mTextMessage.getText().toString(), mNumber, mDate);
            AlarmManagerFactory.getAlarmManager().saveAlarm(this, alarm);
            finish();
        } else {
            Toast.makeText(AddNewAlarmActivity.this, "Something is empty", Toast.LENGTH_SHORT).show();
        }

    }

    private void setupSearchView() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
        mRecipientNumber.setSearchableInfo(searchableInfo);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (ContactsContract.Intents.SEARCH_SUGGESTION_CLICKED.equals(intent.getAction())) {
            getPhoneNumberForContact(intent);
            Cursor phoneCursor = getContentResolver().query(intent.getData(), null, null, null, null);
            phoneCursor.moveToFirst();
            int idDisplayName = phoneCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String name = phoneCursor.getString(idDisplayName);
            phoneCursor.close();
            mRecipientNumber.setQuery(name, false);
            mRecipientNumber.clearFocus();
        } else if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
        }
    }

    private void getPhoneNumberForContact(Intent intent) {
        Cursor phoneCursor = getContentResolver().query(intent.getData(), null,null,null,null);
        String phoneNum= "0";
        phoneCursor.moveToFirst();
        String id = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.Contacts._ID));
        Cursor pCur= getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{id},null);
        while(pCur.moveToNext()){
            phoneNum = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }
        Log.d(TAG, phoneNum);
        mNumber = phoneNum;
    }

}
