package com.example.gohike2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProvinsiAdapter extends RecyclerView.Adapter<ProvinsiAdapter.ProvinsiViewHolder> {
    private final List<ProvinsiResponse> provinsiList;

    private OnItemClickListener listener;


    public ProvinsiAdapter(List<ProvinsiResponse> provinsiList) {
        this.provinsiList = provinsiList;
    }

    // Interface untuk klik item
    public interface OnItemClickListener {
        void onItemClick(ProvinsiResponse provinsi);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProvinsiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_provinsi, parent, false);
        return new ProvinsiViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProvinsiViewHolder holder, int position) {
        ProvinsiResponse provinsi = provinsiList.get(position);
        holder.namaTextView.setText(provinsi.getNama());
        holder.imageView.setImageResource(provinsi.getImageResource());

        // Menambahkan click listener untuk card view
        holder.cardView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(provinsiList.get(position));
            }

            // Menyimpan data provinsi yang dipilih
            Intent intent = new Intent(v.getContext(), ActivityListGunung.class);
            intent.putExtra("lokasi", provinsi.getNama()); // Kirim nama provinsi
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return provinsiList.size();
    }

    public static class ProvinsiViewHolder extends RecyclerView.ViewHolder {
        public TextView namaTextView;
        public ImageView imageView;
        public CardView cardView;

        public ProvinsiViewHolder(View itemView) {
            super(itemView);
            namaTextView = itemView.findViewById(R.id.tvLokasi);
            imageView = itemView.findViewById(R.id.imageProvinsi);
            cardView = itemView.findViewById(R.id.cvListProvinsi);
        }
    }
}
