package com.example.qlch.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.qlch.DAO.MienBacDAO;
import com.example.qlch.DAO.MienNamDAO;
import com.example.qlch.DAO.MienTrungDAO;
import com.example.qlch.MienBac_Activity;
import com.example.qlch.MienNam_Activity;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String  DB_NAME = " abs";
    public static final int VERSION = 1 ;
    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MienTrungDAO.SQL_TRAICAY);
        db.execSQL(MienBacDAO.SQL_MIENBAC);
        db.execSQL(MienNamDAO.SQL_MIENNAM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+MienTrungDAO.SQL_TRAICAY);
        db.execSQL("DROP TABLE IF EXISTS "+MienBacDAO.SQL_MIENBAC);
        db.execSQL("DROP TABLE IF EXISTS "+MienNamDAO.SQL_MIENNAM);
       onCreate(db);
    }
}
