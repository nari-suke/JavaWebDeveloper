package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private Integer userId;

    public Note(Integer noteId, String noteTitle, String noteDescription, Integer userId) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userId = userId;

    }

    public Integer getnoteId() {
        return noteId;
    }

    public void setnoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getnoteTitle() {
        return noteTitle;
    }

    public void setnoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getnoteDescription() {
        return noteDescription;
    }

    public void setnoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public String getuserId() {
        return userId;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }
}
