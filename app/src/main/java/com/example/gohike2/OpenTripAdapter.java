package com.example.gohike2;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OpenTripAdapter extends RecyclerView.Adapter<OpenTripAdapter.ViewHolder> {

    private final Context context;
    private final List<data_class_opentrip.OpenTrip> openTrips;

    // Constructor
    public OpenTripAdapter(Context context, List<data_class_opentrip.OpenTrip> openTrips) {
        this.context = context;
        this.openTrips = openTrips;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate item layout for RecyclerView
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_opentrip, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        data_class_opentrip.OpenTrip openTrip = openTrips.get(position);

        // Set data to UI components
        holder.mountainTitle.setText(openTrip.getTitle());
        holder.mountainDetails.setText(openTrip.getDuration());
        holder.mountainStatus.setText(openTrip.getStatus());

        if ("Tersedia".equalsIgnoreCase(openTrip.getStatus())) {
            holder.joinTeamButton.setEnabled(true);
            holder.joinTeamButton.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.brown));
        } else {
            holder.joinTeamButton.setEnabled(false);
            holder.joinTeamButton.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.lavender_brown));
        }


        holder.joinTeamButton.setOnClickListener(v -> {
            // Pastikan status "Tersedia" agar hanya tombol aktif yang bisa diklik
            if ("Tersedia".equalsIgnoreCase(openTrip.getStatus())) {
                Intent intent = new Intent(context, GabungTim_opentrip.class);
                intent.putParcelableArrayListExtra("teamList", (ArrayList<? extends Parcelable>) new ArrayList<>(openTrip.getTeams()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return openTrips.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mountainTitle, mountainDetails, mountainStatus;
        Button joinTeamButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mountainTitle = itemView.findViewById(R.id.mountainTitle);
            mountainDetails = itemView.findViewById(R.id.mountainDetails);
            mountainStatus = itemView.findViewById(R.id.mountainStatus);
            joinTeamButton = itemView.findViewById(R.id.joinTeamButton);
        }
    }
}
