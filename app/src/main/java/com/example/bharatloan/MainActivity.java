package com.example.bharatloan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bharatloan.activity.Dashboard;
import com.example.bharatloan.activity.PersonalDetails;

public class MainActivity extends AppCompatActivity {

    TextView login_btn, main_full_name, main_mob_num, main_password, main_confirm_password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_btn = findViewById(R.id.login_btn);
        main_full_name = findViewById(R.id.main_full_name);
        main_mob_num = findViewById(R.id.main_mob_num);
        main_password = findViewById(R.id.main_password);
        main_confirm_password = findViewById(R.id.main_confirm_password);

        verifyuserexistance();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String full_name = main_full_name.getText().toString();
                String mob_num = main_mob_num.getText().toString();
                String password = main_password.getText().toString();
                String confirm_password = main_confirm_password.getText().toString();

                if (TextUtils.isEmpty(full_name)) {
                    main_full_name.setError("Required");

                } else if (TextUtils.isEmpty(mob_num)) {
                    main_mob_num.setError("Required");

                } else if (TextUtils.isEmpty(password)) {
                    main_password.setError("Required");

                } else if (TextUtils.isEmpty(confirm_password)) {
                    main_confirm_password.setError("Required");

                } else if (password.equals(confirm_password)) {
                    saveLoginCredential();
                    startActivity(new Intent(getApplicationContext(), Dashboard.class));
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public void saveLoginCredential() {
        SharedPreferences sharedPreferences = getSharedPreferences("credential", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("full_name", main_full_name.getText().toString());
        myEdit.putString("mob_num", main_mob_num.getText().toString());
        myEdit.putString("password", main_password.getText().toString());
        myEdit.commit();
    }

    public void verifyuserexistance() {
        SharedPreferences sp = getSharedPreferences("credential", MODE_PRIVATE);
        if (sp.contains("full_name") && sp.contains("mob_num")) {
            Intent intent1 = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(intent1);
            finish();
        }

    }
}