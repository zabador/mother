package com.zabador.motherspills.activities;

import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.EditText;
import android.widget.SearchView;
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
    }

    private void setupSearchView() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
        mRecipientNumber.setSearchableInfo(searchableInfo);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (ContactsContract.Intents.SEARCH_SUGGESTION_CLICKED.equals(intent.getAction())) {
            String phoneNumber = getPhoneNumberForContact();
        } else if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
        }
    }

    private String getPhoneNumberForContact() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor contacts = getContentResolver().query(uri, projection, null, null, null);
        int indexNumber = contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        contacts.moveToFirst();
        String number = contacts.getString(indexNumber);

        // Use this if you want the contact name as well
        //String name   = contacts.getString(indexName);
        //int indexName = contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

        return number;
    }

}
