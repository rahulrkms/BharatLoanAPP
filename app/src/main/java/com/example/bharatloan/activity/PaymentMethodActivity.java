package com.example.bharatloan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bharatloan.R;

public class PaymentMethodActivity extends AppCompatActivity {
    TextView submit_button_on_payment_method_activity,new_loan_amount,loanplustenure,repayment_amount,received_amount,gst_amount,interest_amount;
    String loan_am;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        CheckBox acceptTermsCheckBox = findViewById(R.id.acceptTermsCheckBox);
        submit_button_on_payment_method_activity = findViewById(R.id.submit_button_on_payment_method_activity);
        new_loan_amount =  findViewById(R.id.new_loan_amount);
        loanplustenure = findViewById(R.id.loanplustenure);
        repayment_amount = findViewById(R.id.repayment_amount);
        received_amount = findViewById(R.id.received_amount);
        gst_amount = findViewById(R.id.gst_amount);
        interest_amount = findViewById(R.id.interest_amount);

        loan_am = getIntent().getStringExtra("loan_amount_two");

        String teneure_am = getIntent().getStringExtra("tenure2");
//        String loan_am = "100000";
//        String teneure_am = "3 Months";
        int lam = Integer.parseInt(loan_am);
        int new_lam = lam+((lam*10)/100);
        String new_loan_am = String.valueOf(new_lam);
        String loantenam = loan_am+"/"+ teneure_am;
        int interestam = (lam*5)/100;
        String interest_am = String.valueOf(interestam);
        String repay_am = String.valueOf(lam+interestam);

        new_loan_amount.setText(loan_am);
        loanplustenure.setText(loantenam);
        interest_amount.setText(interest_am);
        repayment_amount.setText(repay_am);
        received_amount.setText(loan_am);
        gst_amount.setText("1100");


        acceptTermsCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    submit_button_on_payment_method_activity.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                           // progressDialog();
                            Intent intent = new Intent(getApplicationContext(), PayActivity.class);
                            intent.putExtra("loanplustenure",loantenam);
                            intent.putExtra("loan_amount",loan_am);
                            startActivity(intent);
                            finish();
                        }
                    });


                    // The user has checked the checkbox, you can perform actions here.
                } else {
                    Toast.makeText(PaymentMethodActivity.this, "Accept Term and Conditions", Toast.LENGTH_SHORT).show();                    // The user has unchecked the checkbox, you can react accordingly.
                }
            }
        });

    }
    public void progressDialog(){

        // Show the "Please Wait" dialog
        ProgressDialog progressDialog = new ProgressDialog(PaymentMethodActivity.this);
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