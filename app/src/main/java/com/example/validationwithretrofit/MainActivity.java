package com.example.validationwithretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText t1, t2;
    Button loginbtn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        loginbtn=findViewById(R.id.loginbtn);
        tv=findViewById(R.id.tv);

        checkuserexistence();


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processLogin();
            }
        });



    }

    public void checkuserexistence() {

        SharedPreferences sharedPreferences = getSharedPreferences("credentials",MODE_PRIVATE);
        if(sharedPreferences.contains("email")){
            startActivity(new Intent(getApplicationContext(), Dashboard.class));

        }else{

            tv.setText("Please Login");
            tv.setTextColor(Color.RED);
        }


    }

    public void processLogin() {

        String email = t1.getText().toString();
        String password = t2.getText().toString();

        Call<ValidModel> call = ApiController
                                .getInstance()
                                .getApiList()
                                .verifyLogin(email,password);

        call.enqueue(new Callback<ValidModel>() {
            @Override
            public void onResponse(Call<ValidModel> call, Response<ValidModel> response) {
                ValidModel obj = response.body();
                String output = obj.getMessage();
                if(output.equals("failed")){
                    t1.setText("");
                    t2.setText("");
                    tv.setTextColor(Color.RED);
                    tv.setText("Invalid Email or Password");
                }
                if(output.equals("exist")){

                    SharedPreferences sharedPreferences = getSharedPreferences("credentials",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email",t1.getText().toString());
                    editor.putString("password",t2.getText().toString());
                    editor.commit();
                    editor.apply();


                    startActivity(new Intent(getApplicationContext(),Dashboard.class));
                    finish();

                }


            }

            @Override
            public void onFailure(Call<ValidModel> call, Throwable t) {
                    tv.setText("Something went wrong");
                    tv.setTextColor(Color.RED);
            }
        });
    }
}