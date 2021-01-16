package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    public ModelAndView postNote(Authentication auth, Model model, @ModelAttribute Note note){

        User user = this.userService.getUser(auth.getName());
        Integer userId = user.getUserId();
        ModelAndView mav = new ModelAndView();
        note.setUserId(userId);

        try {
            if (note.getNoteId() == null) {
                noteService.createNote(note);
            } else {
                noteService.editNote(note);
            }
            mav.addObject("success", true);
            mav.addObject("message", "New note added.");
        } catch (Exception e) {
            mav.addObject("error", true);
            mav.addObject("message", "System error." + e.getMessage());
        }

        mav.setViewName("result");
        return mav;

    }

    @PostMapping("/delete")
    public ModelAndView deleteNote(Authentication auth, Model model, @ModelAttribute Note note){
        User user = this.userService.getUser(auth.getName());
        Integer userId = user.getUserId();
        Integer noteId = note.getNoteId();
        ModelAndView mav = new ModelAndView();

        try {
            noteService.deleteNote(note, noteId);
            mav.addObject("success", true);
            mav.addObject("message", "Note deleted.");
        } catch (Exception e) {
            mav.addObject("error", true);
            mav.addObject("message","SystemError" + e.getMessage());
        }

        mav.setViewName("result");
        return mav;

    }

}
