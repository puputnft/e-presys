package com.example.e_presys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerviewadapter2 extends RecyclerView.Adapter<recyclerviewadapter2.ViewHolder> {
    ArrayList matakuliahlist,dosenlist,waktukehadiranlist,keteranganlist,daydatelist;
    Context context;

    public recyclerviewadapter2(ArrayList daydatelist, ArrayList matakuliahlist, ArrayList dosenlist, ArrayList waktukehadiranlist, ArrayList keteranganlist, Context context) {
        this.matakuliahlist = matakuliahlist;
        this.dosenlist = dosenlist;
        this.waktukehadiranlist = waktukehadiranlist;
        this.keteranganlist = keteranganlist;
        this.daydatelist = daydatelist;
        this.context = context;
    }

    @NonNull
    @Override
    public recyclerviewadapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_history,parent,false);
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerviewadapter2.ViewHolder holder, int position) {
        String matakuliah = (String) matakuliahlist.get(position);
        String dosen = (String) dosenlist.get(position);
        String waktu_kehadiran = (String) waktukehadiranlist.get(position);
        String keterangan = (String) keteranganlist.get(position);
        String daydate = (String) daydatelist.get(position);
        holder.matakuliah.setText(matakuliah);
        holder.dosen.setText(dosen);
        holder.waktukehadiran.setText(waktu_kehadiran);
        holder.keterangan.setText(keterangan);
        holder.daydate.setText(daydate);




    }

    @Override
    public int getItemCount() {

        return matakuliahlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView matakuliah,dosen,waktukehadiran,keterangan,daydate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            daydate = itemView.findViewById(R.id.daydatehis);
            matakuliah = itemView.findViewById(R.id.matakuliahhis);
            dosen = itemView.findViewById(R.id.dosenhis);
            waktukehadiran = itemView.findViewById(R.id.waktukehadiranhis);
            keterangan = itemView.findViewById(R.id.keteranganhis);



        }
    }
}
