package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.entities.Users;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UsersMapper;

@Service
public class AuthService {

	@Autowired
	private UsersMapper userRepo;

	@Autowired
	private PasswordEncoder encoder;

	public Users register(Users user) {
		String encodedPW = encoder.encode(user.getPassword());
		user.setPassword(encodedPW); // only persist encoded password
		

		// set other fields to default values
		user.setEnabled(true);
		user.setRole("standard");

				userRepo.register(user);
		return user;
	}

}
