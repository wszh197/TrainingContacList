package com.wszh.trainingcontactlist;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private ListView mContactsView;
    ListView mContactsView;
    ArrayAdapter<String> mAdapter;
    List<String> mContactsList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContactsView = (ListView) findViewById(R.id.activity_main_contacts_view);
        //mContactsList = new ArrayList<>();

        mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mContactsList);
        mContactsView.setAdapter(mAdapter);
        readContactInfo();
    }

    private void readContactInfo() {

        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);
            while (cursor.moveToNext()) {
                String displayName = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
        }catch(Exception e){
            e.printStackTrace();
            Log.i("Phone", "Phone 异常");
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }


    }
}
