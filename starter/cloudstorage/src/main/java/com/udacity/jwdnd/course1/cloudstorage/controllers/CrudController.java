package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.entities.Files;
import com.udacity.jwdnd.course1.cloudstorage.entities.Notes;
import com.udacity.jwdnd.course1.cloudstorage.entities.Users;
import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.CrudServiceInterface;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;

@Controller
public class CrudController {

	
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private FilesMapper filesMapper;

	@Autowired
	private NotesMapper notesMapper;
	
	@Autowired 
	private CredentialsMapper credentialsMapper;

	@Autowired
	private CrudServiceInterface crudService;

	@Autowired
	private EncryptionService encryptionService;

	@RequestMapping("/addNote.do")
	public String noteCreate(@ModelAttribute("SpringWeb") Notes note, HttpSession session, Users user, Model model) {
		user = (Users) session.getAttribute("user");
		if (note.getNoteid() != null) {
			crudService.editNotes(note);
			user.setNotes(notesMapper.findNoteByUserId(user.getUserid()));
			return "home";

		} else {

			notesMapper.addNotetoDatabase(note, user.getUserid());
		}
		user.setNotes(notesMapper.findNoteByUserId(user.getUserid()));
		session.setAttribute("user", user);

		return "home";
	}

	@RequestMapping("/addNote.do{noteid}")
	public String noteEdit(@RequestParam("note.noteid") Integer noteid, HttpSession session, Users user, Model model) {
		user = (Users) session.getAttribute("user");
		Notes note = notesMapper.findNoteById(noteid);
		notesMapper.addNotetoDatabase(note, user.getUserid());
		session.setAttribute("user", user);

		return "home";
	}

	@RequestMapping("/deleteNote.do{noteid}")
	public String deleteNote(@RequestParam("noteid") Integer noteid, HttpSession session) {
		Users user = (Users) session.getAttribute("user");
		notesMapper.deleteNote(noteid);
		user.setNotes(notesMapper.findNoteByUserId(user.getUserid()));
		session.setAttribute("user", user);

		return "home";
	}

	// C R E D E N T I A L S
	@RequestMapping("/addCredential.do")
	public String credentialsCreate(@ModelAttribute("SpringWeb") Credentials credential, HttpSession session,
			Users user, Model model) {
		user = (Users) session.getAttribute("user");
		if (false) {
			
			user.setNotes(notesMapper.findNoteByUserId(user.getUserid()));
			return "home";

		} else {
			System.out.println(user);
			credential.setSkeleton(crudService.generateRandomKey());
			String password = credential.getPassword();
			credential.setPassword(encryptionService.encryptValue(password, credential.getSkeleton()));
			System.out.print("decrypted password" + encryptionService.decryptValue(credential.getPassword(), credential.getSkeleton()));
			credentialsMapper.addCredentialtoDatabase(credential, user.getUserid());
		}
		
		user.setCredentials(credentialsMapper.findCredentialByUserId(user.getUserid()));
		session.setAttribute("user", user);
		return "home";
	}

	@RequestMapping("/deleteCredential.do{credentialid}")
	public String deleteCredential(@RequestParam("credentialid") Integer credentialid, HttpSession session) {
		Users user = (Users) session.getAttribute("user");
		credentialsMapper.deleteCredential(credentialid);
		user.setCredentials(credentialsMapper.findCredentialByUserId(user.getUserid()));
		session.setAttribute("user", user);

		return "home";
	}
	
	@RequestMapping("/decrypt.do{credentialid}")
	public String decrypt(@RequestParam("credentialid") Integer credentialid, HttpSession session) {
		System.out.println("in decrypt");
		return null;
	}
	
	// F I L E S
	
	@RequestMapping("/addfile.do")
	public String createFile(@ModelAttribute("SpringWeb") MultipartFile fileUpload, HttpSession session, Users user, Model model) {
		user = (Users) session.getAttribute("user");

		Files file = new Files();
		try {
			file.setContenttype(fileUpload.getContentType());
			file.setFiledata(fileUpload.getBytes());
			file.setFilename(fileUpload.getOriginalFilename());
			file.setFilesize(fileUpload.getSize());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		filesMapper.addFiletoDatabase(file, user.getUserid());
		System.out.println(fileUpload.getOriginalFilename());
		return "home";
		
	}
}
