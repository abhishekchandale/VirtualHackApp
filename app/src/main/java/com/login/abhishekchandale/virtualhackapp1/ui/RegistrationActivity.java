package com.login.abhishekchandale.virtualhackapp1.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.login.abhishekchandale.virtualhackapp1.R;

/**
 * Created by Abhishek.Chandale on 12/29/2015.
 */
public class RegistrationActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText ed_name,ed_email,ed_pass,ed_cpass,ed_mobile,ed_address,ed_gender,ed_bday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnRegister=(Button)findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Registration();
            }
        });
    }

public void Registration(){
    ed_name=(EditText)findViewById(R.id.edit_nome);
    ed_email=(EditText)findViewById(R.id.edit_email);
    ed_pass=(EditText)findViewById(R.id.edit_password);
    ed_cpass=(EditText)findViewById(R.id.edit_confirmpass);
    ed_address=(EditText)findViewById(R.id.edit_location);
    ed_mobile=(EditText)findViewById(R.id.edit_mobile);





}





}
