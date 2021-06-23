package com.example.e_presys;

public class getHistoryDosen {
    private String id;
    private String kelas;
    private String matakuliah;
    private String day;
    private String date;
    private String time;
    private String info;

    public getHistoryDosen(String id){
        this.id = id;
    }

    public String getKelas() {
        return kelas;
    }

    public String getMatakuliah() {
        return matakuliah;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getInfo() {
        return info;
    }
}
