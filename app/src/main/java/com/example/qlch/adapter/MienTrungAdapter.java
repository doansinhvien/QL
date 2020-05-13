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

import com.example.qlch.DAO.MienTrungDAO;
import com.example.qlch.MienTrung_Activity;
import com.example.qlch.R;
import com.example.qlch.model.TraiCay;

import java.util.List;

public class MienTrungAdapter extends BaseAdapter {
    List<TraiCay> arrTraiCay;
    public MienTrung_Activity context;
    LayoutInflater inflater;
    MienTrungDAO mienTrungDAO;
    public MienTrungAdapter(MienTrung_Activity context, List<TraiCay> arrTraiCay) {
        this.arrTraiCay = arrTraiCay;
        this.context = context;
        this.inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mienTrungDAO = new MienTrungDAO(context);
    }

    @Override
    public int getCount() {
        return arrTraiCay.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    public static class ViewHolder{
        ImageView imgAnh, imgDelete;
        TextView tvName, tvMota;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_traicay,null);
            holder.imgAnh = convertView.findViewById(R.id.imvAnh);
            holder.imgDelete = convertView.findViewById(R.id.imvClose);
            holder.tvMota = convertView.findViewById(R.id.tvMoTa);
            holder.tvName = convertView.findViewById(R.id.tvTen);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mienTrungDAO.deleteTraiCay(arrTraiCay.get(position).getTen());
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

        holder.imgAnh.setImageBitmap(bitmap);
        holder.tvName.setText(traiCay.getTen());
        holder.tvMota.setText(traiCay.getMota());
        return convertView;
    }
}
