package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {

    private Integer userId;
    private String username;
    private String url;
    private String password;
    private String key;
    private Integer credentialId;

    public Credential(Integer userId, String username, String url, String password, String key, Integer credentialId) {
        this.userId = userId;
        this.username = username;
        this.url = url;
        this.password = password;
        this.key = key;
        this.credentialId = credentialId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String geturl() {
        return url;
    }

    public void seturl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getkey() {
        return key;
    }

    public void setkey(String key) {
        this.key = key;
    }

    public Integer getcredentialId() {
        return credentialId;
    }

    public void setcredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }
}
