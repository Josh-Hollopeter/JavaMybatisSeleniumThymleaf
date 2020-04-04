package com.udacity.jwdnd.course1.cloudstorage.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.udacity.jwdnd.course1.cloudstorage.entities.Notes;
import com.udacity.jwdnd.course1.cloudstorage.entities.Users;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.CrudServiceInterface;

@Controller
public class CrudController {
	
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private NotesMapper notesMapper;
	
	@Autowired
	private CrudServiceInterface crudService;
	
	
	@RequestMapping("/addNote.do")
	public String noteCreate(@ModelAttribute("SpringWeb") Notes note ,HttpSession session, Users user, Model model) {
		user = (Users) session.getAttribute("user");
		System.err.println(note);
		if(note.getNoteid() != null) {
			crudService.editNotes(note);
			user.setNotes(notesMapper.findNoteByUserId(user.getUserid()));
			return "home";
			
		}else {
			
			notesMapper.addNotetoDatabase(note, user.getUserid());
		}
		user.setNotes(notesMapper.findNoteByUserId(user.getUserid()));
		session.setAttribute("user", user);
		
		
		return "home";
	}
	@RequestMapping("/addNote.do{noteid}")
	public String noteEdit(@RequestParam("note.noteid")Integer noteid,  HttpSession session, Users user, Model model) {
		user = (Users) session.getAttribute("user");
		Notes note = notesMapper.findNoteById(noteid);
		System.out.println(user.getUserid());
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
	

}
