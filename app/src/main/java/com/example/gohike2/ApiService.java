package com.example.gohike2;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.example.gohike2.GunungResponse;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.JsonAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** @noinspection ALL*/
public class ApiService {

    private final OkHttpClient client;

    public ApiService() {
        client = new OkHttpClient();
    }

    public List<GunungResponse.Gunung> fetchGunungData() {
        Request request = new Request.Builder()
                .url("https://raw.githubusercontent.com/AzharRivaldi/Info-Pendakian-Gunung/main/app/src/main/assets/nama_gunung.json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                Moshi moshi = new Moshi.Builder().build();
                JsonAdapter<GunungResponse> jsonAdapter = moshi.adapter(GunungResponse.class);

                GunungResponse gunungResponse = jsonAdapter.fromJson(response.body().string());
                if (gunungResponse != null) {
                    return gunungResponse.getGunung(); // Kembalikan List<GunungResponse.Gunung>
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Method untuk filter berdasarkan lokasi
    public List<GunungResponse.NamaGunung> fetchGunungByLocation(String lokasi) {
        List<GunungResponse.NamaGunung> filteredGunungList = new ArrayList<>();
        List<GunungResponse.Gunung> allGunung = fetchGunungData(); // Mengambil data Gunung

        if (allGunung != null) {
            for (GunungResponse.Gunung gunung : allGunung) {
                if (gunung.getNamaGunung() != null) { // Periksa jika daftar NamaGunung tidak null
                    for (GunungResponse.NamaGunung namaGunung : gunung.getNamaGunung()) {
                        // Filter berdasarkan lokasi
                        if (namaGunung.getLokasi() != null && namaGunung.getLokasi().equals(lokasi)) {
                            filteredGunungList.add(namaGunung);
                        }
                    }
                }
            }
        }
        return filteredGunungList;
    }

    // Metode untuk mendapatkan detail gunung berdasarkan nama
    public GunungResponse.NamaGunung getGunungByNama(String namaGunung) {
        List<GunungResponse.Gunung> allGunung = fetchGunungData();
        if (allGunung != null) {
            for (GunungResponse.Gunung gunung : allGunung) {
                if (gunung.getNamaGunung() != null) {
                    for (GunungResponse.NamaGunung nama : gunung.getNamaGunung()) {
                        // Membandingkan nama gunung
                        if (nama.getNama().equals(namaGunung)) {
                            return nama; // Mengembalikan detail gunung yang sesuai
                        }
                    }
                }
            }
        }
        return null; // Jika tidak ditemukan
    }

}
