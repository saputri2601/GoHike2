package com.example.gohike2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GabungTim_opentrip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gabungtim_opentrip); // Layout GabungTim

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Terima data dari intent
        List<data_class_gabungtim> teamList = getIntent().getParcelableArrayListExtra("teamList");

        // Pasang adapter untuk daftar tim
        gabungtim_adapter adapter = new gabungtim_adapter(this, teamList);
        recyclerView.setAdapter(adapter);
    }
}
