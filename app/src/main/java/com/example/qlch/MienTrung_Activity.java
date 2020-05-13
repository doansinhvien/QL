package com.example.qlch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.qlch.DAO.MienTrungDAO;
import com.example.qlch.activity.AddMienTrung_Activity;
import com.example.qlch.adapter.MienTrungAdapter;
import com.example.qlch.model.TraiCay;

import java.util.ArrayList;
import java.util.List;

public class MienTrung_Activity extends AppCompatActivity {

    ListView lvTheLoai;
    public static List<TraiCay> dsTraiCay = new ArrayList<>();
    MienTrungAdapter mienTrungAdapter =null;
    MienTrungDAO mienTrungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mien_trung_);
        setTitle("Trái Cây Miền Trung");
        lvTheLoai = (ListView) findViewById(R.id.lvMTrung);

        mienTrungDAO = new MienTrungDAO(MienTrung_Activity.this);
        dsTraiCay = mienTrungDAO.getAllTraiCay();
        mienTrungAdapter = new MienTrungAdapter(this,dsTraiCay);
        lvTheLoai.setAdapter(mienTrungAdapter);
//
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrung,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.addMTrung){
            startActivity(new Intent(MienTrung_Activity.this, AddMienTrung_Activity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
