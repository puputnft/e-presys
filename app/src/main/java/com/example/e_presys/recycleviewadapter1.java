package com.example.e_presys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class recycleviewadapter1 extends RecyclerView.Adapter<recycleviewadapter1.ViewHolder> {
    ArrayList matakuliahlist,dosenlist,waktu_kuliahlist;
    Context context;
    public String key = login.token;
    public static String matkul = "matkul" ;
    public SharedPreferences sharedPreferences ;

    public recycleviewadapter1(ArrayList matakuliahlist, ArrayList dosenlist, ArrayList waktu_kuliahlist, Context context) {
        this.matakuliahlist = matakuliahlist;
        this.dosenlist = dosenlist;
        this.waktu_kuliahlist = waktu_kuliahlist;
        this.context = context;

    }
    @NonNull
    @Override
    public recycleviewadapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_cek_data,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull recycleviewadapter1.ViewHolder holder, int position) {
        final String matakuliah = (String) matakuliahlist.get(position);
        String dosen = (String) dosenlist.get(position);
        String waktukuliah = (String) waktu_kuliahlist.get(position);
        holder.matakuliah.setText(matakuliah);
        holder.dosen.setText(dosen);
        holder.waktu_kuliah.setText(waktukuliah);
        sharedPreferences = context.getSharedPreferences(key,context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        holder.cekdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString(matkul,matakuliah );
                editor.commit();
                Intent i = new Intent(context,konfirmasi.class);
                context.startActivity(i);
                ((OnlineMhs)context).finish();
            }
        });


    }

    @Override
    public int getItemCount() {
        return dosenlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView matakuliah,dosen,waktu_kuliah;
        ImageButton cekdata;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            matakuliah = itemView.findViewById(R.id.txtmatakuliah);
            dosen = itemView.findViewById(R.id.txtdosen);
            waktu_kuliah = itemView.findViewById(R.id.txtwaktukuliah);
            cekdata = itemView.findViewById(R.id.buttoncekdata);

        }
    }
}
