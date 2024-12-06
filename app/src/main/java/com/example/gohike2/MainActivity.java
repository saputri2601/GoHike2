package com.example.gohike2;

import static com.example.gohike2.R.id.nav_OpenTrip;
import static com.example.gohike2.R.id.nav_gunung;
import static com.example.gohike2.R.id.nav_home;
import static com.example.gohike2.R.id.nav_peralatan;

import com.example.gohike2.PeralatanFragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gohike2.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private View menuIcon;
    private View profileIcon;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi DrawerLayout dan NavigationView
        drawerLayout = findViewById(R.id.main);

        // Inisialisasi ImageView untuk ikon profil
        profileIcon = findViewById(R.id.profile);

        // Menambahkan listener untuk klik pada ikon profil
        profileIcon.setOnClickListener(v -> {
            // Menavigasi ke ProfileActivity ketika ikon profil diklik
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

//        menuIcon = findViewById(R.id.menu_icon);
//
//        // Menambahkan listener untuk menu icon
//        menuIcon.setOnClickListener(v -> {
//            // Membuka Navigation Drawer
//            drawerLayout.openDrawer(GravityCompat.START);
//        });

        // Set Listener untuk NavigationView
//        setupNavigationMenu();

        // Initialize RecyclerView for Peralatan
        RecyclerView rcvPeralatan = findViewById(R.id.rcvperalatan);
        rcvPeralatan.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Prepare data for Peralatan
        List<Peralatan> peralatanList = new ArrayList<>();
        peralatanList.add(new Peralatan(R.drawable.tenda));
        peralatanList.add(new Peralatan(R.drawable.senter));
        peralatanList.add(new Peralatan(R.drawable.kit));
        peralatanList.add(new Peralatan(R.drawable.propane));
        peralatanList.add(new Peralatan(R.drawable.nesting));
        peralatanList.add(new Peralatan(R.drawable.carier));

        // Set up Adapter for Peralatan
        PeralatanAdapter peralatanAdapter = new PeralatanAdapter(peralatanList);
        rcvPeralatan.setAdapter(peralatanAdapter);

        RecyclerView rcvGunung = findViewById(R.id.rcvgunung);
        rcvGunung.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Gunung> gunungList = new ArrayList<>();
        gunungList.add(new Gunung("Gunung Merbabu", R.drawable.merbabu));
        gunungList.add(new Gunung("Gunung Rinjani", R.drawable.rinjani));
        gunungList.add(new Gunung("Gunung Semeru", R.drawable.semeru));
        gunungList.add(new Gunung("Gunung Bromo", R.drawable.bromo));
        gunungList.add(new Gunung("Gunung Slamet", R.drawable.slamet));

        GunungAdapter gunungAdapter = new GunungAdapter(this, gunungList);
        rcvGunung.setAdapter(gunungAdapter);

        // Inisialisasi BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);

    // Panggil metode initListener untuk mengaktifkan listener navigasi
        initListener();


    }

    private void initListener() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            // Menangani setiap item menu secara langsung
            if (item.getItemId() == R.id.nav_home) {
                selectedFragment = new Fragment(); // Ganti dengan fragment yang sesuai
            } else if (item.getItemId() == R.id.nav_gunung) {
                selectedFragment = new GunungFragment(); // Ganti dengan fragment yang sesuai
            } else if (item.getItemId() == R.id.nav_peralatan) {
                selectedFragment = new PeralatanFragment(); // Ganti dengan fragment yang sesuai
            } else if (item.getItemId() == R.id.nav_OpenTrip) {
                selectedFragment = new OpenTripFragment(); // Ganti dengan fragment yang sesuai
            }

            // Jika fragment dipilih, ganti fragment di FrameLayout
            if (selectedFragment != null) {
                loadFragment(selectedFragment);
            }
            return true;
        });
    }


    // Helper method to load fragments
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void handleLogout() {
        Toast.makeText(MainActivity.this, "You have been logged out.", Toast.LENGTH_SHORT).show();
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear semua aktivitas
        startActivity(loginIntent);
        finish();
    }

}
