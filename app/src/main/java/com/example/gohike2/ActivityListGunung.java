package com.example.gohike2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gohike2.GunungListAdapter;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ActivityListGunung extends AppCompatActivity implements List<Gunung> {

    private RecyclerView recyclerView;

    private GunungListAdapter gunungListAdapter;

    @SuppressLint({"SetTextI18n", "StringFormatInvalid"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_gunung);

        // Inisialisasi TextView untuk menampilkan lokasi
        TextView lokasiPilihanTextView = findViewById(R.id.lokasiPilihan);


        recyclerView = findViewById(R.id.recyclerView);
        // Mengatur GridLayoutManager dengan dua kolom
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);


        // Mendapatkan lokasi dari Intent
        String lokasi = getIntent().getStringExtra("lokasi");

        // Menampilkan lokasi yang dipilih
        if (lokasi != null) {
            lokasiPilihanTextView.setText(getString(R.string.lokasi, lokasi));
        } else {
            lokasiPilihanTextView.setText(R.string.lokasi_tidak_ditemukan);
        }

        // Inisialisasi ApiService untuk mengambil data
        com.example.gohike2.ApiService apiService = new com.example.gohike2.ApiService();

        // Menjalankan pengambilan data di thread terpisah
        new Thread(() -> {
            List<com.example.gohike2.GunungResponse.NamaGunung> gunungList = apiService.fetchGunungByLocation(lokasi);
            runOnUiThread(() -> {
                if (gunungList != null && !gunungList.isEmpty()) {
                    gunungListAdapter = new GunungListAdapter(gunungList, this);
                    recyclerView.setAdapter(gunungListAdapter);
                } else {
                    Toast.makeText(ActivityListGunung.this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();

        // Set up the back button logic
        ImageView backButton = findViewById(R.id.back);
        backButton.setOnClickListener(view -> {
            // Go back to the previous activity
            onBackPressed();
        });
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(@Nullable Object o) {
        return false;
    }

    @NonNull
    @Override
    public Iterator<Gunung> iterator() {
        return null;
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] ts) {
        return null;
    }

    @Override
    public boolean add(Gunung gunung) {
        return false;
    }

    @Override
    public boolean remove(@Nullable Object o) {
        return false;
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends Gunung> collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, @NonNull Collection<? extends Gunung> collection) {
        return false;
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Gunung get(int i) {
        return null;
    }

    @Override
    public Gunung set(int i, Gunung gunung) {
        return null;
    }

    @Override
    public void add(int i, Gunung gunung) {

    }

    @Override
    public Gunung remove(int i) {
        return null;
    }

    @Override
    public int indexOf(@Nullable Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(@Nullable Object o) {
        return 0;
    }

    @NonNull
    @Override
    public ListIterator<Gunung> listIterator() {
        return null;
    }

    @NonNull
    @Override
    public ListIterator<Gunung> listIterator(int i) {
        return null;
    }

    @NonNull
    @Override
    public List<Gunung> subList(int i, int i1) {
        return Collections.emptyList();
    }
}
