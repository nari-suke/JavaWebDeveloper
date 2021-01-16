package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public int createCredential(Credential credential){
        return this.credentialMapper.insert(credential);
    }

    public List<Credential> getAllCredential() {
        return this.credentialMapper.getAllCredentials();
    }

    public Credential getCredentialByCredentialId(Integer credentialId){
        return this.credentialMapper.getCredentialByCredentialId(credentialId);
    }

    public List<Credential> getCredentialByUserId(Integer userId){
        return this.credentialMapper.getCredentialsByUserId(userId);
    }

    public void deleteCredential(Credential credential, Integer credentialId) {
        this.credentialMapper.delete(credentialId);
    }

    public void editCredential(Credential credential){
        this.credentialMapper.update(credential);
    }

}
