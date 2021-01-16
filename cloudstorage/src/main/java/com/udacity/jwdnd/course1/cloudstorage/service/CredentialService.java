package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    CredentialMapper credentialMapper;
    EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public int createCredential(Credential credential){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setPassword(encryptedPassword);
        credential.setKey(encodedKey);
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
        Credential oldCredential = this.credentialMapper.getCredentialByCredentialId(credential.getCredentialId());
        String encodedKey = oldCredential.getKey();
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setPassword(encryptedPassword);
        this.credentialMapper.update(credential);
    }

}
