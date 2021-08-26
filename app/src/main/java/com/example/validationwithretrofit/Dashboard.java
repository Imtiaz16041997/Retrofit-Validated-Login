package com.example.validationwithretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    TextView tv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tv=findViewById(R.id.uemail);
        btn=findViewById(R.id.btnlogout);

        checkuserexistence();

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("credentials",MODE_PRIVATE);
                sharedPreferences.edit().remove("email").commit();
                sharedPreferences.edit().remove("password").commit();
                sharedPreferences.edit().apply();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

    }

    public void checkuserexistence() {
        SharedPreferences sharedPreferences = getSharedPreferences("credentials",MODE_PRIVATE);
        if(sharedPreferences.contains("email")){
            tv.setText(sharedPreferences.getString("email",""));

        }else{
            startActivity(new Intent(getApplicationContext(),MainActivity.class));

        }


    }
}