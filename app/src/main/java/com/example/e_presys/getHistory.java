package com.example.e_presys;

public class getHistory {
    private String id;
    private String day;
    private String date;
    private String matakuliah;
    private String dosen1;
    private String dosen2;
    private String dosen3;
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

    public String getDosen1() {
        return dosen1;
    }

    public String getDosen2() {
        return dosen2;
    }

    public String getDosen3() {
        return dosen3;
    }

    public String getTime() {
        return time;
    }

    public String getInfo() {
        return info;
    }
}
