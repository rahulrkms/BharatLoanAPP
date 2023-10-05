package com.example.bharatloan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bharatloan.ApiController;
import com.example.bharatloan.R;
import com.example.bharatloan.model.ModelFetchAllDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllDetails extends AppCompatActivity {
    TextView user_profile,name_on_all_details,email_on_all_details,mobile_on_all_details,aadhar_on_all_details,pan_on_all_details,dob_on_all_details
            ,account_no_on_all_details,ifsc_no_on_all_details,bank_name_on_all_details;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_details);

        user_profile = findViewById(R.id.user_profile);
        name_on_all_details = findViewById(R.id.name_on_all_details);
        email_on_all_details = findViewById(R.id.email_on_all_details);
        mobile_on_all_details = findViewById(R.id.mobile_on_all_details);
        aadhar_on_all_details = findViewById(R.id.aadhar_on_all_details);
        pan_on_all_details = findViewById(R.id.pan_on_all_details);
        dob_on_all_details = findViewById(R.id.dob_on_all_details);
        account_no_on_all_details = findViewById(R.id.account_no_on_all_details);
        ifsc_no_on_all_details = findViewById(R.id.ifsc_no_on_all_details);
        bank_name_on_all_details = findViewById(R.id.bank_name_on_all_details);

        SharedPreferences sharedPreferences = getSharedPreferences("credential", MODE_PRIVATE);
        String mob_num = sharedPreferences.getString("mob_num","");
        user_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });
        Call<ModelFetchAllDetails> call = ApiController.getInstance().getApi().fetchAllDetails(mob_num);
        call.enqueue(new Callback<ModelFetchAllDetails>() {
            @Override
            public void onResponse(Call<ModelFetchAllDetails> call, Response<ModelFetchAllDetails> response) {
                ModelFetchAllDetails apiResponse = response.body();

                // Now you can access data from the ApiResponse object
                List<ModelFetchAllDetails.Document> documents = apiResponse.getDocuments();
                List<ModelFetchAllDetails.LoanAmount> loanAmounts = apiResponse.getLoan_amount();
                List<ModelFetchAllDetails.PersonalDetails> personalDetails = apiResponse.getPersonal_details();
                
                for (int i=0;i< personalDetails.size();i++){
                    name_on_all_details.setText(personalDetails.get(i).getName());
                    email_on_all_details.setText(personalDetails.get(i).getEmail());
                    mobile_on_all_details.setText(mob_num);
                }
                for (int i=0;i< documents.size();i++){
                    aadhar_on_all_details.setText(documents.get(i).getAadhar_card());
                    pan_on_all_details.setText(documents.get(i).getPan_card());
                    dob_on_all_details.setText(documents.get(i).getDob());
                    
                }
                for (int i=0;i< loanAmounts.size();i++){
                    account_no_on_all_details.setText(loanAmounts.get(i).getAccount_no());
                    ifsc_no_on_all_details.setText(loanAmounts.get(i).getIfsc_code());
                    bank_name_on_all_details.setText(loanAmounts.get(i).getBank_name());

                }
            }

            @Override
            public void onFailure(Call<ModelFetchAllDetails> call, Throwable t) {
                Toast.makeText(AllDetails.this, "failed!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}