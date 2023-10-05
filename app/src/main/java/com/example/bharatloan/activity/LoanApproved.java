package com.example.bharatloan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.bharatloan.R;

public class LoanApproved extends AppCompatActivity {
    TextView submit_button_on_loan_approve,loan_amount,tenure,total_loan_amount;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_approved);
        loan_amount = findViewById(R.id.loan_amount);
        tenure = findViewById(R.id.tenure);
        total_loan_amount = findViewById(R.id.total_loan_amount);
        submit_button_on_loan_approve=findViewById(R.id.submit_button_on_loan_approve);

        String loan_amount_s = getIntent().getStringExtra("loan_amount");
        String tenure_s = getIntent().getStringExtra("tenure");
        loan_amount.setText(loan_amount_s);
        tenure.setText(tenure_s);
        total_loan_amount.setText(loan_amount_s);

        submit_button_on_loan_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   progressDialog();
                Intent intent = new Intent(getApplicationContext(), PaymentDetailsActivity.class);
                intent.putExtra("loan_amount1",loan_amount.getText().toString());
                intent.putExtra("tenure1",tenure.getText().toString());
                startActivity(intent);
                finish();
            }
        });

    }
    public void progressDialog(){

        // Show the "Please Wait" dialog
        ProgressDialog progressDialog = new ProgressDialog(LoanApproved.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Dismiss the dialog after 4 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        }, 9000); // 4000 milliseconds = 4 seconds
    }
}