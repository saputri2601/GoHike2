package com.example.gohike2;

public class Gunung {
    private String name;
    private int imageResId;

    public Gunung(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }
}
