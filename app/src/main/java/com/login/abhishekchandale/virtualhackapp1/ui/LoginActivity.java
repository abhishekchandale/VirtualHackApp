package com.login.abhishekchandale.virtualhackapp1.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.login.abhishekchandale.virtualhackapp1.R;
import com.login.abhishekchandale.virtualhackapp1.database.DbAccess;

/**
 * Created by abhi on 15-01-2016.
 */
public class LoginActivity extends AppCompatActivity {
    private Button btnSignIn;
    private DbAccess dbAccess;
    private ProgressDialog  pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbAccess=new DbAccess(this);
        setContentView(R.layout.activity_login);
        pDialog=new ProgressDialog(this);
        dbAccess=new DbAccess(this);

        btnSignIn=(Button)findViewById(R.id.email_sign_in_button);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    pDialog.setMessage("pleast wait..");
                    pDialog.show();
                    dbAccess.addUser("Abhishek","abhishek.chandale@happiestminds.com","male","29Apr","Bomenalli Bangalore","admin");
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    pDialog.dismiss();
                }catch (Exception e){

                    e.printStackTrace();
                }
            }
        });
    }
}
