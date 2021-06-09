package com.example.e_presys;

public class respon_get_schedule {
    private String id;
    private String jamstart;
    private String menitstart;
    private String jamend;
    private String menitend;
    private String matakuliah;
    private String dosen;
    public respon_get_schedule(String id){
        this.id = id;

    }

    public String getJamstart() {
        return jamstart;
    }
    public String getMenitstart() {
        return menitstart;

    }
    public String getJamend() {
        return jamend;
    }

    public String getMenitend() {
        return menitend;
    }

    public String getMatakuliah() {
        return matakuliah;
    }

    public String getDosen() {
        return dosen;
    }
}
