package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int createFile(MultipartFile mpf, Integer userId) throws IOException {
        File file = new File();
        file.setFilename(mpf.getOriginalFilename());
        file.setContenttype(mpf.getContentType());
        file.setFiledata(mpf.getBytes());
        file.setFilesize(mpf.getSize());
        file.setUserId(userId);

        return fileMapper.insert(file);
    }

    public int uploadFile(File file) {
        return fileMapper.insert(file);
    }

    public List<File> getFilesByUserId(Integer id){
        return this.fileMapper.getFilesByUserId(id);
    }

    public File getFileByFileId(Integer id){
        return this.fileMapper.getFileByFileId(id);
    }

    public int deleteFile(File file, Integer userId, Integer fileId){
        file.setUserId(userId);
        file.setFileId(fileId);
        return fileMapper.delete(file);
    }

    public boolean isFilenameExists(String filename, Integer userid) {
        return ((this.fileMapper.getFile(filename, userid) == null) ? false:true);
    }

}
