package com.example.bharatloan.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bharatloan.ApiController;
import com.example.bharatloan.R;
import com.example.bharatloan.model.ModelFetchStaticLink;
import com.example.bharatloan.model.ModelInsertPersonalDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayActivity extends AppCompatActivity {
    TextView someTextTextView, pay_profile, redirect_btn, total_amount, gst_amount_activity_pay, service_tax_activity_pay, loan_amount_with_tenure, submit_button_on_pay_activity;
    private TextView timerTextView;
    String loan_amount,link;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        timerTextView = findViewById(R.id.timerTextView);
        someTextTextView = findViewById(R.id.someTextTextView);
        gst_amount_activity_pay = findViewById(R.id.gst_amount_activity_pay);
        service_tax_activity_pay = findViewById(R.id.service_tax_activity_pay);
        loan_amount_with_tenure = findViewById(R.id.loan_amount_with_tenure);
        submit_button_on_pay_activity = findViewById(R.id.submit_button_on_pay_activity);

        total_amount = findViewById(R.id.total_amount);
        redirect_btn = findViewById(R.id.redirect_btn);
        redirect_btn.setVisibility(View.INVISIBLE);
        pay_profile = findViewById(R.id.pay_profile);


        String loanOptions = getIntent().getStringExtra("loanplustenure");
        loan_amount = getIntent().getStringExtra("loan_amount");
        loan_amount_with_tenure.setText(loanOptions);
        total_amount.setText("1110");

        pay_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                finish();
            }
        });

        // Create a CountDownTimer with a 30-second countdown
        new CountDownTimer(300000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Update the timerTextView with the remaining time
                long seconds = millisUntilFinished / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                seconds %= 60;
                minutes %= 60;
                hours %= 24;
                String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                timerTextView.setText(time);
            }

            public void onFinish() {
                // Timer has finished, you can perform any action here
                timerTextView.setText("00:00:00");
            }
        }.start();


        submit_button_on_pay_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit_button_on_pay_activity.setVisibility(View.INVISIBLE);
                redirect_btn.setVisibility(View.VISIBLE);
                SharedPreferences sharedPreferences = getSharedPreferences("credential", MODE_PRIVATE);
                String mob_numm = sharedPreferences.getString("mob_num","");
                String pay_again = "Reactivate";
                String quotes = "Payment Processing!! \n Contact Customer Executive!!";
                //progressDialog();

                Call<List<ModelFetchStaticLink>> call2 = ApiController.getInstance().getApi().fetchStaticAmount();
                call2.enqueue(new Callback<List<ModelFetchStaticLink>>() {
                    @Override
                    public void onResponse(Call<List<ModelFetchStaticLink>> call, Response<List<ModelFetchStaticLink>> response) {
                        List<ModelFetchStaticLink> model1 = response.body();
                        for (int i=0; i<model1.size();i++){

                            link = model1.get(i).getStatic_payment();
                            Intent intnt = new Intent(Intent.ACTION_VIEW);
                            intnt.setData(Uri.parse(link));
                            startActivity(intnt);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<ModelFetchStaticLink>> call, Throwable t) {

                    }
                });


                String pay_next_amount = "https://khata.pe/p/xYZDdWSJj?ui=v2?s=pr&m=w";
                Call<ModelInsertPersonalDetails> call = ApiController.getInstance().getApi().sendPay(mob_numm,loan_amount,pay_again,link,quotes);
                call.enqueue(new Callback<ModelInsertPersonalDetails>() {
                    @Override
                    public void onResponse(Call<ModelInsertPersonalDetails> call, Response<ModelInsertPersonalDetails> response) {
                        ModelInsertPersonalDetails model = response.body();
                        String msg = model.getMessage();
                        if (msg.equals("inserted")){
                            String url = "https://khata.pe/p/xYZDdWSJj?ui=v2?s=pr&m=w";


                        }
                    }

                    @Override
                    public void onFailure(Call<ModelInsertPersonalDetails> call, Throwable t) {
                        Toast.makeText(PayActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }


        });

        redirect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayActivity.this,LoanStatus.class);
                intent.putExtra("loan_amount",loan_amount);
                startActivity(intent);
                finish();

            }
        });


    }

    public void progressDialog() {

        // Show the "Please Wait" dialog
        ProgressDialog progressDialog = new ProgressDialog(PayActivity.this);
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