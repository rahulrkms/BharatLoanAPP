package com.example.bharatloan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bharatloan.R;

public class SelectAmount extends AppCompatActivity {

    private ProgressBar progressBar;
    private SeekBar seekBar;
    EditText selected_amount_on_progress;
    TextView submit_button_on_select_amount;

    String [] tenure = {"3 Month","6 Month","1 Year","2 Year"};
    AutoCompleteTextView auto_complete_text;
    ArrayAdapter<String> tenureAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_amount);
//        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        selected_amount_on_progress = findViewById(R.id.selected_amount_on_progress);
        auto_complete_text = findViewById(R.id.auto_complete_text);
        submit_button_on_select_amount = findViewById(R.id.submit_button_on_select_amount);

        submit_button_on_select_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(auto_complete_text.getText().toString())){
                    Toast.makeText(SelectAmount.this, "Please Select Tenure", Toast.LENGTH_SHORT).show();
                }else {
                  //  progressDialog();
                    Intent intent = new Intent(getApplicationContext(), LoanApproved.class);
                    intent.putExtra("loan_amount",selected_amount_on_progress.getText().toString());
                    intent.putExtra("tenure",auto_complete_text.getText().toString());
                    startActivity(intent);
                    finish();
                }

            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int progressval = progress;
                if (progressval==0){
                    selected_amount_on_progress.setText("25000");

                } else if (progressval==1) {
                    selected_amount_on_progress.setText("50000");
                }else if (progressval==2) {
                    selected_amount_on_progress.setText("100000");
                }else if (progressval==3) {
                    selected_amount_on_progress.setText("200000");
                }else if (progressval==4) {
                    selected_amount_on_progress.setText("250000");
                }else if (progressval==5) {
                    selected_amount_on_progress.setText("300000");
                }else if (progressval==6) {
                    selected_amount_on_progress.setText("350000");
                }else if (progressval==7) {
                    selected_amount_on_progress.setText("400000");
                }else if (progressval==8) {
                    selected_amount_on_progress.setText("450000");
                } else {
                    selected_amount_on_progress.setText("500000");
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        tenureAdapter = new ArrayAdapter<String>(this, R.layout.list_tenure,tenure);
        auto_complete_text.setAdapter(tenureAdapter);
        auto_complete_text.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String item = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(SelectAmount.this, item, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void progressDialog(){

        // Show the "Please Wait" dialog
        ProgressDialog progressDialog = new ProgressDialog(SelectAmount.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Dismiss the dialog after 4 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        }, 6000); // 4000 milliseconds = 4 seconds
    }
}