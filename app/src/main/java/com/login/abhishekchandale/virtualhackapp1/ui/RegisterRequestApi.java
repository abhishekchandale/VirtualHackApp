package com.login.abhishekchandale.virtualhackapp1.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.abhishek.chandale.myapplication.backend.complaintApi.ComplaintApi;
import com.example.abhishek.chandale.myapplication.backend.complaintApi.model.Complaint;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

class RegisterRequestApi extends AsyncTask<Void, Void, List<Complaint>> {
    private static ComplaintApi myApiService = null;
    private Context context;
    private ProgressDialog progressDialog;

    RegisterRequestApi(Context context) {
        this.context = context;
        progressDialog=new ProgressDialog(this.context);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Loading");
        progressDialog.show();
    }

    @Override
    protected List<Complaint> doInBackground(Void... params) {
        if (myApiService == null) {  // Only do this once
            ComplaintApi.Builder builder = new ComplaintApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("https://virtualhack-1193.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.listComplaint().execute().getItems();
        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    protected void onPostExecute(List<Complaint> result) {
        for (Complaint q : result) {
            Toast.makeText(context, q.getComplaintMessage() + " : " + q.getComplaintAddress() + q.getId(), Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    }
}
