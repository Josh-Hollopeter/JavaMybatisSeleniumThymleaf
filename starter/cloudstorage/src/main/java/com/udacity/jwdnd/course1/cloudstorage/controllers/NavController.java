package com.udacity.jwdnd.course1.cloudstorage.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.entities.Users;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UsersMapper;

@Controller
public class NavController {

	@Autowired
	private UsersMapper usersMapper;

	@RequestMapping("/")
	public String home(HttpSession session, Users user, Model model) {
		System.out.println("here in home");
		if (session.getAttribute("user") == null) {
			return "login";
		}
		return "home";
	}

	@RequestMapping("/register")
	public String register(String username, String password) {
		System.out.println("here");
		return "/signup";
	}
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.setAttribute("user", null);
		return "/login";
	}

	// Login form
	@RequestMapping("/login.do")
	public String login(@ModelAttribute("SpringWeb") Users user, HttpSession session) {
		System.out.println("here" + user);
		if(user.getUsername() != null) {
		System.err.println(user.getUsername());
		Users storedUser = usersMapper.findOne(2);
		if (BCrypt.checkpw(user.getPassword(), storedUser.getPassword())) {
			session.setAttribute("user", user);
			return "home";
		}
		}

		return "redirect:login.error";
	}

	// Login form with error
	@RequestMapping("/login.error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login.html";
	}

}
