package com.example.qlch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.qlch.DAO.MienBacDAO;
import com.example.qlch.activity.AddMienBac_Activity;
import com.example.qlch.adapter.MienBacAdapter;
import com.example.qlch.model.TraiCay;

import java.util.ArrayList;
import java.util.List;

public class MienBac_Activity extends AppCompatActivity {

    List<TraiCay> dsTraiCay = new ArrayList<>();
    MienBacDAO mienBacDAO;
    ListView lvMienBac;
    MienBacAdapter mienBacAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Trái Cây Miền Bắc");
        setContentView(R.layout.activity_mien_bac_);
        lvMienBac = findViewById(R.id.lvMienBac);

        mienBacDAO = new MienBacDAO(MienBac_Activity.this);
        dsTraiCay = mienBacDAO.getAllTraiCay();
        mienBacAdapter = new MienBacAdapter(dsTraiCay,this);
        lvMienBac.setAdapter(mienBacAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menubac,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.addMBac){
           startActivity(new Intent(MienBac_Activity.this, AddMienBac_Activity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
