package com.example.gohike2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gohike2.R;

import java.util.List;

public class GunungAdapter extends RecyclerView.Adapter<GunungAdapter.GunungViewHolder> {

    private final Context context;
    private final List<Gunung> gunungList;

    public GunungAdapter(Context context, List<Gunung> gunungList) {
        this.context = context;
        this.gunungList = gunungList;
    }

    @NonNull
    @Override
    public GunungViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gunung, parent, false);
        return new GunungViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GunungViewHolder holder, int position) {
        Gunung gunung = gunungList.get(position);
        holder.ivGunungImage.setImageResource(gunung.getImageResId());
        holder.tvGunungName.setText(gunung.getName());
    }

    @Override
    public int getItemCount() {
        return gunungList.size();
    }

    public static class GunungViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGunungImage;
        TextView tvGunungName;


        public GunungViewHolder(@NonNull View itemView) {
            super(itemView);
            ivGunungImage = itemView.findViewById(R.id.iv_gunung_image);
            tvGunungName = itemView.findViewById(R.id.tv_gunung_name); // Correct ID for TextView
            // Correct ID for ImageView
        }
    }
}