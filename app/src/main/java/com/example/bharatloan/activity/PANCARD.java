package com.example.bharatloan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bharatloan.ApiController;
import com.example.bharatloan.R;
import com.example.bharatloan.model.ModelInsertPersonalDetails;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PANCARD extends AppCompatActivity {
    TextView submit_button_on_pancard_activity,pan_dob;
    EditText pan_card_number,pan_pin_code,aadhar_no;
    RadioGroup radioGroup3;
    RadioButton radioButton7,radioButton8,radioButton9;
    String user_mobile_no_pan_activity;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pancard);
        submit_button_on_pancard_activity = findViewById(R.id.submit_button_on_pancard_activity);
        pan_card_number = findViewById(R.id.pan_card_number);
        pan_dob = findViewById(R.id.pan_dob);
        pan_pin_code = findViewById(R.id.pan_pin_code);
        radioGroup3 = findViewById(R.id.radioGroup3);
        radioButton7 = findViewById(R.id.radioButton7);
        radioButton8 = findViewById(R.id.radioButton8);
        radioButton9 = findViewById(R.id.radioButton9);
        aadhar_no = findViewById(R.id.aadhar_no);


        SharedPreferences sharedPreferences = getSharedPreferences("credential", MODE_PRIVATE);
        user_mobile_no_pan_activity = sharedPreferences.getString("mob_num","");


        pan_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        submit_button_on_pancard_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String card_number = pan_card_number.getText().toString();
                String dob = pan_dob.getText().toString();
                String pin_code = pan_pin_code.getText().toString();
                String string_aadhar = aadhar_no.getText().toString();
                int selectedRadioButtonId = radioGroup3.getCheckedRadioButtonId();
                String selectedGender = String.valueOf(selectedRadioButtonId);

                if (TextUtils.isEmpty(card_number)) {
                    pan_card_number.setError("Required");

                } else if (TextUtils.isEmpty(dob)) {
                    pan_dob.setError("Required");

                } else if (TextUtils.isEmpty(pin_code)) {
                    pan_pin_code.setError("Required");

                } else if (TextUtils.isEmpty(string_aadhar)) {
                    aadhar_no.setError("Required");

                }  else if (selectedRadioButtonId == -1) {
                    Toast.makeText(PANCARD.this, "Please select Gender", Toast.LENGTH_SHORT).show();

                } else if (card_number.length()<10) {
                    Toast.makeText(PANCARD.this, "Enter valid PAN number", Toast.LENGTH_SHORT).show();

                }else if (string_aadhar.length()<12) {
                    Toast.makeText(PANCARD.this, "Enter valid Aadhar number", Toast.LENGTH_SHORT).show();

                }else if (pin_code.length()<6) {
                    Toast.makeText(PANCARD.this, "Enter valid Aadhar number", Toast.LENGTH_SHORT).show();

                } else {
                  //  progressDialog();
                    Call<ModelInsertPersonalDetails> call = ApiController.getInstance().getApi().sendDocuments(user_mobile_no_pan_activity,
                            card_number, string_aadhar,dob,selectedGender);
                    call.enqueue(new Callback<ModelInsertPersonalDetails>() {
                        @Override
                        public void onResponse(Call<ModelInsertPersonalDetails> call, Response<ModelInsertPersonalDetails> response) {
                            ModelInsertPersonalDetails data = response.body();
                            String ss = data.getMessage();
                            if (ss.equals("inserted")){
                                Intent intent = new Intent(getApplicationContext(), SelectAmount.class);
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
        ProgressDialog progressDialog = new ProgressDialog(PANCARD.this);
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
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Handle the selected date
                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        pan_dob.setText(selectedDate);
                    }
                }, year, month, day);

        // Set a maximum date to restrict selecting future dates (optional)
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        // Show the date picker dialog
        datePickerDialog.show();
    }
}