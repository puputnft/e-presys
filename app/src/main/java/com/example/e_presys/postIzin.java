package com.example.e_presys;

public class postIzin {
    private String id;
    private String matakuliah;
    private String dosen;
    private String message;

    public postIzin(String id, String matakuliah, String dosen){
        this.id = id;
        this.matakuliah = matakuliah;
        this.dosen = dosen;
    }
    public String getMessage() {
        return message;
    }
}
