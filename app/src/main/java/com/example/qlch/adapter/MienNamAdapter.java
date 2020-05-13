package com.example.qlch.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qlch.DAO.MienNamDAO;
import com.example.qlch.R;
import com.example.qlch.model.TraiCay;

import java.util.List;

public class MienNamAdapter extends BaseAdapter {
    List<TraiCay> arrTraiCay;
    Context context;
    LayoutInflater inflater;
    MienNamDAO mienNamDAO;

    public MienNamAdapter(List<TraiCay> arrTraiCay, Context context) {
        this.arrTraiCay = arrTraiCay;
        this.context = context;
        this.inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mienNamDAO = new MienNamDAO(context);
    }

    @Override
    public int getCount() {
        return arrTraiCay.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder{
        ImageView imvAnh, imvDelete;
        TextView tvTen, tvMota;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_traicay,null);
            holder.imvAnh = convertView.findViewById(R.id.imvAnh);
            holder.imvDelete= convertView.findViewById(R.id.imvClose);
            holder.tvMota = convertView.findViewById(R.id.tvMoTa);
            holder.tvTen = convertView.findViewById(R.id.tvTen);
            holder.imvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mienNamDAO.deleteTraiCay(arrTraiCay.get(position).getTen());
                    arrTraiCay.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        TraiCay traiCay = arrTraiCay.get(position);

        //Chuyern byte sang bitmap
        byte [] dovat = traiCay.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(dovat,0,dovat.length);

        holder.imvAnh.setImageBitmap(bitmap);
        holder.tvTen.setText(traiCay.getTen());
        holder.tvMota.setText(traiCay.getMota());
        return convertView;
    }
}
