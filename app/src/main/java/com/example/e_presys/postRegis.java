package com.example.e_presys;

public class postRegis {
    private String nim;
    private String username;
    private String jurusan;
    private String prodi;
    private String kelas;
    private String email;
    private String pass;
    private String status;
    private String message;

    public postRegis(String nim,String username,String jurusan,String prodi,String kelas,String email,String pass,String status){
        this.nim =nim;
        this.username =username;
        this.jurusan =jurusan;
        this.prodi =prodi;
        this.kelas =kelas;
        this.email =email;
        this.pass =pass;
        this.status = status;

    }

    public String getMessage() {
        return message;
    }
}
