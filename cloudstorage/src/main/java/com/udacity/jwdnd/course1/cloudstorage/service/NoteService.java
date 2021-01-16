package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int createNote(Note note){
        return this.noteMapper.insert(note);
    }

    public List<Note> getAllNotes() {
        return this.noteMapper.getAllNotes();
    }

    public List<Note> getNoteByNoteId(Integer noteId){
        return this.noteMapper.getNoteByNoteId(noteId);
    }

    public List<Note> getNotesByUserId(Integer userId){
        return this.noteMapper.getNotesByUserId(userId);
    }

    public void deleteNote(Note note, Integer noteId) {
        this.noteMapper.delete(noteId);
    }

    public void editNote(Note note){
        this.noteMapper.update(note);
    }

}
