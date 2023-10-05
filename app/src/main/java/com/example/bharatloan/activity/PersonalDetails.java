package com.example.bharatloan.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bharatloan.ApiController;
import com.example.bharatloan.R;
import com.example.bharatloan.model.ModelFetchRepaymentDetails;
import com.example.bharatloan.model.ModelInsertPersonalDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalDetails extends AppCompatActivity {
    TextView submit_button_on_personal_details_activity, user_profile;
    EditText personal_first_name, personal_last_name, personal_email, personal_education, personal_monthly_income, personal_annual_family_income;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6;
    RadioGroup radiogroup_emp_type, radiogroup_annual_income;

    String user_mobile_no;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        personal_first_name = findViewById(R.id.personal_first_name);
        personal_last_name = findViewById(R.id.personal_last_name);
        submit_button_on_personal_details_activity = findViewById(R.id.submit_button_on_personal_details_activity);
        personal_email = findViewById(R.id.personal_email);
        personal_education = findViewById(R.id.personal_education);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        personal_monthly_income = findViewById(R.id.personal_monthly_income);
        personal_annual_family_income = findViewById(R.id.personal_annual_family_income);
        radiogroup_emp_type = findViewById(R.id.radiogroup_emp_type);
        radiogroup_annual_income = findViewById(R.id.radiogroup_annual_income);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        radioButton5 = findViewById(R.id.radioButton5);
        radioButton6 = findViewById(R.id.radioButton6);
        user_profile = findViewById(R.id.user_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("credential", MODE_PRIVATE);
        user_mobile_no = sharedPreferences.getString("mob_num", "");


//        user_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
//            }
//        });

        submit_button_on_personal_details_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name = personal_first_name.getText().toString();
                String last_name = personal_last_name.getText().toString();
                String email = personal_email.getText().toString();
                String education = personal_education.getText().toString();
                String monthly_income = personal_monthly_income.getText().toString();
                String annual_family_income = personal_annual_family_income.getText().toString();
                int selectedRadioButtonId = radiogroup_emp_type.getCheckedRadioButtonId();
                int selectedRadioButtonId2 = radiogroup_annual_income.getCheckedRadioButtonId();

                if (TextUtils.isEmpty(first_name)) {
                    personal_first_name.setError("Required");

                } else if (TextUtils.isEmpty(last_name)) {
                    personal_last_name.setError("Required");

                } else if (TextUtils.isEmpty(email)) {
                    personal_email.setError("Required");

                } else if (TextUtils.isEmpty(education)) {
                    personal_education.setError("Required");

                } else if (TextUtils.isEmpty(monthly_income)) {
                    personal_monthly_income.setError("Required");
                } else if (TextUtils.isEmpty(annual_family_income)) {
                    personal_annual_family_income.setError("Required");
                } else if (selectedRadioButtonId == -1) {
                    Toast.makeText(PersonalDetails.this, "Please select Employee Type", Toast.LENGTH_SHORT).show();

                } else if (selectedRadioButtonId2 == -1) {
                    Toast.makeText(PersonalDetails.this, "Please select Annual Income", Toast.LENGTH_SHORT).show();

                } else {
//                    progressDialog();
                    SharedPreferences sp = getSharedPreferences("credential", MODE_PRIVATE);
                    String mob_num = sp.getString("mob_num", "");
                    Call<List<ModelFetchRepaymentDetails>> call = ApiController.getInstance().getApi().fetchRepaymentDetail(mob_num);
                    call.enqueue(new Callback<List<ModelFetchRepaymentDetails>>() {
                        @Override
                        public void onResponse(Call<List<ModelFetchRepaymentDetails>> call, Response<List<ModelFetchRepaymentDetails>> response) {
                            List<ModelFetchRepaymentDetails> model = response.body();
                            for (int i = 0; i < model.size(); i++) {
                                String mmm = model.get(i).getMob_num();
                                if (mmm.equals(mob_num)) {
                                    Toast.makeText(PersonalDetails.this, "Already Applied", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), LoanStatus.class));
                                    finish();

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<ModelFetchRepaymentDetails>> call, Throwable t) {
                            Call<ModelInsertPersonalDetails> call1 = ApiController.getInstance().getApi().sendCreateIdRequestDetails(user_mobile_no, first_name, email);
                            call1.enqueue(new Callback<ModelInsertPersonalDetails>() {
                                @Override
                                public void onResponse(Call<ModelInsertPersonalDetails> call, Response<ModelInsertPersonalDetails> response) {
                                    ModelInsertPersonalDetails data = response.body();
                                    String ss = data.getMessage();
                                    if (ss.equals("inserted")) {
                                        Intent intent = new Intent(getApplicationContext(), PANCARD.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                }

                                @Override
                                public void onFailure(Call<ModelInsertPersonalDetails> call, Throwable t) {

                                }
                            });
                        }
                    });


                }

            }
        });
    }

    public void progressDialog() {

        // Show the "Please Wait" dialog
        ProgressDialog progressDialog = new ProgressDialog(PersonalDetails.this);
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