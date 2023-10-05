package com.example.bharatloan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bharatloan.R;

public class Dashboard extends AppCompatActivity {

    LinearLayout apply_loan,activeLoan,loan_status,get_personal_loan,student_loan,instance_loan;
    TextView user_profile;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        apply_loan = findViewById(R.id.apply_loan);
        user_profile = findViewById(R.id.user_profile);
        activeLoan = findViewById(R.id.activeLoan);
        loan_status = findViewById(R.id.loan_status);
        get_personal_loan = findViewById(R.id.get_personal_loan);
        student_loan = findViewById(R.id.student_loan);
        instance_loan = findViewById(R.id.instance_loan);

//        user_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
//            }
//        });
        loan_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoanStatus.class));
            }
        });
        get_personal_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PersonalDetails.class));
            }
        });
        student_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PersonalDetails.class));
            }
        });
        instance_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PersonalDetails.class));
            }
        });

        activeLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "";   // Paste Your whatsapp link
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


        apply_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PersonalDetails.class));
            }
        });

    }
}