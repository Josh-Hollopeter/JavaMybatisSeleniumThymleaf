package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.entities.Users;
import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;



@RestController
@RequestMapping("/users")
public class HelperController {
	
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private NotesMapper notesMapper;
	
	@Autowired
	private EncryptionService encryptionService;
	
	@Autowired 
	private CredentialsMapper credentialsMapper;
	

	
	@RequestMapping("/decrypt.do{credentialid}")
	public List<String> decrypt(@RequestParam("credentialid") Integer credentialid, HttpSession session, HttpServletResponse response) {
		Users user = (Users) session.getAttribute("user");
		Credentials credential = credentialsMapper.findCredentialById(credentialid);
		if(credential.getUserid() != user.getUserid()) {
			response.setStatus(403);
			return null;
		}
		List<String> plainText = new ArrayList<>();
		plainText.add(encryptionService.decryptValue(credential.getPassword(), credential.getSkeleton()));
		System.out.println("in decrypt" + plainText);
		return plainText;
	}
	
	
	
		

}
