package com.example.gohike2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OpenTripFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout untuk fragment
        View view = inflater.inflate(R.layout.opentrip, container, false);

        // Inisialisasi RecyclerView dan daftar data
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Siapkan data contoh (bisa diganti dengan data dari database atau API)
        List<data_class_opentrip.OpenTrip> openTrips = new ArrayList<>();

        List<data_class_gabungtim> semeruTeams = new ArrayList<>();
        semeruTeams.add(new data_class_gabungtim("Tim Ranu Pani", "5/10", "10-12 Desember 2024", "Rp 200.000", "Jalur Ranu Pani", R.drawable.opentripbackground));
        semeruTeams.add(new data_class_gabungtim("Tim Kalimati", "3/8", "15-17 Desember 2024", "Rp 250.000", "Jalur Kalimati", R.drawable.opentripbackground));
        semeruTeams.add(new data_class_gabungtim("Tim SAR", "7/8", "18-20 Desember 2024", "Rp 300.000", "Jalur Ayek-Ayek", R.drawable.opentripbackground));
        semeruTeams.add(new data_class_gabungtim("Tim Tanjakan Cinta", "6/10", "22-24 Desember 2024", "Rp 220.000", "Jalur Ranu Pani", R.drawable.opentripbackground));
        semeruTeams.add(new data_class_gabungtim("Tim Oro-Oro Ombo", "4/8", "28-30 Desember 2024", "Rp 270.000", "Jalur Ranu Pani", R.drawable.opentripbackground));

        List<data_class_gabungtim> rinjaniTeams = new ArrayList<>();
        rinjaniTeams.add(new data_class_gabungtim("Tim Segara Anak", "6/10", "10-13 Desember 2024", "Rp 500.000", "Jalur Sembalun", R.drawable.opentripbackground));
        rinjaniTeams.add(new data_class_gabungtim("Tim Plawangan", "4/8", "15-18 Desember 2024", "Rp 550.000", "Jalur Senaru", R.drawable.opentripbackground));
        rinjaniTeams.add(new data_class_gabungtim("Tim Summit Rinjani", "7/8", "20-23 Desember 2024", "Rp 600.000", "Jalur Torean", R.drawable.opentripbackground));

        // Contoh data lainnya
        openTrips.add(new data_class_opentrip.OpenTrip("Gunung Semeru", "2 Hari 1 Malam", "Tersedia", semeruTeams));
        openTrips.add(new data_class_opentrip.OpenTrip("Gunung Rinjani", "3 Hari 2 Malam", "Tidak Tersedia", rinjaniTeams));

        // Pasang adapter ke RecyclerView
        OpenTripAdapter openTripAdapter = new OpenTripAdapter(getContext(), openTrips);
        recyclerView.setAdapter(openTripAdapter);

        return view;
    }
}
