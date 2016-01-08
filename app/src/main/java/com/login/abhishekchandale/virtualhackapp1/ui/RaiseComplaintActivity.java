package com.login.abhishekchandale.virtualhackapp1.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.login.abhishekchandale.virtualhackapp1.R;

import java.nio.ByteBuffer;

public class RaiseComplaintActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private ImageButton photoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raise_complaint_activity);

        photoButton = (ImageButton) this.findViewById(R.id.take_pic);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
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

                byte[] array = buffer.array(); //Get the underlying array containing the data.
            }
        }
    }
}
