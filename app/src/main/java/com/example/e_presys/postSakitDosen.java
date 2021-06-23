package com.example.e_presys;

public class postSakitDosen {
    private String id;
    private String matakuliah;
    private String kelas;
    private String sakit;

    public postSakitDosen(String id,String matakuliah,String kelas){
        this.id = id;
        this.matakuliah = matakuliah;
        this.kelas = kelas;
    }

    public String getSakit() {
        return sakit;
    }
}
