package com.example.e_presys;

public class postLokasi {
    private String id;
    private String lat;
    private String lng;
    private String ruangan;
    private String message;

    public postLokasi(String id,String lat,String lng,String ruangan){
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.ruangan = ruangan;

    }

    public String getMessage() {
        return message;
    }
}
