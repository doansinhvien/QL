package com.example.qlch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.qlch.DAO.MienNamDAO;
import com.example.qlch.activity.AddMienNam_Activity;
import com.example.qlch.adapter.MienNamAdapter;
import com.example.qlch.database.DatabaseHelper;
import com.example.qlch.model.TraiCay;

import java.util.ArrayList;
import java.util.List;

public class MienNam_Activity extends AppCompatActivity {

    List<TraiCay> dsTraiCay = new ArrayList<>();
    MienNamDAO mienNamDAO;
    ListView lvMienNam;
    MienNamAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Trái Cây Miền Nam");
        setContentView(R.layout.activity_mien_nam_);
        lvMienNam = findViewById(R.id.lvmienNam);

       mienNamDAO = new MienNamDAO(MienNam_Activity.this);
       dsTraiCay = mienNamDAO.getAllTraiCay();
       adapter = new MienNamAdapter(dsTraiCay,this);
       lvMienNam.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.miennam,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.addMNam){
            startActivity(new Intent(MienNam_Activity.this, AddMienNam_Activity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
