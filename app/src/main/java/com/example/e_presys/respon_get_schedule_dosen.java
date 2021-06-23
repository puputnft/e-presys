package com.example.e_presys;

public class respon_get_schedule_dosen {
    private String id;
    private String time_start;
    private String time_end;
    private String kelas;
    private String matakuliah;
    private String ruangan;

    public respon_get_schedule_dosen(String id){
        this.id = id;
    }

    public String getTime_start() {
        return time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public String getKelas() {
        return kelas;
    }

    public String getMatakuliah() {
        return matakuliah;
    }
}
