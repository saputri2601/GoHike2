package com.example.gohike2;

import static android.app.PendingIntent.getActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity; // Pastikan binding yang benar
import com.example.gohike2.databinding.FragmentPeralatanDetailBinding;

public class PeralatanDetail extends AppCompatActivity {

    FragmentPeralatanDetailBinding binding; // Gunakan binding yang benar
    private View back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Menggunakan View Binding untuk layout aktivitas
        binding = FragmentPeralatanDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Mendapatkan data dari Intent
        Intent intent = this.getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String pembawaan = intent.getStringExtra("pembawaan");
            String pengertian = intent.getStringExtra("pengertian");
            String tips = intent.getStringExtra("tips");
            int image = intent.getIntExtra("image", R.drawable.carier); // Default image jika tidak ada

            // Menampilkan data ke UI
            binding.detailName.setText(name);
            binding.detailPembawaan.setText(pembawaan);
            binding.detailPengertian.setText(pengertian);
            binding.detailTips.setText(tips);
            binding.detailImage.setImageResource(image);
        }

        // Tombol Back
        binding.back.setOnClickListener(v -> {
            Log.d("PeralatanDetail", "Tombol Back ditekan");
            onBackPressed(); // Kembali ke aktivitas sebelumnya
        });
    }
}
