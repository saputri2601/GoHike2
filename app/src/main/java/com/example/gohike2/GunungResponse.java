package com.example.gohike2;

import java.io.Serializable;
import java.util.List;

public class GunungResponse {

    private List<Gunung> gunung;

    public List<Gunung> getGunung() {
        return gunung;
    }

    public void setGunung(List<Gunung> gunung) {
        this.gunung = gunung;
    }

    public static class Gunung {
        private String lokasi;
        private List<NamaGunung> nama_gunung;

        public String getLokasi() {
            return lokasi;
        }

        public void setLokasi(String lokasi) {
            this.lokasi = lokasi;
        }

        public List<NamaGunung> getNamaGunung() {
            return nama_gunung;
        }

        public void setNamaGunung(List<NamaGunung> nama_gunung) {
            this.nama_gunung = nama_gunung;
        }
    }

    public static class NamaGunung implements Serializable {
        private String nama;
        private String image_gunung;
        private String lokasi;
        private double lat;
        private double lon;
        private String deskripsi;
        private String jalur_pendakian;
        private String info_gunung;

        // Getter dan Setter untuk NamaGunung
        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getImageGunung() {
            return image_gunung;
        }

        public void setImageGunung(String image_gunung) {
            this.image_gunung = image_gunung;
        }

        public String getLokasi() {
            return lokasi;
        }

        public void setLokasi(String lokasi) {
            this.lokasi = lokasi;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public String getDeskripsi() {
            return deskripsi;
        }

        public void setDeskripsi(String deskripsi) {
            this.deskripsi = deskripsi;
        }

        public String getJalurPendakian() {
            return jalur_pendakian;
        }

        public void setJalurPendakian(String jalur_pendakian) {
            this.jalur_pendakian = jalur_pendakian;
        }

        public String getInfoGunung() {
            return info_gunung;
        }

        public void setInfoGunung(String info_gunung) {
            this.info_gunung = info_gunung;
        }
    }
}
