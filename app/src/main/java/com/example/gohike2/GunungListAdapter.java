package com.example.gohike2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class GunungListAdapter extends RecyclerView.Adapter<GunungListAdapter.GunungViewHolder> {

    private final List<com.example.gohike2.GunungResponse.NamaGunung> gunungList;
    private final Context context;

    // Constructor
    public GunungListAdapter(List<com.example.gohike2.GunungResponse.NamaGunung> gunungList, Context context) {
        this.gunungList = gunungList;
        this.context = context;
    }

    @NonNull
    @Override
    public GunungViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate layout item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_gunung, parent, false);
        return new GunungViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GunungViewHolder holder, int position) {
        com.example.gohike2.GunungResponse.NamaGunung gunung = gunungList.get(position);

        // Set data to the views
        holder.namaGunungTextView.setText(gunung.getNama());
        Glide.with(holder.itemView.getContext())
                .load(gunung.getImageGunung())
                .into(holder.imageGunungImageView);

        // Menambahkan click listener untuk card view
        holder.cardView.setOnClickListener(v -> {
            // Log untuk memastikan data tidak null
            if (gunung.getNama() != null && gunung.getLokasi() != null) {
                Intent intent = new Intent(v.getContext(), ActivityDetailGunung.class);
                intent.putExtra("NAMA_GUNUNG", gunung.getNama());
                intent.putExtra("LOKASI_GUNUNG", gunung.getLokasi());
                intent.putExtra("DESKRIPSI_GUNUNG", gunung.getDeskripsi());
                intent.putExtra("JALUR_PENDAKIAN", gunung.getJalurPendakian());
                intent.putExtra("INFO_GUNUNG", gunung.getInfoGunung());
                intent.putExtra("IMAGE_GUNUNG", gunung.getImageGunung());
                v.getContext().startActivity(intent);
            } else {
                Toast.makeText(context, "Data tidak lengkap", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return gunungList.size();
    }

    public static class GunungViewHolder extends RecyclerView.ViewHolder {

        TextView namaGunungTextView;
        ImageView imageGunungImageView;
        CardView cardView;

        public GunungViewHolder(View itemView) {
            super(itemView);
            namaGunungTextView = itemView.findViewById(R.id.tvNamaGunung);
            imageGunungImageView = itemView.findViewById(R.id.imageGunung);
            cardView = itemView.findViewById(R.id.cvListGunung);
        }
    }
}

