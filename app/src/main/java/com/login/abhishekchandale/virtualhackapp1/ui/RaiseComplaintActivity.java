package com.login.abhishekchandale.virtualhackapp1.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.login.abhishekchandale.virtualhackapp1.R;
import com.login.abhishekchandale.virtualhackapp1.database.DbAccess;

import java.nio.ByteBuffer;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

public class RaiseComplaintActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int CAMERA_REQUEST = 1888;
    private ImageButton photoButton,btnpost;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Cursor cursor;
    private DbAccess dbAccess;
    private String name,city,address,email,date,image,compMessage;
    private Double lat,lon;
    private byte[] arrayimage;
    private EditText compText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raise_complaint_activity);

        dbAccess=new DbAccess(this);

        compText=(EditText)findViewById(R.id.txt_comp_message);
        photoButton = (ImageButton) this.findViewById(R.id.take_pic);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        btnpost=(ImageButton)findViewById(R.id.btn_post);
        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            photoButton.setImageDrawable(new BitmapDrawable(photo));

            if (photo != null) {
                //calculate how many bytes our image consists of.
                int bytes = photo.getByteCount();
                //or we can calculate bytes this way. Use a different value than 4 if you don't use 32bit images.
                // int bytes = b.getWidth()*b.getHeight()*4;

                ByteBuffer buffer = ByteBuffer.allocate(bytes); //Create a new buffer
                photo.copyPixelsToBuffer(buffer); //Move the byte data to the buffer

                arrayimage = buffer.array(); //Get the underlying array containing the data.
                Log.e("Image-->",arrayimage.toString());
            }
        }
    }

    @Override
    public void onConnected(Bundle bundle) {

//        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
//                mGoogleApiClient);
//        if (mLastLocation != null) {
//            //TODO - do something with lat and long
//           //mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
//            //mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
//        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

public void sendData(){
        try {
            cursor = dbAccess.getUser();
            name = cursor.getString(cursor.getColumnIndex("name"));
            email = cursor.getString(cursor.getColumnIndex("email"));
            address = cursor.getString(cursor.getColumnIndex("location"));
            city = cursor.getString(cursor.getColumnIndex("location"));
            date = DateFormat.getTimeInstance().format(new Date());
            lat = 10.0;
            lon = 10.0;
            compMessage = compText.getText().toString();
            if (arrayimage != null) {

                dbAccess.addComplaint(compMessage, arrayimage, name, email, date, lat, lon, address);

            }
        }catch (Exception e){
           e.printStackTrace();
        }
}

}//class end
