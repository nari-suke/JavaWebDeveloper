package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;

    public HomeController(UserService userService, FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping()
    public ModelAndView loginView(Authentication auth, Model model) {
        User user = this.userService.getUser(auth.getName());
        Integer userId = user.getUserId();
        ModelAndView mav = new ModelAndView();
        mav.addObject("files", this.fileService.getFilesByUserId(userId));
        mav.addObject("notes", this.noteService.getNotesByUserId(userId));
        mav.addObject("credentials", this.credentialService.getCredentialByUserId(userId));
        mav.addObject("credentialForm", new Credential());
        mav.addObject("deleteCredential", new Credential());
        mav.setViewName("home");
        return mav;
    }


    /*
    @PostMapping()
    public String addNote(){
    }

    @PostMapping()
    public String addCredential(){
    }

     */

}
