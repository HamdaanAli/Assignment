package com.example.assignment.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FullScreenActivity extends AppCompatActivity {
ImageView fullimage;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        fullimage=findViewById(R.id.full_View);
        Intent n=getIntent();
        b=n.getExtras();
        String imag_uri=(String) b.get("uri");
        Glide.with(getApplicationContext()).load(imag_uri).into(fullimage);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        b.clear();
        startActivity(new Intent(FullScreenActivity.this,MainActivity.class));
    }
}
