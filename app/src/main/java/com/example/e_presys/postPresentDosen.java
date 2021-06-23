package com.example.e_presys;

public class postPresentDosen {
    private String id;
    private String matakuliah;
    private String kelas;
    private String link;

    public postPresentDosen(String id,String matakuliah,String kelas){
        this.id = id;
        this.matakuliah = matakuliah;
        this.kelas = kelas;
    }

    public String getLink() {
        return link;
    }
}
