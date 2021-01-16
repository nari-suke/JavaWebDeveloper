package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    public HomeController(UserService userService, FileService fileService, NoteService noteService, CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping()
    public ModelAndView loginView(Authentication auth, Model model, EncryptionService encryptionService) {
        User user = this.userService.getUser(auth.getName());
        Integer userId = user.getUserId();
        ModelAndView mav = new ModelAndView();
        mav.addObject("files", this.fileService.getFilesByUserId(userId));
        mav.addObject("notes", this.noteService.getNotesByUserId(userId));
        mav.addObject("credentials", this.credentialService.getCredentialByUserId(userId));
        mav.addObject("credentialForm", new Credential());
        mav.addObject("deleteCredential", new Credential());
        mav.addObject("encryptionService", this.encryptionService);
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
