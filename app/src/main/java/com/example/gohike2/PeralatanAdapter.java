package com.example.gohike2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PeralatanAdapter extends RecyclerView.Adapter<PeralatanAdapter.PeralatanViewHolder> {

    private List<Peralatan> peralatanList;
    private Context context;

    // Constructor untuk menerima context dan data
    public PeralatanAdapter(List<Peralatan> peralatanList) {
        this.context = context;
        this.peralatanList = peralatanList;
    }

    @NonNull
    @Override
    public PeralatanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_peralatan, parent, false);
        return new PeralatanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeralatanViewHolder holder, int position) {
        Peralatan peralatan = peralatanList.get(position);

        // Set image untuk item saat ini
        holder.imageView.setImageResource(peralatan.getImageResId());

        // Set OnClickListener untuk gambar untuk membuka halaman detail
        holder.imageView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PeralatanDetail.class);

            // Kirim data ke activity PeralatanDetail
            intent.putExtra("name", "Nama Peralatan ke-" + (position + 1)); // Contoh nama
            intent.putExtra("pembawaan", "Pembawaan peralatan ke-" + (position + 1)); // Contoh pembawaan
            intent.putExtra("pengertian", "Pengertian peralatan ke-" + (position + 1)); // Contoh pengertian
            intent.putExtra("tips", "Tips menggunakan peralatan ke-" + (position + 1)); // Contoh tips
            intent.putExtra("image", peralatan.getImageResId()); // Gambar peralatan

            context.startActivity(intent); // Mulai activity detail
        });
    }

    @Override
    public int getItemCount() {
        return peralatanList.size();
    }

    public static class PeralatanViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public PeralatanViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_peralatan); // Pastikan ID sesuai dengan item layout
        }
    }
}
