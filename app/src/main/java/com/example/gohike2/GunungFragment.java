package com.example.gohike2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GunungFragment extends Fragment {

    private Object backButton;
    private Activity view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_gunungfragment, container, false);

        // Inisialisasi tombol Back
        View backButton = rootView.findViewById(R.id.back);
        if (backButton != null) {
            backButton.setOnClickListener(v -> {
                Log.d("GunungFragment", "Tombol Back ditekan");
                // Navigasi kembali ke NavigationActivity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                requireActivity().finish(); // Menutup GunungFragment
            });
        } else {
            Log.e("GunungFragment", "backButton tidak ditemukan di layout.");
        }

        // Initialize RecyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.rvLokasi);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Mengganti data yang sebelumnya menggunakan API dengan data statis
        List<com.example.gohike2.ProvinsiResponse> provinsiList = getProvinsiList();

        if (!provinsiList.isEmpty()) {
            com.example.gohike2.ProvinsiAdapter provinsiAdapter = new com.example.gohike2.ProvinsiAdapter(provinsiList);
            recyclerView.setAdapter(provinsiAdapter);

            provinsiAdapter.setOnItemClickListener((provinsi) -> {
                Intent intent = new Intent(getContext(), com.example.gohike2.ActivityListGunung.class);
                intent.putExtra("lokasi", provinsi.getNama()); // Mengirimkan nama provinsi yang dipilih
                startActivity(intent);
            });
        } else {
            Toast.makeText(getContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
        }

        return rootView;
    }


    // Data statis yang akan ditampilkan
    private List<ProvinsiResponse> getProvinsiList() {
        List<com.example.gohike2.ProvinsiResponse> provinsiList = new ArrayList<>();
        provinsiList.add(new com.example.gohike2.ProvinsiResponse("Jawa Barat", R.drawable.slamet));
        provinsiList.add(new com.example.gohike2.ProvinsiResponse("Jawa Tengah", R.drawable.semeru));
        provinsiList.add(new com.example.gohike2.ProvinsiResponse("Jawa Timur", R.drawable.rinjani));
        provinsiList.add(new com.example.gohike2.ProvinsiResponse("Luar Jawa", R.drawable.merbabu));
        // Tambahkan lebih banyak provinsi sesuai kebutuhan
        return provinsiList;
    }
}

