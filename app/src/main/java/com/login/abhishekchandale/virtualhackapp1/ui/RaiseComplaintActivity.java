package com.login.abhishekchandale.virtualhackapp1.ui;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.abhishek.chandale.myapplication.backend.complaintApi.ComplaintApi;
import com.example.abhishek.chandale.myapplication.backend.complaintApi.model.Complaint;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.login.abhishekchandale.virtualhackapp1.R;
import com.login.abhishekchandale.virtualhackapp1.database.DbAccess;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import static com.example.abhishek.chandale.myapplication.backend.complaintApi.ComplaintApi.*;

public class RaiseComplaintActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int CAMERA_REQUEST = 1888;
    private ImageButton photoButton, btnpost;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Cursor cursor;
    private DbAccess dbAccess;
    private String name, city, address, email, date, image, compMessage;
    private Double lat=10.0, lon=11.0;
    private byte[] arrayimage;
    private EditText compText;
    private ProgressDialog pDailog;
    private String TAG = "RaiseComplaint";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbAccess = new DbAccess(this);
        setContentView(R.layout.raise_complaint_activity);
        pDailog = new ProgressDialog(this);
        compText = (EditText) findViewById(R.id.txt_comp_message);

        cursor = dbAccess.getUser();
        if (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex("name"));
            email = cursor.getString(cursor.getColumnIndex("email"));
            address = cursor.getString(cursor.getColumnIndex("location"));
            city = cursor.getString(cursor.getColumnIndex("location"));
            date = DateFormat.getTimeInstance().format(new Date());
        } else {

            Log.e(TAG, "cursor -->null");

        }

        photoButton = (ImageButton) this.findViewById(R.id.take_pic);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        btnpost = (ImageButton) findViewById(R.id.btn_post);
        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compMessage = compText.getText().toString();
                if (compMessage != null)
                    new uploadDatatoServer(getApplicationContext()).execute();
                dbAccess.addComplaint(compMessage, arrayimage, name, email, date, lat, lon, address);
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
                Log.e("Image-->", arrayimage.toString());
            }
        }
    }

    @Override
    public void onConnected(Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            lat=mLastLocation.getLatitude();
            lon=mLastLocation.getLongitude();
            Log.e(TAG,"lat->"+lat+"lon-->"+lon);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


class uploadDatatoServer extends AsyncTask<String,Void,Complaint>{

    Context context;
    public  uploadDatatoServer(Context context){
        this.context=context;
        Log.e(TAG,"Latitude"+lat+"longitude"+lon);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e(TAG,"AsynkPreE");
        pDailog.setMessage("uploading request..");
        pDailog.show();
    }

    @Override
    protected Complaint doInBackground(String... params) {
        date = DateFormat.getDateInstance().format(new Date());
        Log.e(TAG,date);
        Complaint response=null;
        try {

            ComplaintApi.Builder builder = new ComplaintApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://virtualhack-1193.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            ComplaintApi service = builder.build();
            Complaint complaint = new Complaint();
            complaint.setComplaintMessage(compMessage);
            complaint.setName(name);
            complaint.setEmail(email);
            complaint.setLat(lat);
            complaint.setLon(lon);
            complaint.setComplaintAddress(address);
            complaint.setImage(image);
            complaint.setMobile("1234567");
            complaint.setCity(city);
            response=service.insertComplaint(complaint).execute();

            Log.e(TAG, "Data Upload successfully ");

        }catch (Exception e){
            Log.e(TAG,e.getMessage());
            e.printStackTrace();

        }
        return response;
    }

    @Override
    protected void onPostExecute(Complaint complaint) {
        super.onPostExecute(complaint);
        pDailog.dismiss();
        startActivity(new Intent(getApplicationContext(),PreviousComplaintsActivity.class));
        finish();
    }
}



}//class end
