package com.example.bharatloan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bharatloan.ApiController;
import com.example.bharatloan.R;
import com.example.bharatloan.model.ModelFetchRepaymentDetails;
import com.example.bharatloan.model.ModelFetchStaticLink;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanStatus extends AppCompatActivity {
    String loan_amount,link;
    TextView submit_button_on_loan_status_activity,quotes,loanplustenure,profile_btn_loan_status,contact_uss;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_status);

        submit_button_on_loan_status_activity = findViewById(R.id.submit_button_on_loan_status_activity);
        quotes = findViewById(R.id.quotes);
        loanplustenure = findViewById(R.id.loanplustenure);
        profile_btn_loan_status =  findViewById(R.id.profile_btn_loan_status);
        contact_uss = findViewById(R.id.contact_uss);
        contact_uss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "";  // paste your whatsapp link
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        profile_btn_loan_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });

        SharedPreferences sp = getSharedPreferences("credential", MODE_PRIVATE);
        String mob_num =  sp.getString("mob_num","");


        Call<List<ModelFetchRepaymentDetails>>call = ApiController.getInstance().getApi().fetchRepaymentDetail(mob_num);
        call.enqueue(new Callback<List<ModelFetchRepaymentDetails>>() {
            @Override
            public void onResponse(Call<List<ModelFetchRepaymentDetails>> call, Response<List<ModelFetchRepaymentDetails>> response) {
                List<ModelFetchRepaymentDetails> model = response.body();
                for (int i=0; i< model.size();i++){
                    quotes.setText(model.get(i).getQuotes());
                    loanplustenure.setText(model.get(i).getLoan_amount());
                    submit_button_on_loan_status_activity.setText(model.get(i).getPay_again());



                }
            }

            @Override
            public void onFailure(Call<List<ModelFetchRepaymentDetails>> call, Throwable t) {
                loanplustenure.setText("No Loan");
                quotes.setText("");
                submit_button_on_loan_status_activity.setVisibility(View.INVISIBLE);
            }
        });


        submit_button_on_loan_status_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

            }
        });



        loan_amount = getIntent().getStringExtra("loan_amount");

    }
}