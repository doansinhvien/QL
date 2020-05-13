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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qlch.DAO.MienTrungDAO;
import com.example.qlch.MienTrung_Activity;
import com.example.qlch.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddMienTrung_Activity extends AppCompatActivity {
    Button btn;
    ImageView img;
    EditText edMota, edTen;
    int requestcode_camera = 123;
    int requestcode_chooseFile = 456;
    MienTrungDAO mienTrungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mien_trung_);
        setTitle("THÊM MỚI");
        init();
    }

    public void chupAnh(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, requestcode_camera);

    }

    private void init() {
        btn = findViewById(R.id.btn);
        img = findViewById(R.id.imgView);
        edMota = findViewById(R.id.edMoTa);
        edTen = findViewById(R.id.edTen);
    }

    public void chooseFile(View view) {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, requestcode_chooseFile);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == requestcode_camera && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(bitmap);
        }
        if (requestCode == requestcode_chooseFile && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void saveFile(View view) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArray);
        byte[] hinhanh = byteArray.toByteArray();

        mienTrungDAO = new MienTrungDAO(AddMienTrung_Activity.this);


        boolean isInsert = mienTrungDAO.insertDoVat(edTen.getText().toString().trim(),
                edMota.getText().toString(), hinhanh);
        if (isInsert) {
            Toast.makeText(this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddMienTrung_Activity.this, MienTrung_Activity.class));
            finish();
        } else {
            Toast.makeText(this, "Thêm Thất bại", Toast.LENGTH_SHORT).show();
        }

    }

}
