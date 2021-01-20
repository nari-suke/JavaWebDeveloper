package com.udacity.jdnd.SaltExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

//@SpringBootApplication
public class SaltExampleApplication {
	/*
	public static void main(String[] args) {
		SpringApplication.run(SaltExampleApplication.class, args);
	}
	 */

	public static byte[] createSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt;
	}

	private static String get_SecurePassword(String passwordToHash, byte[] salt){
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt);
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for(int i=0; i <bytes.length; i++){
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		}
		catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}


	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
		String passwordToHash = "myPassword";

		byte[] salt = createSalt();

		String securePassword = get_SecurePassword(passwordToHash, salt);
		System.out.println(securePassword);
	}


}
