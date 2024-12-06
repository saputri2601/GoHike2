package com.example.gohike2;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class gabungtim_adapter extends RecyclerView.Adapter<gabungtim_adapter.ViewHolder> {

    private final Context context;
    private final List<data_class_gabungtim> teamList;

    public gabungtim_adapter(Context context, List<data_class_gabungtim> teamList) {
        this.context = context;
        this.teamList = teamList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gabungitem_opentrip, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        data_class_gabungtim team = teamList.get(position);

        // Set data ke tampilan
        holder.teamName.setText(team.getTeamName());
        holder.slotAvailability.setText(team.getSlotAvailability());
        holder.tripDate.setText(team.getTripDate());
        holder.tripPrice.setText(team.getTripPrice());
        holder.trailName.setText(team.getTrailName());
        holder.mountainImage.setImageResource(team.getImageResId());

        // Set OnClickListener untuk tombol gabung tim
        holder.joinTeamButton.setOnClickListener(v -> showDialogBerhasil());
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    private void showDialogBerhasil() {
        // Membuat dialog dengan AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialogberhasil, null);
        builder.setView(dialogView);

        // Membuat dan menampilkan dialog
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        // Menampilkan pesan sukses di dialog
        TextView namaBerhasil = dialogView.findViewById(R.id.namaberhasil);
        namaBerhasil.setText("Selamat! Anda berhasil bergabung.");

        // Tombol OK untuk menutup dialog
        Button btnCloseDialog = dialogView.findViewById(R.id.btnOke);
        btnCloseDialog.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView teamName, slotAvailability, tripDate, tripPrice, trailName;
        ImageView mountainImage;
        Button joinTeamButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Inisialisasi view
            teamName = itemView.findViewById(R.id.teamName);
            slotAvailability = itemView.findViewById(R.id.slotAvailability);
            tripDate = itemView.findViewById(R.id.tripDate);
            tripPrice = itemView.findViewById(R.id.tripPrice);
            trailName = itemView.findViewById(R.id.trailName);
            mountainImage = itemView.findViewById(R.id.mountainImage);
            joinTeamButton = itemView.findViewById(R.id.joinTeamButtongabung);
        }
    }
}
