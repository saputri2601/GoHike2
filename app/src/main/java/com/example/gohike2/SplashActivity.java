package com.example.gohike2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Menampilkan layout splash screen
        setContentView(R.layout.splash);

        // Menemukan tombol "Lanjut ke Sign Up"
        Button exploreButton = findViewById(R.id.exploreButton);

        exploreButton.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(SplashActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish(); // Pastikan untuk menutup SplashActivity
            } catch (Exception e) {
                e.printStackTrace(); // Cetak error ke Logcat
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
