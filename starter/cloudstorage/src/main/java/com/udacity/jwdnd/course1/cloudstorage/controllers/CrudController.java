package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String noteCreate(@ModelAttribute("SpringWeb") Notes note, HttpSession session, Users user, Model model, HttpServletResponse response) {
		user = (Users) session.getAttribute("user");
		if(user == null) {
			response.setStatus(403);
			model.addAttribute("success",null);
			return "error";
		}
		if (note.getNoteid() != null) {
			crudService.editNotes(note);
			user.setNotes(notesMapper.findNoteByUserId(user.getUserid()));
			model.addAttribute("success", "Success");

			return "home";

		} else {

			notesMapper.addNotetoDatabase(note, user.getUserid());
		}
		user.setNotes(notesMapper.findNoteByUserId(user.getUserid()));
		session.setAttribute("user", user);
		model.addAttribute("success", "Success");


		return "home";
	}

	@RequestMapping("/addNote.do{noteid}")
	public String noteEdit(@RequestParam("note.noteid") Integer noteid, HttpSession session, Users user, Model model,HttpServletResponse response) {
		user = (Users) session.getAttribute("user");
		if(user == null) {
			response.setStatus(403);
			model.addAttribute("success",null);
			return "error";
		}
		Notes note = notesMapper.findNoteById(noteid);
		notesMapper.addNotetoDatabase(note, user.getUserid());
		session.setAttribute("user", user);
		model.addAttribute("success", "Success");


		return "home";
	}

	@RequestMapping("/deleteNote.do{noteid}")
	public String deleteNote(@RequestParam("noteid") Integer noteid, HttpSession session,HttpServletResponse response, Model model) {
		
		Users user = (Users) session.getAttribute("user");
		if(user == null) {
			response.setStatus(403);
			model.addAttribute("success",null);
			return "error";
		}
		notesMapper.deleteNote(noteid);
		user.setNotes(notesMapper.findNoteByUserId(user.getUserid()));
		session.setAttribute("user", user);
		model.addAttribute("success", "Success");


		return "home";
	}

	// C R E D E N T I A L S
	@RequestMapping("/addCredential.do")
	public String credentialsCreate(@ModelAttribute("SpringWeb") Credentials credential, HttpSession session,
			Users user, Model model,HttpServletResponse response) {
		user = (Users) session.getAttribute("user");
		if(user == null) {
			response.setStatus(403);
			model.addAttribute("success",null);
			return "error";
		}if(credential == null) {
			response.setStatus(403);
			model.addAttribute("success",null);
			return "error";
			
		}if(credential.getUserid() == null) {
		}else if(credential.getUserid() != user.getUserid()) {
			response.setStatus(403);
			model.addAttribute("success",null);
			return "error";
		}
		credential.setSkeleton(crudService.generateRandomKey());
		String password = credential.getPassword();
		System.out.println("********" + password);
		credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getSkeleton()));
		if (credential.getCredentialid() != null) {
			System.out.println("in add if statement");
			crudService.editCredentials(credential);
			user.setCredentials(credentialsMapper.findCredentialByUserId(user.getUserid()));
			model.addAttribute("success", "Success");

			return "home";

		} else {
			System.out.println("in add else statement" + user);
			System.out.print("decrypted password" + encryptionService.decryptValue(credential.getPassword(), credential.getSkeleton()));
			credentialsMapper.addCredentialtoDatabase(credential, user.getUserid());
		}
		model.addAttribute("success", "Success");

		user.setCredentials(credentialsMapper.findCredentialByUserId(user.getUserid()));
		session.setAttribute("user", user);
		return "home";
	}

	@RequestMapping("/deleteCredential.do{credentialid}")
	public String deleteCredential(@RequestParam("credentialid") Integer credentialid, HttpSession session,HttpServletResponse response, Model model) {
		Users user = (Users) session.getAttribute("user");
		if(user == null) {
			response.setStatus(403);
			model.addAttribute("success",null);
			return "error";
		}
		if(credentialsMapper.findCredentialById(credentialid).getUserid() != user.getUserid()) {
			response.setStatus(403);
			model.addAttribute("success",null);
			return "error";
		}
		credentialsMapper.deleteCredential(credentialid);
		model.addAttribute("success", "Success");

		user.setCredentials(credentialsMapper.findCredentialByUserId(user.getUserid()));
		session.setAttribute("user", user);
		

		return "home";
	}
	
//	@RequestMapping("/decrypt.do{credentialid}")
//	public String decrypt(@RequestParam("credentialid") Integer credentialid, HttpSession session, Model model) {
//		Credentials credential = credentialsMapper.findCredentialById(credentialid);
//		String plainText = encryptionService.decryptValue(credential.getPassword(), credential.getSkeleton());
//		System.out.println(plainText);
//		return "home";
//	}
	
	// F I L E S
	
	@RequestMapping("/addfile.do")
	public String createFile(@ModelAttribute("SpringWeb") MultipartFile fileUpload, HttpSession session, Users user, Model model,HttpServletResponse response) {
		user = (Users) session.getAttribute("user");
		if(user == null) {
			response.setStatus(403);
			return "error";
		}

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
		try{
			filesMapper.addFiletoDatabase(file, user.getUserid());
		}catch(Exception e) {
			model.addAttribute("fileerror", "Error inserting file, max file size is 70 KB");
			return "home";
		}
		model.addAttribute("success", "Success");
		user.setFiles(filesMapper.findFilesByUserId(user.getUserid()));
		session.setAttribute("user", user);
		System.out.println(fileUpload.getOriginalFilename());
		return "home";
		
	}
	@RequestMapping("/deleteFile.do{fileid}")
	public String deleteFile(@RequestParam("fileid") Integer fileid, HttpSession session,HttpServletResponse response, Model model) {
		Users user = (Users) session.getAttribute("user");
		if(user == null) {
			response.setStatus(403);
			return "error";
		}
		if(filesMapper.findFilesByFileId(fileid).getUserid() != user.getUserid()) {
			response.setStatus(403);
			return "error";
		}
		filesMapper.deleteFileFromDatabase(fileid);
		user.setFiles(filesMapper.findFilesByUserId(user.getUserid()));
		model.addAttribute("success", "Success");
		session.setAttribute("user", user);
		

		return "home";
	}
	
	@GetMapping("/img/{id}")
	public void getImage(@PathVariable("id") Integer id, HttpServletResponse response) {
		
	        crudService.writeImageToRespose(id, response);
	}
}
