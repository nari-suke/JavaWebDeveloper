package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/credential")
public class CredentialController {
    private final CredentialService credentialService;
    private final UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService, AuthenticationService authenticationService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    public ModelAndView postCredential(Model model, Authentication auth, @ModelAttribute Credential credential){
        User user = this.userService.getUser(auth.getName());
        Integer userId = user.getUserId();
        credential.setUserId(userId);
        ModelAndView mav = new ModelAndView();

        try{
            if(credential.getCredentialId() == null){
                credentialService.createCredential(credential);
            }else{
                credentialService.editCredential(credential);
            }
            mav.addObject("success", true);
            mav.addObject("message", "New credential added.");
        } catch(Exception e) {
            mav.addObject("error", true);
            mav.addObject("message", "System error." + e.getMessage());
            }

        mav.setViewName("result");
        return mav;
    }

    @GetMapping("/delete")
    public ModelAndView deleteCredential(Model model, Authentication auth, @ModelAttribute Credential credential){
        User user = userService.getUser(auth.getName());
        Integer userId = user.getUserId();
        Integer credentialId = credential.getCredentialId();
        credential.setUserId(userId);
        ModelAndView mav = new ModelAndView();

        try{
            credentialService.deleteCredential(credential, credentialId);
            mav.addObject("success", true);
            mav.addObject("message", "Successfully deleted.");
        } catch (Exception e) {
            mav.addObject("error", true);
            mav.addObject("message", "System error." + e.getMessage());
        }

        mav.setViewName("result");
        return mav;
    }


}
