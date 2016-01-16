package com.login.abhishekchandale.virtualhackapp1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.login.abhishekchandale.virtualhackapp1.R;

/**
 * Created by Abhishek.Chandale on 12/28/2015.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton btnRaiseComp,btnPreviousComp,btnTutorial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnRaiseComp=(ImageButton)findViewById(R.id.btn_sendcomp);
        btnPreviousComp=(ImageButton)findViewById(R.id.btn_previewcomp);
        btnTutorial=(ImageButton)findViewById(R.id.btn_tutorial);
        btnRaiseComp.setOnClickListener(this);
        btnPreviousComp.setOnClickListener(this);
        btnTutorial.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_sendcomp:
               startActivity(new Intent(getApplicationContext(),RaiseComplaintActivity.class));
                break;
            case R.id.btn_previewcomp:

                break;
            case R.id.btn_tutorial:

                break;

        }

    }
}
