package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;
    private final UserService userService;

    public FileController(FileService fileService, UserService UserService) {
        this.fileService = fileService;
        this.userService = UserService;
    }

    @PostMapping("/upload")
    public ModelAndView postFile(@RequestParam(name = "fileUpload")
                                 MultipartFile mpf, Authentication auth, Model model) {

        User user = this.userService.getUser(auth.getName());
        Integer userId = user.getUserId();
        ModelAndView mav = new ModelAndView();

        if (mpf.isEmpty()) {
            mav.addObject("success", false);
            mav.addObject("error", true);
            mav.addObject("message", "No file selected to upload!");
            mav.setViewName("result");
            return mav;
        }


        if (fileService.isFilenameExists(mpf.getOriginalFilename(), userId)) {
            mav.addObject("success", false);
            mav.addObject("error", true);
            mav.addObject("message", "file name already exists");
            mav.setViewName("result");
            return mav;
        }

        try {
            fileService.createFile(mpf, userId);
            mav.addObject("success", true);
            mav.addObject("error", false);
            mav.addObject("message", "New file added successfully");
        } catch (Exception e) {
            mav.addObject("success", false);
            mav.addObject("error", true);
            mav.addObject("message", "System error!" + e.getMessage());
        }

        mav.setViewName("result");

        return mav;

    }


    @PostMapping("/delete")
    public ModelAndView deleteFile(@ModelAttribute File file, Authentication auth, Model model){
        User user = this.userService.getUser(auth.getName());
        Integer userId = user.getUserId();
        Integer fileId = file.getFileId();
        ModelAndView mav = new ModelAndView();

        try {
            fileService.deleteFile(file, userId, fileId);
            mav.addObject("success", true);
            mav.addObject("error", false);
            mav.addObject("message", "file Deleted.");
        } catch (Exception e) {
            mav.addObject("success", false);
            mav.addObject("error", true);
            mav.addObject("message", "System error!" + e.getMessage());
        }

        mav.setViewName("result");
        return mav;
    }

    @GetMapping("{fileId}")
    public ResponseEntity<Resource> download(@PathVariable("fileId") Integer fileid){
        File file = fileService.getFileByFileId(fileid);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(httpHeaders.CONTENT_DISPOSITION, "attachment; filename = " + file.getFilename());
        httpHeaders.add("Cache-control", "no-cache, no-store, must-revalidate");
        httpHeaders.add("Pragma", "no-chache");
        httpHeaders.add("Expires", "0");
        ByteArrayResource resource = new ByteArrayResource(file.getFiledata());
        return ResponseEntity.ok()
                .headers(httpHeaders).body(resource);
    }

}
