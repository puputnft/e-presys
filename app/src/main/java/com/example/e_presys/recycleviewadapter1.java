package com.example.e_presys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class recycleviewadapter1 extends RecyclerView.Adapter<recycleviewadapter1.ViewHolder> {
    ArrayList matakuliahlist,dosen1list,dosen2list,dosen3list,waktu_kuliahlist;
    String status;
    Context context;
    public String key = login.token;
    public static String matkul = "matkul" ;
    public static String mdosen1 = "dosen1" ;
    public static String mdosen2 = "dosen2";
    public static String mdosen3 = "dosen3";
    public static String kelas_ngajar = "kelas_ngajar";
    public SharedPreferences sharedPreferences ;

    public recycleviewadapter1(ArrayList matakuliahlist, ArrayList dosen1list, ArrayList dosen2list, ArrayList dosen3list, ArrayList waktu_kuliahlist, String status,Context context) {
        this.matakuliahlist = matakuliahlist;
        this.dosen1list = dosen1list;
        this.dosen2list = dosen2list;
        this.dosen3list = dosen3list;
        this.waktu_kuliahlist = waktu_kuliahlist;
        this.context = context;
        this.status = status;

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
        final String dosen1 = (String) dosen1list.get(position);
        final String dosen2 = (String)dosen2list.get(position);
        final String dosen3 = (String)dosen3list.get(position);
        String waktukuliah = (String) waktu_kuliahlist.get(position);
        Calendar cal = Calendar.getInstance();
        int tahun = cal.get(Calendar.YEAR);
        int bulan = cal.get(Calendar.MONTH)+1;
        int date = cal.get(Calendar.DAY_OF_MONTH);
        String tgl = date+"-"+bulan+"-"+tahun;
        String hari = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            OffsetDateTime offset = OffsetDateTime.now();
            hari = String.valueOf(offset.getDayOfWeek());
        }
        holder.showdaydate.setText(hari+","+tgl);
        holder.matakuliah.setText(matakuliah);
        holder.dosen.setText(dosen1+","+dosen2+","+dosen3);
        holder.waktu_kuliah.setText(waktukuliah);
        sharedPreferences = context.getSharedPreferences(key,context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        if(status.equals("1")){
            holder.kelas_dosen.setText("Kelas:");
        }

        holder.cekdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString(matkul,matakuliah );
                if(status.equals("0")){
                    editor.putString(mdosen1,dosen1);
                    editor.putString(mdosen2,dosen2);
                    editor.putString(mdosen3,dosen3);
                }
                else{
                    editor.putString(kelas_ngajar,dosen1);
                }
                editor.commit();
                Intent i = new Intent(context,fingerPrint.class);
                context.startActivity(i);
                ((OnlineMhs)context).finish();
            }
        });


    }

    @Override
    public int getItemCount() {
        return dosen1list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView matakuliah,dosen,waktu_kuliah,kelas_dosen,showdaydate;
        ImageButton cekdata;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            matakuliah = itemView.findViewById(R.id.txtmatakuliah);
            dosen = itemView.findViewById(R.id.txtdosen);
            waktu_kuliah = itemView.findViewById(R.id.txtwaktukuliah);
            cekdata = itemView.findViewById(R.id.buttoncekdata);
            kelas_dosen = itemView.findViewById(R.id.txtkelasdosenshowcekdata);
            showdaydate = itemView.findViewById(R.id.daydatelistshowcekdata);
        }
    }
}
