package com.example.lenovo.ambilcamera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Bitmap bitmap;
    int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.imageView);
        Button btnCamera = findViewById(R.id.btnCamera);
        File file = new File("/Pictures/timbersih/" + "test" + ".JPG");
        if(file.exists()){
            try {
                bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            } catch (OutOfMemoryError ignored) {
                Toast.makeText(getApplicationContext(), "Sorry, OUT OF MEMORY in your mobile", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "File Not Exist in : " + file.toString(), Toast.LENGTH_LONG).show();
        }
        imageView.setImageBitmap(bitmap);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mulaiCamera();
            }
        });
    }

    public void mulaiCamera() {
        Intent takePictureintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri UrlGambar;
        File file = new File("Pictures/timbersih/"+ "Test" + ".JPG");

        Log.e("MulaiBekerja","file : "+file.toString());

        UrlGambar = Uri.fromFile(file);
        try {
            takePictureintent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, UrlGambar);
            takePictureintent.putExtra("return-data", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (takePictureintent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureintent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            File file = new File("/Pictures/timbersih/"+ "Test" + ".JPG");
            if(file.exists()){
                Toast.makeText(this, "path Sudah Tersedia", Toast.LENGTH_SHORT).show();
            }else{
                file.mkdir();
                if(file.mkdir()){
                    Toast.makeText(this, "path sudah dibuat", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Path tidak terbuat", Toast.LENGTH_SHORT).show();
                }
            }
        }finish();
        startActivity(getIntent());
    }
}
