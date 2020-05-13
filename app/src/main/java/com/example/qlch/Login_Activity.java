package com.example.qlch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qlch.network.LoginRes;
import com.example.qlch.network.NetContext;
import com.example.qlch.network.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Activity extends AppCompatActivity {
    private EditText etUser, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("ĐĂNG NHẬP");
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
        etPass = findViewById(R.id.etPass);
        etUser = findViewById(R.id.etUser);
    }


    public void login(View view) {
        if (etPass.length() > 0 && etUser.length() > 0) {
            Service service = NetContext.instance.create(Service.class);
            service.login(etUser.getText().toString(),etPass.getText().toString()).enqueue(new Callback<LoginRes>() {
                @Override
                public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                    LoginRes body = response.body();
                    if(body.isStatus()){

                        startActivity(new Intent(Login_Activity.this, Main_Activity.class));
                    }else {
                        Toast.makeText(Login_Activity.this,body.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginRes> call, Throwable t) {
t.printStackTrace();
                    Toast.makeText(Login_Activity.this,"Co loi xay ra, lien he quan tri vien",Toast.LENGTH_LONG).show();
                }
            });
        } else {

            Toast.makeText(Login_Activity.this,"Noi dung dang nhap trong",Toast.LENGTH_LONG).show();
        }
    }
}
