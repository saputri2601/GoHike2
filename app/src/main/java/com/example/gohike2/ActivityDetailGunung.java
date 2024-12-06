package com.example.gohike2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import static org.osmdroid.config.Configuration.*;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ActivityDetailGunung extends AppCompatActivity {

    private ExecutorService executorService;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gunung);

        // Initialize ExecutorService for background tasks
        executorService = Executors.newSingleThreadExecutor();

        // Get the mountain name from the intent
        String namaGunung = getIntent().getStringExtra("NAMA_GUNUNG");

        // Run the background task to fetch the mountain details
        fetchGunungDetail(namaGunung);

        Configuration.getInstance().setUserAgentValue(getPackageName());

        // Set up the back button logic
        ImageView backButton = findViewById(R.id.back);
        backButton.setOnClickListener(view -> {
            // Go back to the previous activity
            onBackPressed();
        });
    }

    private void fetchGunungDetail(String namaGunung) {
        executorService.execute(() -> {
            // Initialize ApiService
            com.example.gohike2.ApiService apiService = new com.example.gohike2.ApiService();

            // Get mountain details in the background thread
            com.example.gohike2.GunungResponse.NamaGunung gunungDetail = apiService.getGunungByNama(namaGunung);

            // Update UI after background task completion
            runOnUiThread(() -> {
                if (gunungDetail != null) {
                    // Display data in UI
                    TextView namaTextView = findViewById(R.id.detailGunungName);
                    TextView lokasiTextView = findViewById(R.id.detailLokasi);
                    TextView deskripsiTextView = findViewById(R.id.detailDeskripsi);
                    TextView jalurPendakianTextView = findViewById(R.id.tvJalurGunung);
                    TextView infoGunungTextView = findViewById(R.id.tvInfoGunung);
                    ImageView imageView = findViewById(R.id.detailImage);

                    // Set data to UI elements
                    namaTextView.setText(gunungDetail.getNama());
                    lokasiTextView.setText(gunungDetail.getLokasi());
                    deskripsiTextView.setText(gunungDetail.getDeskripsi());
                    jalurPendakianTextView.setText(gunungDetail.getJalurPendakian());
                    infoGunungTextView.setText(gunungDetail.getInfoGunung());

                    // Load image with Glide
                    String imageUrl = gunungDetail.getImageGunung();

                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        Glide.with(ActivityDetailGunung.this)
                                .load(imageUrl)
                                .into(imageView);
                    }

                    getInstance().setUserAgentValue("GoHike2/1.0 (Android)");

                    // Initialize the MapView
                    mapView = findViewById(R.id.mapview);  // Assuming you have a MapView in your layout
                    mapView.setTileSource(TileSourceFactory.MAPNIK);  // You can choose different tile sources like OpenStreetMap, Mapnik, etc.
                    mapView.setBuiltInZoomControls(true);  // Enable zoom controls
                    mapView.setMultiTouchControls(true);  // Enable multi-touch (pinch-to-zoom)

                    // Get latitude and longitude from the gunungDetail object
                    double dblLatitude = gunungDetail.getLat();  // Ensure latitude is provided in the GunungResponse
                    double dblLongitude = gunungDetail.getLon();  // Ensure longitude is provided in the GunungResponse

                    // Set up the map controller and zoom to the location
                    IMapController mapController = mapView.getController();
                    mapController.setZoom(12);  // Set initial zoom level
                    mapController.setCenter(new GeoPoint(dblLatitude, dblLongitude));  // Set the map center to the mountain location

                    // Add a marker on the map for the mountain location
                    Marker marker = new Marker(mapView);
                    marker.setPosition(new GeoPoint(dblLatitude, dblLongitude));
                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                    marker.setTitle(gunungDetail.getNama());  // Set marker title
                    mapView.getOverlays().add(marker);  // Add the marker to the map
                } else {
                    // If mountain data is not found
                    Toast.makeText(ActivityDetailGunung.this, "Detail gunung tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Clean up the executor service
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }


}
