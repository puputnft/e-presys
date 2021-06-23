package com.example.e_presys;

public class respon_get_schedule {
    private String id;
    private String time_start;
    private String time_end;
    private String matakuliah;
    private String dosen1;
    private String dosen2;
    private String dosen3;
    private String ruangan;
    public respon_get_schedule(String id){
        this.id = id;

    }

    public String getMatakuliah() {
        return matakuliah;
    }

    public String getDosen1() {
        return dosen1;
    }

    public String getTime_start() {
        return time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public String getDosen2() {
        return dosen2;
    }

    public String getDosen3() {
        return dosen3;
    }

    public String getRuangan() {
        return ruangan;
    }
}
