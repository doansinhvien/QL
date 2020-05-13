package com.example.qlch.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.qlch.database.DatabaseHelper;
import com.example.qlch.model.TraiCay;

import java.util.ArrayList;
import java.util.List;

public class MienTrungDAO {
    public static final String TABLE_NAME = "TraiCay";
    public DatabaseHelper dbHelper;
    public SQLiteDatabase db;
    public static final String SQL_TRAICAY =" CREATE TABLE IF NOT EXISTS TraiCay(" +
            "Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten VARCHAR(150), Mota VARCHAR(250),HinhAnh BLOB)";

    public MienTrungDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean insertDoVat(String ten, String mota, byte[] hinh) {
        ContentValues values = new ContentValues();
        values.put("Ten",ten);
        values.put("MoTa",mota);
        values.put("HinhAnh",hinh);
        long result = db.insert(TABLE_NAME,null,values);
        try {
            if (result == -1) {
                return false;
            }

        } catch (Exception e) {
            Log.e("TheLoaiDAO", e.toString());
            return false;
        }
        return true;

    }

    public List<TraiCay> getAllTraiCay() {
        List<TraiCay> dsTraiCay = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null,
                null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            TraiCay traiCay = new TraiCay();
            traiCay.setId(c.getInt(0));
            traiCay.setTen(c.getString(1));
            traiCay.setMota(c.getString(2));
            traiCay.setHinh(c.getBlob(3));
            dsTraiCay.add(traiCay);
            Log.d("//=====", traiCay.toString());
            c.moveToNext();

        }
        c.close();
        return dsTraiCay;
    }

    public int deleteTraiCay(String Ten){
        long result = db.delete(TABLE_NAME,"Ten=?",new String[]{Ten});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
}
