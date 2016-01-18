package com.login.abhishekchandale.virtualhackapp1.ui;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.test.ApplicationTestCase;
import android.widget.ListView;
import android.widget.Toast;

import com.login.abhishekchandale.virtualhackapp1.R;
import com.login.abhishekchandale.virtualhackapp1.adapter.ComplaintAdapter;
import com.login.abhishekchandale.virtualhackapp1.database.DbAccess;

/**
 * Created by abhi on 16-01-2016.
 */

public class PreviousComplaintsActivity extends AppCompatActivity {

    private ListView listView;
    private Cursor cursor;
    private DbAccess dbAccess;
    private String compMessage[],comDate[];
    private byte[] araay;
    private ComplaintAdapter complaintAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.previous_comp_activity);
        dbAccess=new DbAccess(this);
        listView=(ListView)findViewById(R.id.listView);
        complaintData();
    }

    public void complaintData(){

        try {
            cursor = dbAccess.getComplaint();
            compMessage = new String[cursor.getCount()];
            comDate = new String[cursor.getCount()];
            //araay=new Byte[cursor.getCount()];
            if (cursor.moveToNext()) {

                for (int i = 1; cursor.moveToNext(); i++) {
                    compMessage[i] = cursor.getString(cursor.getColumnIndex("complaintMessage"));
                    comDate[i] = cursor.getString(cursor.getColumnIndex("datetime"));
                    // araay=cursor.getBlob(cursor.getColumnIndex("image"));
                }
            } else {

                Toast.makeText(getApplicationContext(), "databse not created...", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e) {
               e.printStackTrace();
        }
        complaintAdapter=new ComplaintAdapter(this,compMessage,comDate);
        listView.setAdapter(complaintAdapter);
    }

}

