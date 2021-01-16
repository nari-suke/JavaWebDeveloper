package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {

    private Integer userId;
    private Integer credentialId;
    private String username;
    private String password;
    private String key;
    private String url;

    public Credential(Integer userId, Integer credentialId, String username, String password, String key, String url) {
        this.userId = userId;
        this.credentialId = credentialId;
        this.username = username;
        this.password = password;
        this.key = key;
        this.url = url;
    }

    public Credential() { }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
