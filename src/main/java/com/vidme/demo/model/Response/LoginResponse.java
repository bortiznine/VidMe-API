package com.vidme.demo.model.Response;


public class LoginResponse {

    public String getJWT() {
        return JWT;
    }

    public void setJWT(String JWT) {
        this.JWT = JWT;
    }

    private String JWT;

    public LoginResponse(String JWT) {
        this.JWT = JWT;
    }
}
