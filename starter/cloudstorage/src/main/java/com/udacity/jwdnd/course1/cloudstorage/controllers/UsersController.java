package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jwdnd.course1.cloudstorage.entities.Users;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthService;



@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private NotesMapper notesMapper;
	
	@GetMapping("/all")
    public List<Users> getAll() {
		
		List<Users> users = usersMapper.findAll();
		System.err.println("*****************" + users);
        return usersMapper.findAll();
    }
	
	@GetMapping("/user/{id}")
	public Users getUser(@PathVariable Integer id) {
		Users user = usersMapper.findOne(id);
		user.setNotes(notesMapper.findNoteByUserId(id));
		return user;
	}
	
	
	
		

}
