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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bharatloan.ApiController;
import com.example.bharatloan.R;
import com.example.bharatloan.model.ModelInsertPersonalDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentDetailsActivity extends AppCompatActivity {
    TextView submit_button_on_payment_details;
    EditText account_number, ifsc_code, bank_name;



    String mobile;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);
        account_number = findViewById(R.id.account_number);
        ifsc_code = findViewById(R.id.ifsc_code);
        bank_name = findViewById(R.id.bank_name);
        submit_button_on_payment_details = findViewById(R.id.submit_button_on_payment_details);

        SharedPreferences preferences =getSharedPreferences("credential",MODE_PRIVATE);
        mobile = preferences.getString("mob_num","");



        submit_button_on_payment_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loan_amount = getIntent().getStringExtra("loan_amount1");
                String tenure = getIntent().getStringExtra("tenure1");
                String ac_num = account_number.getText().toString();
                String ifsc_c = ifsc_code.getText().toString();
                String b_name = bank_name.getText().toString();
                if (TextUtils.isEmpty(ac_num)) {
                    account_number.setError("Required");

                } else if (TextUtils.isEmpty(ifsc_c)) {
                    ifsc_code.setError("Required");

                } else if (TextUtils.isEmpty(b_name)) {
                    bank_name.setError("Required");

                } else {
                    //progressDialog();
                    Call<ModelInsertPersonalDetails> call = ApiController.getInstance().getApi().sendLoanAmount(mobile,loan_amount,tenure,ac_num,
                            ifsc_c,b_name);
                    call.enqueue(new Callback<ModelInsertPersonalDetails>() {
                        @Override
                        public void onResponse(Call<ModelInsertPersonalDetails> call, Response<ModelInsertPersonalDetails> response) {
                            ModelInsertPersonalDetails data = response.body();
                            String ss = data.getMessage();
                            if (ss.equals("inserted")){
                                Intent intent = new Intent(getApplicationContext(), PaymentMethodActivity.class);
                                intent.putExtra("loan_amount_two",loan_amount);
                                intent.putExtra("tenure2",tenure);
                                startActivity(intent);
                                finish();

                            }
                        }

                        @Override
                        public void onFailure(Call<ModelInsertPersonalDetails> call, Throwable t) {

                        }
                    });


                }

            }
        });
    }
    public void progressDialog(){

        // Show the "Please Wait" dialog
        ProgressDialog progressDialog = new ProgressDialog(PaymentDetailsActivity.this);
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