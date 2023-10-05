package com.example.bharatloan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bharatloan.MainActivity;
import com.example.bharatloan.R;

public class ProfileActivity extends AppCompatActivity {

    LinearLayout ll_signout,message_center,contact_us,linearLayout3;
    TextView full_name;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ll_signout = findViewById(R.id.ll_signout);
        message_center = findViewById(R.id.message_center);
        contact_us = findViewById(R.id.contact_us);
        full_name = findViewById(R.id.full_name);
        linearLayout3 = findViewById(R.id.linearLayout3);

        SharedPreferences sh = getSharedPreferences("credential", Context.MODE_PRIVATE);
        String s1 = sh.getString("full_name", "");
        full_name.setText(s1);

        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AllDetails.class));
            }
        });

        message_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://khata.pe/p/xYZDdWSJj?ui=v2?s=pr&m=w";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://khata.pe/p/xYZDdWSJj?ui=v2?s=pr&m=w";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        ll_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences =getSharedPreferences("credential",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }
}