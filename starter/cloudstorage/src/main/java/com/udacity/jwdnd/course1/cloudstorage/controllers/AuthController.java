package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.udacity.jwdnd.course1.cloudstorage.entities.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthService;

@Controller
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@RequestMapping(path = "/authenticate", method = RequestMethod.GET)
	public String authenticate(Principal principal) {
	    return "home";
	}
	
	@RequestMapping(path = "/register.do", method = RequestMethod.POST)
	public String register(@ModelAttribute("SpringWeb") Users user, HttpServletResponse res) {

	    if (user == null) {
	        res.setStatus(400);
	    }

	    user = authService.register(user);

	    return "home";
	}

}
