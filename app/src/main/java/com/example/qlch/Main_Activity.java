package com.example.qlch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);
        setTitle("Màn hình chính");
    }

    public void mienBac(View view) {
        startActivity(new Intent(Main_Activity.this,MienBac_Activity.class));
    }

    public void mienTrung(View view){
        startActivity(new Intent(Main_Activity.this,MienTrung_Activity.class));
    }
    public void mienNam(View  view){
        startActivity(new Intent(Main_Activity.this,MienNam_Activity.class));
    }
}
