package com.example.e_presys;

public class postlogin {
    private String username;
    private String password;
    private String message;
    private String status;

    public postlogin(String username,String password){
        this.username = username;
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
