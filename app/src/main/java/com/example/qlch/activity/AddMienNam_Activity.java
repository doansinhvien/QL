package com.example.qlch.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qlch.DAO.MienBacDAO;
import com.example.qlch.DAO.MienNamDAO;
import com.example.qlch.MienNam_Activity;
import com.example.qlch.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AddMienNam_Activity extends AppCompatActivity {

    int requestcode_camera = 123;
    int requestcode_chonFile = 456;
    private EditText edTen,edMoTa;
    private ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mien_nam_2);
        setTitle("THÊM MỚI");
        init();
    }

    private void init() {
        edTen = findViewById(R.id.edTen);
        imgView =findViewById(R.id.imgView);
        edMoTa = findViewById(R.id.edMoTa);
    }


    public void camera(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,requestcode_camera);
    }
    public void chonFile(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,requestcode_chonFile);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==requestcode_camera && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgView.setImageBitmap(bitmap);
        }
        if(requestCode==requestcode_chonFile && resultCode==RESULT_OK && data !=null){
            Uri uri = data.getData();
           try{
               InputStream inputStream = getContentResolver().openInputStream(uri);
               Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
               imgView.setImageBitmap(bitmap);
           }catch (Exception e){

           }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void themMoi(View view) {

        MienNamDAO mienNamDAO = new MienNamDAO(AddMienNam_Activity.this);

        BitmapDrawable drawable = (BitmapDrawable) imgView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,50,byteArray);
        byte []hinh = byteArray.toByteArray();


        boolean isInsert = mienNamDAO.insertTraiCay(edTen.getText().toString(),edMoTa.getText().toString(),hinh);
        if(isInsert){
           Toast.makeText(this,"Thêm thành công",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddMienNam_Activity.this, MienNam_Activity.class));
            finish();
        } else {
            Toast.makeText(this,"Thêm thất bại",Toast.LENGTH_SHORT).show();
        }
    }



}
