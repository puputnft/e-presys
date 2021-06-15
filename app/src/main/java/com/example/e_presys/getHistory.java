package com.example.e_presys;

public class getHistory {
    private String id;
    private String day;
    private String date;
    private String matakuliah;
    private String dosen;
    private String time;
    private String info;


    public getHistory (String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }

    public String getMatakuliah() {
        return matakuliah;
    }

    public String getDosen() {
        return dosen;
    }

    public String getTime() {
        return time;
    }

    public String getInfo() {
        return info;
    }
}
