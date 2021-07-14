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

import static com.example.e_presys.recycleviewadapter1.kelas_ngajar;

public class recyclerviewadapter3 extends RecyclerView.Adapter<recyclerviewadapter3.ViewHolder> {
    ArrayList offmatakuliahlist,offdosen1list,offdosen2list,offdosen3list,offwaktukuliahlist,offruanganlist;
    String status;
    Context context;
    public String key = login.token;
    public static String offmatkul = "matkul";
    public static String offdosen1 = "dosen1";
    public static String offdosen2 = "dosen2";
    public static String offdosen3 = "dosen3";
    public static String offruangan = "ruangan";
    public SharedPreferences sharedPreferences;

    public recyclerviewadapter3(ArrayList offmatakuliahlist, ArrayList offdosen1list, ArrayList offdosen2list, ArrayList offdosen3list,ArrayList offwaktukuliahlist ,ArrayList offruanganlist, String status, Context context) {
        this.offmatakuliahlist = offmatakuliahlist;
        this.offdosen1list = offdosen1list;
        this.offdosen2list = offdosen2list;
        this.offdosen3list = offdosen3list;
        this.offwaktukuliahlist = offwaktukuliahlist;
        this.offruanganlist = offruanganlist;
        this.context = context;
        this.status = status;

    }
    @NonNull
    @Override
    public recyclerviewadapter3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_offline,parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull recyclerviewadapter3.ViewHolder holder, int position) {
        final String matakuliahoff = (String) offmatakuliahlist.get(position);
        final String dosen1off = (String) offdosen1list.get(position);
        final String dosen2off = (String) offdosen2list.get(position);
        final String dosen3off = (String) offdosen3list.get(position);
        String waktukuliahoff = (String) offwaktukuliahlist.get(position);
        final String ruanganoff = (String) offruanganlist.get(position);
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
        holder.daydate.setText(hari+","+tgl);
        holder.matakuliahoff.setText(matakuliahoff);
        holder.dosenoff.setText(dosen1off+","+dosen2off+","+dosen3off);
        holder.waktukuliahoff.setText(waktukuliahoff);
        holder.ruanganoff.setText(ruanganoff);
        sharedPreferences = context.getSharedPreferences(key, context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        if (status.equals("1")){
                holder.kelas_dosenoff.setText("Kelas:");
        }

        holder.cekdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString(offmatkul, matakuliahoff);
                editor.putString(offruangan,ruanganoff);
                if (status.equals("0")) {
                    editor.putString(offdosen1, dosen1off);
                    editor.putString(offdosen2, dosen2off);
                    editor.putString(offdosen3, dosen3off);
                } else {
                    editor.putString(kelas_ngajar, dosen1off);
                }
                editor.commit();
                if(MainActivity.keterangan_kode==3){
                    Intent i = new Intent(context, konfirmasi.class);
                    context.startActivity(i);
                    ((OnlineMhs) context).finish();
                }
                else{
                    Intent i = new Intent(context, Lokasi.class);
                    context.startActivity(i);
                    ((OnlineMhs) context).finish();
                }

            }
        });

    }
    @Override
    public int getItemCount() {
        return offdosen1list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView matakuliahoff,dosenoff,waktukuliahoff,ruanganoff,kelas_dosenoff,daydate;
        ImageButton cekdata;
        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            matakuliahoff = itemView.findViewById(R.id.offmatakuliah);
            dosenoff = itemView.findViewById(R.id.offdosen);
            waktukuliahoff = itemView.findViewById(R.id.offwaktukuliah);
            ruanganoff = itemView.findViewById(R.id.offruangan);
            kelas_dosenoff = itemView.findViewById(R.id.txtkelasdosenshowoffline);
            cekdata = itemView.findViewById(R.id.offcekdatashowofflline);
            daydate = itemView.findViewById(R.id.txtdaydateshowlistoffline);
        }
    }
}