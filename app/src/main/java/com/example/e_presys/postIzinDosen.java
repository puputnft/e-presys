package com.example.e_presys;

public class postIzinDosen {
    private String id;
    private String matakuliah;
    private String kelas;
    private String izin;

    public postIzinDosen(String id,String matakuliah,String kelas){
        this.id = id;
        this.matakuliah = matakuliah;
        this.kelas = kelas;
    }

    public String getIzin() {
        return izin;
    }
}
