package com.example.gohike2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.gohike2.databinding.FragmentPeralatanBinding;
import java.util.ArrayList;

public class PeralatanFragment extends Fragment {

    FragmentPeralatanBinding binding;
    ListAdapterPeralatan listAdapter;
    ArrayList<ListDataPeralatan> dataArrayList = new ArrayList<>();
    private Activity rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Menggunakan view binding untuk fragment
        binding = FragmentPeralatanBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Inisialisasi tombol Back
        View backButton = view.findViewById(R.id.back); // Menggunakan 'view' parameter untuk referensi layout fragment
        if (backButton != null) {
            backButton.setOnClickListener(v -> {
                Log.d("PeralatanFragment", "Tombol Back ditekan");
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                requireActivity().finish(); // Menutup aktivitas fragment saat ini
            });
        } else {
            Log.e("PeralatanFragment", "backButton tidak ditemukan di layout.");
        }

        // Data untuk ListView
        int[] imageList = {
                R.drawable.tenda, R.drawable.carier, R.drawable.first,
                R.drawable.senter, R.drawable.nesting, R.drawable.propane
        };
        String[] nameList = {
                getString(R.string.tendaTitle), getString(R.string.tasCarrierTitle), getString(R.string.firstAidKitTitle),
                getString(R.string.senterTitle), getString(R.string.nestingTitle), getString(R.string.propaneStoveTitle)
        };
        // Tentukan pembawaan berdasarkan kategori
        String[] pembawaanList = new String[nameList.length];
        for (int i = 0; i < nameList.length; i++) {
            if (nameList[i].equals(getString(R.string.tendaTitle)) ||
                    nameList[i].equals(getString(R.string.propaneStoveTitle)) ||
                    nameList[i].equals(getString(R.string.firstAidKitTitle)) ||
                    nameList[i].equals(getString(R.string.nestingTitle))) {
                pembawaanList[i] = getString(R.string.peralatan_kelompok); // Peralatan Kelompok
            } else {
                pembawaanList[i] = getString(R.string.peralatan_pribadi); // Peralatan Pribadi
            }
        }
        String[] pengertianList = {
                getString(R.string.tendaDesc), getString(R.string.tasCarrierDesc),
                getString(R.string.firstAidKitDesc), getString(R.string.senterDesc),
                getString(R.string.nestingDesc), getString(R.string.propaneStoveDesc)
        };

        String[] tipsList = {
                getString(R.string.tipsTenda), getString(R.string.tipsTasCarrier),
                getString(R.string.tipsFirstAidKit), getString(R.string.tipsSenter),
                getString(R.string.tipsNesting), getString(R.string.tipsPropaneStove)
        };

// Mengisi data ke dalam ArrayList
        for (int i = 0; i < imageList.length; i++) {
            ListDataPeralatan listData = new ListDataPeralatan(
                    nameList[i], pembawaanList[i], pengertianList[i], tipsList[i], imageList[i]
            );
            dataArrayList.add(listData);
        }


        // Mengisi data ke dalam ArrayList
        for (int i = 0; i < imageList.length; i++) {
            ListDataPeralatan listData = new ListDataPeralatan(
                    nameList[i], pembawaanList[i], pengertianList[i], tipsList[i], imageList[i]
            );
            dataArrayList.add(listData);
        }

        // Set adapter untuk ListView
        listAdapter = new ListAdapterPeralatan(requireContext(), dataArrayList);
        binding.listview.setAdapter(listAdapter);

        // Event klik pada item
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Navigasi ke DetailedActivity dan kirim data melalui Intent
                Intent intent = new Intent(requireContext(), PeralatanDetail.class);
                intent.putExtra("name", nameList[position]);
                intent.putExtra("pembawaan", pembawaanList[position]);
                intent.putExtra("pengertian", pengertianList[position]);
                intent.putExtra("image", imageList[position]);
                intent.putExtra("tips", tipsList[position]);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Menghindari memory leak
    }
}
