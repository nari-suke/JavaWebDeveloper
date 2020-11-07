package com.udacity.jwdnd.course1.cloudstorage.model;


import java.sql.Blob;

public class File {
    private Integer userId;
    private String filename;
    private String contenttype;
    private String filesize;
    private Blob filedata;
    private Integer fileId;

    public File (Integer userId, String filename, String contenttype, String filesize, Blob filedata, Integer fileId) {
        this.userId = userId;
        this.filename = filename;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.filedata = filedata;
        this.fileId = fileId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getfilename() {
        return filename;
    }

    public void setfilename(String filename) {
        this.filename = filename;
    }

    public String getcontenttype() {
        return contenttype;
    }

    public void setcontenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getfilesize() {
        return filesize;
    }

    public void setfilesize(String filesize) {
        this.filesize = filesize;
    }

    public Blob getfiledata() {
        return filedata;
    }

    public void setfiledata(Blob filedata) {
        this.filedata = filedata;
    }

    public Integer getfileId() {
        return fileId;
    }

    public void setfileId(Integer fileId) {
        this.fileId = fileId;
    }

}
