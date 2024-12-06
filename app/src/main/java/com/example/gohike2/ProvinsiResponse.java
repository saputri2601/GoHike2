package com.example.gohike2;

public class ProvinsiResponse {
    private final String nama;
    private final int imageResource; // Menggunakan integer untuk resource drawable

    public ProvinsiResponse(String nama, int imageResource) {
        this.nama = nama;
        this.imageResource = imageResource;
    }

    public String getNama() {
        return nama;
    }

    public int getImageResource() {
        return imageResource;
    }
}
