package com.example.e_presys;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recyclerviewadapter2 extends RecyclerView.Adapter<recyclerviewadapter2.ViewHolder> {
    @NonNull
    @Override
    public recyclerviewadapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerviewadapter2.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView matakuliah,dosen,waktukuliah,waktukehadiran,keterangan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            matakuliah = itemView.findViewById(R.id.matakuliahhis);
            dosen = itemView.findViewById(R.id.dosenhis);
            waktukuliah = itemView.findViewById(R.id.waktukuliahhis);
            waktukehadiran = itemView.findViewById(R.id.waktukehadiranhis);
            keterangan = itemView.findViewById(R.id.keteranganhis);

        }
    }
}
