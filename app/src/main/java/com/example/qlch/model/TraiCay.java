package com.example.qlch.model;

public class TraiCay {
    private int Id;
    private String Ten;
    private String Mota;
    private byte[] Hinh;

    public TraiCay() {
    }

    public TraiCay(int id, String ten, String mota, byte[] hinh) {
        Id = id;
        Ten = ten;
        Mota = mota;
        Hinh = hinh;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public byte[] getHinh() {
        return Hinh;
    }

    public void setHinh(byte[] hinh) {
        Hinh = hinh;
    }
}
